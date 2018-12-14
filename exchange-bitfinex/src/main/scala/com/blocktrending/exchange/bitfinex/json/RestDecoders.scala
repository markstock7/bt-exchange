package com.blocktrending.exchange.bitfinex.json

import java.text.SimpleDateFormat

import com.blocktrending.exchange.base.domain._
import io.circe.Decoder

object RestDecoders extends Decoders {

  // candle 结果隐式转换
  implicit lazy val CandleDecoder: Decoder[Candle] =
    Decoder.decodeTuple6[Long, Double, Double, Double, Double, Double].map {
      case (openTime, openPrice, closePrice, highPrice, lowPrice, volume) =>
        Candle(
          symbol = "",
          interval = "",
          openTime = openTime,
          closeTime = 0,
          open = openPrice,
          close = closePrice,
          high = highPrice,
          low = lowPrice,
          volume = volume,
          quoteAssetVolume = 0,
          numberOfTrades = 0
        )
    }

  implicit lazy val TickerDecoder: Decoder[Ticker] =
    Decoder.decodeTuple11[String, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double].map {
      case (pair, bid, bidSize, ask, askSize, change, percent, closePrice, volume, highPrice, lowPrice) => {
        Ticker(
          symbol = pair,
          high = highPrice,
          low = lowPrice,
          baseVolume = volume,
          open = 0,
          close = closePrice,
          quoteVolume = 0,
          openTime = 0,
          closeTime = System.currentTimeMillis()
        )
      }
    }

//  implicit lazy val Ticker2Decoder: Decoder[Ticker] =
//    Decoder.decodeTuple10[Double, Double, Double, Double, Double, Double, Double, Double, Double, Double].map {
//      case (bid, bidSize, ask, askSize, change, percent, closePrice, volume, highPrice, lowPrice) => {
//        Ticker(
//          symbol = "",
//          high = highPrice,
//          low = lowPrice,
//          baseVolume = volume,
//          open = null,
//          close = closePrice,
//          quoteVolume = null,
//          openTime = null,
//          closeTime = System.currentTimeMillis()
//        )
//      }
//    }

  implicit lazy val AggTradeDecoder: Decoder[AggTrade] =
    Decoder.decodeTuple4[Int, Long, Double, Double].map {
      case (id, time, amount, price) =>
        AggTrade(
          symbol = "",
          price = price,
          quantity = amount,
          tradeTime = time,
          isBuyerMake = (amount > 0)
        )
      }

  // Depth
  override implicit lazy val OrderBookEntryDecoder:           Decoder[OrderBookEntry] =
  Decoder.decodeTuple3[BigDecimal, Double, BigDecimal].map {
    case (price, count, amount) =>
      OrderBookEntry(
        price = price,
        qty = amount
      )
  }

  implicit lazy val StringDecoder: Decoder[String] = Decoder.decodeString

  def strToLong: (String => Long) = (str: String) => {
    val format: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    format.parse(str).getTime
  }
}