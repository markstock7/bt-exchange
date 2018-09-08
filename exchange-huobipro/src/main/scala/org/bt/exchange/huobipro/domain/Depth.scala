package org.bt.exchange.huobipro.domain

case class Depth (
	bids: List[(BigDecimal, BigDecimal)],
	asks: List[(BigDecimal, BigDecimal)]
)
