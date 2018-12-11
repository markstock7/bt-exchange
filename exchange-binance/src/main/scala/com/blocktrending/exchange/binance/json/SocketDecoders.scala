package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import com.blocktrending.exchange.binance.domain.AssetBalance
import com.blocktrending.exchange.binance.domain.event.{AccountUpdateEvent, OrderTradeUpdateEvent}
import io.circe.{Decoder, DecodingFailure, HCursor, JsonObject}

object SocketDecoders extends Decoders {

	implicit lazy val DepthEvent: Decoder[Depth] = Decoder.forProduct3("s", "b", "a")(Depth.apply)

	implicit lazy val AggTradeDecoder: Decoder[AggTrade] = Decoder.forProduct5(
		"s",
		"p",
		"q",
		"T",
		"m"
	)(AggTrade.apply)

	implicit lazy val TickerDecoder: Decoder[Ticker] = Decoder.forProduct9(
		"s",
		"c",
		"o",
		"h",
		"l",
		"v",
		"q",
		"O",
		"C"
	)(Ticker.apply)

	implicit val CandleDecoder: Decoder[Candle] = new Decoder[Candle] {
		final def apply(c: HCursor): Decoder.Result[Candle] = {
			val data = c.downField("data").downField("k")
			for {
				openTime <- data.downField("t").as[Long]
				closeTime <- data.downField("T").as[Long]
				symbol <- data.downField("s").as[String]
				interval <- data.downField("i").as[String]
				open <- data.downField("o").as[Double]
				close <- data.downField("c").as[Double]
				high <- data.downField("h").as[Double]
				low <- data.downField("l").as[Double]
				volume <- data.downField("v").as[Double]
				quoteAssetVolume <- data.downField("q").as[Double]
				numberOfTrades <- data.downField("n").as[Int]
			} yield Candle(symbol, interval, openTime, closeTime, open, close, high, low, volume, quoteAssetVolume, numberOfTrades)
		}
	}


	implicit lazy val AssetBalanceDecoder: Decoder[AssetBalance] =
		Decoder.forProduct3("a", "f", "l")(AssetBalance.apply)

	implicit lazy val AccountUpdateEventDecoder: Decoder[AccountUpdateEvent] =
		Decoder.forProduct3("e", "E", "B")(AccountUpdateEvent.apply)

	implicit lazy val OrderTradeUpdateEventDecoder: Decoder[OrderTradeUpdateEvent] =
		Decoder.forProduct20("e",
			"E",
			"s",
			"c",
			"S",
			"o",
			"f",
			"q",
			"p",
			"x",
			"X",
			"r",
			"i",
			"l",
			"z",
			"L",
			"n",
			"N",
			"T",
			"t")(OrderTradeUpdateEvent.apply)

	implicit lazy val UserDataUpdateEventDecoder: Decoder[Either[OrderTradeUpdateEvent, AccountUpdateEvent]] =
		Decoder.decodeJsonObject.flatMap { obj: JsonObject =>
			obj("e").flatMap(_.asString) match {
				case Some("executionReport") =>
					OrderTradeUpdateEventDecoder.map(Left.apply)
				case Some("outboundAccountInfo") =>
					AccountUpdateEventDecoder.map(Right.apply)
				case _ =>
					(c: HCursor) =>
						Left(DecodingFailure("Not a valid UserDataUpdateEvent", c.history))
			}
		}
}
