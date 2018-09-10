package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.base.EnumTranscoder
import com.blocktrending.exchange.binance.domain.{CandlestickInterval, OrderBookEntry}
import io.circe.Decoder
import com.blocktrending.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.binance.domain.OrderBookEntry

class Decoders {
	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)

	implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
		Decoder.decodeTuple3[BigDecimal, BigDecimal, Seq[String]].map {
			case (a0, a1, _) => OrderBookEntry(a0, a1)
		}
}

