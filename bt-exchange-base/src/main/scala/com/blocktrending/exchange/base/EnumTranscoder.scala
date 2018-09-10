package com.blocktrending.exchange.base

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}

class EnumTranscoder[E <: Enumeration](enum: E) extends Decoder[E#Value] with Encoder[E#Value] {
	override def apply(c: HCursor): Result[E#Value] = Decoder.decodeString.map(enum.withName).apply(c)

	override def apply(a: E#Value): Json = Encoder.encodeString.apply(a.toString)
}
