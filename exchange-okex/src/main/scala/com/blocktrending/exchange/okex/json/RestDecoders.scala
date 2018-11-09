package com.blocktrending.exchange.okex.json

import java.time.Instant

import com.blocktrending.exchange.base.domain.{Candle, Depth, NestedSymbol, OrderBookEntry}
import io.circe.Decoder

object RestDecoders extends Decoders {

	implicit lazy val NestedSymbolDecoder:    Decoder[NestedSymbol]         = Decoder.forProduct2(
		"base_currency",
		"quote_currency"
	)((base: String, quote: String) => NestedSymbol(s"$base/$quote", base, quote))

	override implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
		Decoder.decodeTuple2[BigDecimal, BigDecimal].map {
			case (a0, a1) => OrderBookEntry(a0, a1)
		}

	implicit lazy val DepthDecoder:           Decoder[Depth]                = Decoder.forProduct2(
		"bids",
		"asks"
	)((bids: Seq[OrderBookEntry], asks: Seq[OrderBookEntry]) => Depth("", bids, asks))


	implicit lazy val CandleDecoder: Decoder[Candle] =
		Decoder.forProduct6("close", "high", "low", "open", "time", "volume")((
			close: Double, high: Double, low: Double, open: Double, time: String, volume: Double
		) => Candle("", "", Instant.parse(time).toEpochMilli, 1, open, close, high, low, volume, 0, 0))
}