package com.blocktrending.exchange.okex.json

import io.circe.{Decoder, parser}
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.okex.domain._
import io.circe.generic.semiauto.deriveDecoder
import java.text.SimpleDateFormat

// symbols

// candles
// candlesWithPair

// Tickers
// TickersWithPair

// trades
// tradesWithPair

// Depths
// DepthsWithPair

object RestDecoders extends Decoders {

  // 获取交易对结果的隐式转换
  implicit lazy val PairResponseDecoder: Decoder[PairResponse] = Decoder.forProduct1(
    "data"
  )((result: Seq[NestedSymbol]) => PairResponse(result))

  implicit lazy val NestedSymbolDecoder: Decoder[NestedSymbol] =
    Decoder.forProduct2(
      "base-currency", "quote-currency"
    )((base: String, quote: String) => NestedSymbol(s"$base/$quote", base, quote))

  // candle 结果隐式转换
  implicit lazy val CandleRepsonseDecoder: Decoder[CandleResponse] = Decoder.forProduct1(
    "data"
  )((result: Seq[Candle]) => CandleResponse(result))

  implicit lazy val CandleDecoder: Decoder[Candle] =
    Decoder.forProduct8(
      "id", "open", "close", "low", "high", "amount", "vol", "count"
    )((id: Long , openPrice: Double, closePrice: Double, lowPrice: Double, highPrice: Double, amount: Double, volumn: Double, qvs: Long) =>
      Candle(
        symbol = "",
        interval = "",
        openTime = id,
        closeTime = 0,
        open = openPrice,
        close = closePrice,
        high = highPrice,
        low = lowPrice,
        volume = volumn,
        quoteAssetVolume = amount,
        numberOfTrades = qvs
      )
    )

  // Ticker 结果隐式转换
  implicit lazy val TickersRepsonseDecoder: Decoder[TickersResponse] = Decoder.forProduct1(
    "data"
  )((result: Seq[Ticker]) => TickersResponse(result))

  implicit lazy val TickerDecoder: Decoder[Ticker] =
    Decoder.forProduct8(
      "open", "close", "low", "high", "amount", "count", "vol", "symbol"
    )((openPrice: Double, closePrice: Double, lowPrice: Double, highPrice: Double, amount: Double, count: Double, vol: Double, pair: String) =>
      Ticker(
        symbol = pair,
        high = highPrice,
        low = lowPrice,
        baseVolume = vol,
        open = openPrice,
        close = closePrice,
        quoteVolume = amount,
        openTime = 0,
        closeTime = System.currentTimeMillis()
    ))

  // Depth
  implicit lazy val DepthBodyDecoder:           Decoder[Depth]                = Decoder.forProduct2(
    "bids", "asks"
  )((bids: Seq[OrderBookEntry], asks: Seq[OrderBookEntry]) => Depth("", bids, asks))

  implicit lazy val DepthDecoder:           Decoder[DepthResponse]                = Decoder.forProduct1(
    "tick",
  )((tick: Depth) => DepthResponse(tick))


  implicit lazy val StringDecoder: Decoder[String] = Decoder.decodeString
}
