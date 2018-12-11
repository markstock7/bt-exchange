package com.blocktrending.exchange.binance.domain.account

import com.blocktrending.exchange.binance.domain.AssetBalance

case class Account(
	/**
		* Maker commission.
		*/
	makerCommission: Int,
	/**
		* Taker commission.
		*/
	takerCommission: Int,
	/**
		* Buyer commission.
		*/
	buyerCommission: Int,
	/**
		* Seller commission.
		*/
	sellerCommission: Int,
	/**
		* Whether or not this account can trade.
		*/
	canTrade: Boolean,
	/**
		* Whether or not it is possible to withdraw from this account.
		*/
	canWithdraw: Boolean,
	/**
		* Whether or not it is possible to deposit into this account.
		*/
	canDeposit: Boolean,
	/**
		* Last account update time.
		*/
	updateTime: Long,
	/**
		* List of asset balances of this account.
		*/
	balances: List[AssetBalance]
) {

	/**
		* Returns the asset balance for a given symbol.
		*
		* @param symbol asset symbol to obtain the balances from
		* @return an asset balance for the given symbol which can be 0 in case the symbol has no balance in the account
		*/
	def getAssetBalance(symbol: String): Option[AssetBalance] =
		balances.find(_.asset == symbol)
}
