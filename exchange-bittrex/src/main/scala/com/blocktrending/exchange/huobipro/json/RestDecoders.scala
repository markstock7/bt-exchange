package com.blocktrending.exchange.huobipro.json

import io.circe.{Decoder, parser}
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.huobipro.domain._
import io.circe.generic.semiauto.deriveDecoder
import java.text.SimpleDateFormat

object RestDecoders extends Decoders {

  // 获取交易对结果的隐式转换
  implicit lazy val PairResponseDecoder: Decoder[PairResponse] = Decoder.forProduct1(
    "result"
  )((result: Seq[NestedSymbol]) => PairResponse(result))

  implicit lazy val NestedSymbolDecoder: Decoder[NestedSymbol] =
    Decoder.forProduct2(
      "MarketCurrency", "BaseCurrency"
    )((base: String, quote: String) => NestedSymbol(s"$base/$quote", base, quote))

  // candle 结果隐式转换
  implicit lazy val CandleRepsonseDecoder: Decoder[CandleResponse] = Decoder.forProduct1(
    "result"
  )((result: Seq[Candle]) => CandleResponse(result))

  implicit lazy val CandleDecoder: Decoder[Candle] =
    Decoder.forProduct7(
      "BV", "C", "H", "L", "O", "T", "V"
    )((qva: Double , closePrice: Double, highPrice: Double, lowPrice: Double, openPrice: Double, openTime: String, volumn: Double) =>
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
        quoteAssetVolume = qva,
        numberOfTrades = 0
      )
    )

  // Ticker 结果隐式转换
  implicit lazy val TickersRepsonseDecoder: Decoder[TickersResponse] = Decoder.forProduct1(
    "result"
  )((result: Seq[Ticker]) => TickersResponse(result))

  implicit lazy val TickerDecoder: Decoder[Ticker] =
    Decoder.forProduct8(
      "MarketName", "High", "Low", "Volume", "BaseVolume", "TimeStamp", "PrevDay", "Last"
    )((symbol: String , hightPrice: Double, lowPrice: Double, volumn: Double, BaseVolume: Double, openTime: String, openPrice: Double, closePrice: Double) =>
      Ticker(
        symbol = symbol,
        high = hightPrice,
        low = lowPrice,
        baseVolume = volumn,
        open = openPrice,
        close = closePrice,
        quoteVolume = BaseVolume,
        openTime = strToLong(openTime),
        closeTime = System.currentTimeMillis()
    ))

  // Depth
  override implicit lazy val OrderBookEntryDecoder:           Decoder[OrderBookEntry]                = Decoder.forProduct2(
    "Quantity", "Rate"
  )((quantity: BigDecimal, price: BigDecimal) => OrderBookEntry(price, quantity))

  implicit lazy val DepthBodyDecoder:           Decoder[DepthBody]                = Decoder.forProduct2(
    "sell", "buy"
  )((sell: Seq[OrderBookEntry], buy: Seq[OrderBookEntry]) => DepthBody(buy, sell))

  implicit lazy val DepthDecoder:           Decoder[Depth]                = Decoder.forProduct1(
    "result",
  )((result: DepthBody) => Depth("", result.buy, result.sell))


  implicit lazy val StringDecoder: Decoder[String] = Decoder.decodeString

  def strToLong: (String => Long) = (str: String) => {
    val format: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    format.parse(str).getTime
  }
}
