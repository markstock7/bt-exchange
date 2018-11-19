package com.blocktrending.exchange.okex

object SymbolTransfer {
	def s2l(symbol: String): String = symbol.split("/").mkString("_")
	def l2s(symbol: String): String = symbol.split("_").mkString("/")
}