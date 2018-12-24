package com.blocktrending.exchange.bibox.json

import io.circe.{Decoder, parser}
import com.blocktrending.exchange.base.domain.{_}
import com.blocktrending.exchange.bibox.domain._
import io.circe.generic.semiauto.deriveDecoder

object RestDecoders extends Decoders {

  // 获取交易对结果的隐式转换
  implicit lazy val PairResponseDecoder: Decoder[PairResponse] = Decoder.forProduct1(
    "result"
  )((result: Seq[NestedSymbol]) => PairResponse(result))

  implicit lazy val NestedSymbolDecoder: Decoder[NestedSymbol] =
    Decoder.forProduct1(
      "pair"
    )((pair: String) => {
      pair.split("_") match {
        case Array(base, quote) => NestedSymbol(s"$base/$quote", base, quote)
      }
    })

  // candle 结果隐式转换
  implicit lazy val CandleRepsonseDecoder: Decoder[CandleRepsonse] = Decoder.forProduct1(
    "result"
  )((result: Seq[Candle]) => CandleRepsonse(result))

  implicit lazy val CandleDecoder: Decoder[Candle] =
    Decoder.forProduct6(
      "time", "open", "high", "low", "close", "vol"
    )((time: Long, open: Double, high: Double, low: Double, close: Double, vol: Double) =>
      Candle("BIBOX", "", time, 0, open, close, high, low, vol, 0, 0)
    )

  // tickers
  implicit lazy val TickersDecoder:           Decoder[TickersResponse]                = Decoder.forProduct1(
    "result",
  )((result: Seq[Ticker]) => TickersResponse(result))

  implicit lazy val TickerResponseDecoder:           Decoder[TickerResponse]                = Decoder.forProduct1(
    "result",
  )((result: Ticker) => TickerResponse(result))

  implicit lazy val TickerDecoder: Decoder[Ticker] =
    Decoder.forProduct9(
      "coin_symbol", "currency_symbol", "last", "high", "low", "change", "percent", "vol24H", "amount"
    )((quote: String, base: String, closePrice: Double, highPrice: Double, lowPrice: Double, change: String, percent: String, volumn: Double, BaseVolume: Double) =>
      Ticker(
        symbol = s"${quote}_${base}",
        high = highPrice,
        low = lowPrice,
        baseVolume = volumn,
        open = 0,
        close = closePrice,
        quoteVolume = BaseVolume,
        openTime = 0,
        closeTime = System.currentTimeMillis()
      ))

  // history
  // TODO 不支持按照时间去回去数据

  // depths
  // TODO API不提供

  // depthsWithPair
  override implicit lazy val OrderBookEntryDecoder:           Decoder[OrderBookEntry]                = Decoder.forProduct2(
    "price", "volume"
  )((price: BigDecimal, quantity: BigDecimal) => OrderBookEntry(price, quantity))

  implicit lazy val DepthBodyDecoder:           Decoder[Depth]                = Decoder.forProduct2(
    "asks", "bids"
  )((asks: Seq[OrderBookEntry], bids: Seq[OrderBookEntry]) => Depth("", asks, bids))

  implicit lazy val DepthDecoder:           Decoder[DepthResponse]                = Decoder.forProduct1(
    "result",
  )((result: Depth) => DepthResponse(result))


  implicit lazy val StringDecoder: Decoder[String] = Decoder.decodeString
  implicit lazy val ServerResponseDecoder: Decoder[ServerTimeResponse] = deriveDecoder[ServerTimeResponse]
}
