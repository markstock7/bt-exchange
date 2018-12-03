package com.blocktrending.exchange.bibox.domain

import com.blocktrending.exchange.base.domain.NestedSymbol

case class PairList(
	result: Seq[NestedSymbol]
)