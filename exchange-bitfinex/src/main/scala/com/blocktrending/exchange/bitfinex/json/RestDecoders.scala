package com.blocktrending.exchange.bitfinex.json

import java.time.ZoneId

import com.blocktrending.exchange.base.domain.{Candle, Depth, NestedSymbol, OrderBookEntry}
import io.circe.Decoder
import com.blocktrending.util.DateTime
import io.circe.generic.semiauto.deriveDecoder

object RestDecoders extends Decoders {

//	implicit lazy val NestedSymbolDecoder:    Decoder[NestedSymbol]         = Decoder.forProduct3(
//		"instrument_id",
//		"base_current",
//		"quote_currency"
//	)((symbol: String, base: String, quote: String) => NestedSymbol(symbol, base, quote))
//
//	override implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
//		Decoder.decodeTuple2[BigDecimal, BigDecimal].map {
//			case (a0, a1) => OrderBookEntry(a0, a1)
//		}
//
//	implicit lazy val DepthDecoder:           Decoder[Depth]                = Decoder.forProduct2(
//		"bids",
//		"asks"
//	)((bids: Seq[OrderBookEntry], asks: Seq[OrderBookEntry]) => Depth("", bids, asks))
//
//
//	implicit lazy val CandleDecoder: Decoder[Candle] =
//		Decoder.forProduct6("close", "high", "low", "open", "time", "volume")((
//			close: Double, high: Double, low: Double, open: Double, time: String, volume: Double
//		) => Candle("", "", 1, DateTime.toMillis(DateTime.parse(time)), open, close, high, low, volume, 0, 0))

}