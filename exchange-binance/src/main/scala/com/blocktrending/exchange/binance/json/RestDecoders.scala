package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.base.domain.{SimpleTicker, _}
import com.blocktrending.exchange.binance.domain.{ExchangeInfo, ServerResponse}
import io.circe.{Decoder, HCursor}
import io.circe.generic.semiauto.deriveDecoder

object RestDecoders extends Decoders {

	implicit lazy val ServerResponseDecoder:  Decoder[ServerResponse]       = deriveDecoder[ServerResponse]
	implicit lazy val StringDecoder:          Decoder[String]               = Decoder.decodeString

	implicit lazy val NestedSymbolDecoder:    Decoder[NestedSymbol]         = deriveDecoder[NestedSymbol]
	implicit lazy val ExchangeInfoDecoder:    Decoder[ExchangeInfo]         = deriveDecoder[ExchangeInfo]

	override implicit lazy val OrderBookEntryDecoder: Decoder[OrderBookEntry] =
		Decoder.decodeTuple1[BigDecimal].map {
			case (a0) => OrderBookEntry(a0._1, 1)
		}

	implicit lazy val DepthDecoder:           Decoder[Depth]                = Decoder.forProduct2(
		"bids",
		"asks"
	)((bids: Seq[OrderBookEntry], asks: Seq[OrderBookEntry]) => Depth("", bids, asks))

	implicit lazy val CandleDecoder:          Decoder[Candle] =
		Decoder.decodeTuple12[Long, Double, Double, Double, Double, Double, Long, Double, Int, Double, Double, String]
			.map {
				case (openTime, open, high, low, close, volume, closeTime, quoteV, trades, bakerByBaseV, takerBuyQuoteV, _) =>
					Candle("", "", openTime, closeTime, open, close, high, low, volume, quoteV, trades)
			}

	implicit lazy val TickerDecoder:          Decoder[Ticker] = Decoder.forProduct9(
		"symbol",
		"lastPrice",
		"openPrice",
		"highPrice",
		"lowPrice",
		"volume",
		"quoteVolume",
		"openTime",
		"closeTime"
	)(Ticker.apply)

	implicit lazy val SimpleTickerDecoder:    Decoder[SimpleTicker] = Decoder.forProduct2(
		"symbol",
		"price"
	)(SimpleTicker.apply)
}
