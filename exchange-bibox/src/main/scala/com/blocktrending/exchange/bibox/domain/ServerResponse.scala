package com.blocktrending.exchange.bibox.domain

import com.blocktrending.exchange.base.domain.NestedSymbol
import com.blocktrending.exchange.base.domain.Candle

case class PairResponse(
    result: Seq[NestedSymbol],
    cmd: String
)

case class CandleRepsonse(
    result: Seq[Candle],
    com: String
)

case class ServerTimeResponse(
    serverTime: Long
)
