package com.blocktrending.exchange.bibox.domain
import com.blocktrending.exchange.base.domain.{
  Candle,
  NestedSymbol,
  Depth,
  Ticker
}

case class PairResponse(
    result: Seq[NestedSymbol]
)

case class CandleRepsonse(
    result: Seq[Candle]
)

case class ServerTimeResponse(
    serverTime: Long
)

case class TickersResponse(
    result: Seq[Ticker]
)

case class TickerResponse(
    result: Ticker
)

case class DepthResponse(
    result: Depth
)
