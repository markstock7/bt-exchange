package com.blocktrending.exchange.base.json

import com.blocktrending.exchange.base.domain.OrderBookEntry
import io.circe.Decoder

abstract class Decoders {
	implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
		Decoder.decodeTuple2[BigDecimal, BigDecimal].map {
			case (a0, a1) => OrderBookEntry(a0, a1)
		}
}