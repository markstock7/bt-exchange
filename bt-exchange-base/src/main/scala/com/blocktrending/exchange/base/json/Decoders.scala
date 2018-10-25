package com.blocktrending.exchange.base.json

import com.blocktrending.exchange.base.domain.OrderBookEntry
import io.circe.Decoder

abstract class Decoders {
	implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
		Decoder.decodeTuple3[BigDecimal, BigDecimal, Seq[String]].map {
			case (a0, a1, _) => OrderBookEntry(a0, a1)
		}
}