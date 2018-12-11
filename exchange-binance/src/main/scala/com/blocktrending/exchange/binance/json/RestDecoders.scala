package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.base.domain.{SimpleTicker, _}
import com.blocktrending.exchange.binance.domain.account.{Account, NewOrderResponse, Order, Trade}
import com.blocktrending.exchange.binance.domain.event.ListenKey
import com.blocktrending.exchange.binance.domain.{ExchangeInfo, ServerResponse}
import io.circe.{Decoder, HCursor}
import io.circe.generic.semiauto.deriveDecoder

object RestDecoders extends Decoders {

	implicit lazy val ServerResponseDecoder:    Decoder[ServerResponse]       = deriveDecoder[ServerResponse]
	implicit lazy val StringDecoder:            Decoder[String]               = Decoder.decodeString
	implicit lazy val NestedSymbolDecoder:      Decoder[NestedSymbol]         = deriveDecoder[NestedSymbol]
	implicit lazy val ExchangeInfoDecoder:      Decoder[ExchangeInfo]         = deriveDecoder[ExchangeInfo]
	implicit lazy val AccountDecoder:           Decoder[Account]              = deriveDecoder[Account]
	implicit lazy val NewOrderResponseDecoder:  Decoder[NewOrderResponse]     = deriveDecoder[NewOrderResponse]
	implicit lazy val OrderDecoder:             Decoder[Order]                = deriveDecoder[Order]
	implicit lazy val TradeDecoder:             Decoder[Trade]                = deriveDecoder[Trade]
	implicit lazy val ListenKeyDecoder:         Decoder[ListenKey]            = deriveDecoder[ListenKey]

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
