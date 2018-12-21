package com.blocktrending.exchange.base.domain

case class NestedSymbol (
	symbol: String,
	baseAsset: String,
	quoteAsset: String
) {
	def pair: String = s"${baseAsset.toUpperCase}/${quoteAsset.toUpperCase}"
}
object NestedSymbol {
	def apply(baseAsset: String, quoteAsset: String): NestedSymbol = NestedSymbol(
		s"${baseAsset.toUpperCase}${quoteAsset.toUpperCase}",
		baseAsset,
		quoteAsset
	)
}