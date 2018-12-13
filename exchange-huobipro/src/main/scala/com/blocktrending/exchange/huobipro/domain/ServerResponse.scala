package com.blocktrending.exchange.huobipro.domain

import com.blocktrending.exchange.base.domain._

case class PairResponse(
    data: Seq[NestedSymbol]
)

case class CandleResponse(
    data: Seq[Candle]
)

case class TickersResponse(
    data: Seq[Ticker]
)

case class DepthResponse(
    data: Depth
)
