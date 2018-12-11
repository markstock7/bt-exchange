package com.blocktrending.exchange.binance.domain

case class AssetBalance(
	/**
		* Asset symbol.
		*/
	asset: String,
	/**
		* Available balance.
		*/
	free: BigDecimal,
	/**
		* Locked by open orders.
		*/
	locked: BigDecimal
)
