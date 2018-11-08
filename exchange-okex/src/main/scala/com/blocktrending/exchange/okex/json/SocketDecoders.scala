package com.blocktrending.exchange.okex.json

import com.blocktrending.exchange.base.domain._
import io.circe.{Decoder, HCursor}

object SocketDecoders extends Decoders {

	def channel2Symbol(channel: String): String =
		channel.split("_").toSeq.slice(4, 5).map(_.toUpperCase()).mkString("")

	implicit val TickerDecoder: Decoder[Ticker] = new Decoder[Ticker] {
		final def apply(c: HCursor): Decoder.Result[Ticker] = {
			val data = c.downField("data")
			for {
				channel <- c.downField("channel").as[String]
				high <- data.downField("high").as[Double]
				vol <- data.downField("vol").as[Double]
				last <- data.downField("last").as[Double]
				low <- data.downField("low").as[Double]
				buy <- data.downField("buy").as[Double]
				change <- data.downField("change").as[Double]
				sell <- data.downField("sell").as[Double]
				dayLow <- data.downField("dayLow").as[Double]
				dayHigh <- data.downField("dayHigh").as[Double]
				timestmap <- data.downField("timestamp").as[Long]
			} yield Ticker(channel2Symbol(channel), last, last * change + last, high, low, vol, 0, 0, timestmap)
		}
	}

	implicit val DepthDecoder: Decoder[Depth] = new Decoder[Depth] {
		final def apply(c: HCursor): Decoder.Result[Depth] = {
			val data = c.downField("data")
			for {
				channel <- c.downField("channel").as[String]
				bids <- data.downField("bids").as[Seq[OrderBookEntry]]
				asks <- data.downField("asks").as[Seq[OrderBookEntry]]
			} yield Depth(channel2Symbol(channel), bids, asks)
		}
	}

	implicit val AggTradeDecoder: Decoder[AggTrade] = new Decoder[AggTrade] {
		final def apply(c: HCursor): Decoder.Result[AggTrade] = {
			val data = c.downField("data")
			for {
				channel <- c.downField("channel").as[String]
				price <- c.downArray.leftN(1).as[Double]
				volume <- c.downArray.leftN(2).as[Double]
				timestamp <- c.downArray.leftN(3).as[Long]
				side <- c.downArray.leftN(3).as[String]
			} yield AggTrade(channel2Symbol(channel), price, volume, timestamp, side == "ask")
		}
	}

	implicit val CandleDecoder: Decoder[Candle] = new Decoder[Candle] {
		final def apply(c: HCursor): Decoder.Result[Candle] = {
			val data = c.downField("data")
			for {
				channel <- c.downField("channel").as[String]
				openTime <- c.downArray.leftN(0).as[Long]
				open <- c.downArray.leftN(1).as[Double]
				high <- c.downArray.leftN(2).as[Double]
				low <- c.downArray.leftN(3).as[Double]
				close <- c.downArray.leftN(4).as[Double]
				volume <- c.downArray.leftN(5).as[Double]
			} yield Candle(channel2Symbol(channel), "", openTime, 0, open, close, high, low, volume, 0 ,0 )
		}
	}

}

