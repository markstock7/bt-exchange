package com.blocktrending.exchange.okex.json

import io.circe.{Decoder, parser}
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.okex.domain._
import io.circe.generic.semiauto.deriveDecoder
import java.text.SimpleDateFormat

object RestDecoders extends Decoders {

  // 获取交易对结果的隐式转换
  implicit lazy val NestedSymbolDecoder: Decoder[NestedSymbol] =
    Decoder.forProduct2(
      "base_currency", "quote_currency"
    )((base: String, quote: String) => NestedSymbol(s"$base/$quote", base, quote))

  // candle 结果隐式转换
  implicit lazy val CandleDecoder: Decoder[Candle] =
    Decoder.forProduct6(
      "close", "high", "low", "open", "time", "volume"
    )((closePrice: Double, highPrice: Double, lowPrice: Double, openPrice: Double, openTime: String, volumn: Double) =>
      Candle(
        symbol = "",
        interval = "",
        openTime = strToLong(openTime),
        closeTime = 0,
        open = openPrice,
        close = closePrice,
        high = highPrice,
        low = lowPrice,
        volume = volumn,
        quoteAssetVolume = 0,
        numberOfTrades = 0
      )
    )

  // Ticker 结果隐式转换

  implicit lazy val TickerDecoder: Decoder[Ticker] =
    Decoder.forProduct8(
      "instrument_id", "last", "open_24h", "high_24h", "low_24h", "base_volume_24h", "quote_volume_24h", "timestamp"
    )((symbol: String , closePrice: Double, openPrice: Double, highPrice: Double, lowPrice: Double, bv: Double, qv: Double, openTime: String) =>
      Ticker(
        symbol = symbol,
        high = highPrice,
        low = lowPrice,
        baseVolume = bv,
        open = openPrice,
        close = closePrice,
        quoteVolume = qv,
        openTime = strToLong(openTime),
        closeTime = System.currentTimeMillis()
    ))

  // Depth
  override implicit lazy val OrderBookEntryDecoder:           Decoder[OrderBookEntry]                =
    Decoder.decodeTuple3[Double, Double, String].map {
      case (a0, a1, _) => OrderBookEntry(a0, a1)
    }

  implicit lazy val DepthDecoder:           Decoder[Depth]                = Decoder.forProduct2(
    "bids", "asks"
  )((bids: Seq[OrderBookEntry], asks: Seq[OrderBookEntry]) => Depth("", bids, asks))

  implicit lazy val StringDecoder: Decoder[String] = Decoder.decodeString

  def strToLong: (String => Long) = (str: String) => {
    val format: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    format.parse(str).getTime
  }
}
