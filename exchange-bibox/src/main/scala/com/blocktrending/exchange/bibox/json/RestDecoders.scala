package com.blocktrending.exchange.bibox.json

import io.circe.{Decoder, parser}
import com.blocktrending.exchange.base.domain.{_}
import com.blocktrending.exchange.bibox.domain._
import io.circe.generic.semiauto.deriveDecoder

object RestDecoders extends Decoders {

  // 获取交易对结果的隐式转换
  implicit lazy val PairResponseDecoder: Decoder[PairResponse] = Decoder.forProduct2(
    "result", "cmd"
  )((result: Seq[NestedSymbol], cmd: String) => PairResponse(result, cmd))

  implicit lazy val NestedSymbolDecoder: Decoder[NestedSymbol] =
    Decoder.forProduct1(
      "pair"
    )((pair: String) => {
      pair.split("_") match {
        case Array(base, quote) => NestedSymbol(s"$base/$quote", base, quote)
      }
    })

  // candle 结果隐式转换
  implicit lazy val CandleRepsonseDecoder: Decoder[CandleRepsonse] = Decoder.forProduct2(
    "result", "cmd"
  )((result: Seq[Candle], cmd: String) => CandleRepsonse(result, cmd))

  implicit lazy val CandleDecoder: Decoder[Candle] =
    Decoder.forProduct6(
      "time", "open", "high", "low", "close", "vol"
    )((time: Long, open: Double, high: Double, low: Double, close: Double, vol: Double) =>
      Candle("BIBOX", "", time, 0, open, close, high, low, vol, 0, 0)
    )

  implicit lazy val StringDecoder: Decoder[String] = Decoder.decodeString
  implicit lazy val ServerResponseDecoder: Decoder[ServerTimeResponse] = deriveDecoder[ServerTimeResponse]
}
