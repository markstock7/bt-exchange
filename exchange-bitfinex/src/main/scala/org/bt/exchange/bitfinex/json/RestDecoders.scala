package org.bt.exchange.bitfinex.json

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import org.bt.exchange.bitfienx.domain.{Candle, Ticker, Trade}



object RestDecoders extends Decoders {

	/**
		* [
		*   SYMBOL,
		*   BID,
		*   BID_SIZE,
		*   ASK,
		*   ASK_SIZE,
		*   DAILY_CHANGE,
		*   DAILY_CHANGE_PERC,
		*   LAST_PRICE,
		*   VOLUME,
		*   HIGH,
		*   LOW
		* ]
		*/
	implicit lazy val TickerDecoder: Decoder[Ticker] =
		Decoder.decodeTuple11[String, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal]
			.map {
				case (symbol, bid, bidSize, ask, askSize, dailyChange, dailyChangePerc, lastPrice, volume, high, low, _) =>
					Ticker(symbol, bid, bidSize, ask, askSize, dailyChange, dailyChangePerc, lastPrice, volume, high, low)
			}


	/**
		* [
		*   timestmap
		*   open
		*   close
		*   high
		*   low
		*   volume
		* ]
		*/
	implicit lazy val CandleDecoder: Decoder[Candle] =
		Decoder.decodeTuple6[BigInt, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal]
			.map {
				case (timestamp, open, close, high, low, volume, _) => Candle(timestamp, open, close, high, low, volume)
			}


	implicit lazy val TradeDecoder: Decoder[Trade] =
		Decoder.decodeTuple5[BigInt, BigInt, BigDecimal, BigDecimal, Int]
			.map {
				case (id, timestamp, amount ,price) => Trade(id, timestamp, amount, price)
			}

}
