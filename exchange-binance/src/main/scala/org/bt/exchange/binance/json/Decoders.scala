package org.bt.exchange.binance.json

import io.circe.Decoder
import org.bt.exchange.base.EnumTranscoder
import org.bt.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import org.bt.exchange.binance.domain.{CandlestickInterval, OrderBookEntry}

class Decoders {
	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)

	implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
		Decoder.decodeTuple3[BigDecimal, BigDecimal, Seq[String]].map {
			case (a0, a1, _) => OrderBookEntry(a0, a1)
		}
}

