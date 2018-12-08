package com.blocktrending.exchange.huobipro.domain

import com.blocktrending.exchange.base.domain.{Candle, NestedSymbol, OrderBookEntry, Ticker}

case class PairResponse(
    result: Seq[NestedSymbol]
)

case class CandleResponse(
    result: Seq[Candle]
)

case class TickersResponse(
    result: Seq[Ticker]
)

case class DepthBody (
    buy: Seq[OrderBookEntry],
    sell: Seq[OrderBookEntry]
)

case class DepthResponse (
   result: DepthBody
)

