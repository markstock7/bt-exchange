package com.blocktrending.exchange.binance.domain.event

import com.blocktrending.exchange.binance.domain.ExecutionType.ExecutionType
import com.blocktrending.exchange.binance.domain.OrderRejectReason.OrderRejectReason
import com.blocktrending.exchange.binance.domain.OrderSide.OrderSide
import com.blocktrending.exchange.binance.domain.OrderStatus.OrderStatus
import com.blocktrending.exchange.binance.domain.OrderType.OrderType
import com.blocktrending.exchange.binance.domain.TimeInForce.TimeInForce


/**
	* Order or trade report update event.
	*
	* This event is embedded as part of a user data update event.
	*
	* @see UserDataUpdateEvent
	*/
case class OrderTradeUpdateEvent(
	eventType:        String,
	eventTime:        Long,
	symbol:           String,
	newClientOrderId: String,
	/**
		* Buy/Sell order side.
		*/
	side: OrderSide,
	/**
		* Type of order.
		*/
	`type`: OrderType,
	/**
		* Time in force to indicate how long will the order remain active.
		*/
	timeInForce: TimeInForce,
	/**
		* Original quantity in the order.
		*/
	originalQuantity: BigDecimal,
	/**
		* Price.
		*/
	price: BigDecimal,
	/**
		* Type of execution.
		*/
	executionType: ExecutionType,
	/**
		* Status of the order.
		*/
	orderStatus: OrderStatus,
	/**
		* Reason why the order was rejected.
		*/
	orderRejectReason: OrderRejectReason,
	/**
		* Order id.
		*/
	orderId: Long,
	/**
		* Quantity of the last filled trade.
		*/
	quantityLastFilledTrade: BigDecimal,
	/**
		* Accumulated quantity of filled trades on this order.
		*/
	accumulatedQuantity: BigDecimal,
	/**
		* Price of last filled trade.
		*/
	priceOfLastFilledTrade: BigDecimal,
	/**
		* Commission.
		*/
	commission: BigDecimal,
	/**
		* Asset on which commission is taken
		*/
	commissionAsset: Option[String],
	/**
		* Order/trade time.
		*/
	orderTradeTime: Long
	,
	/**
		* Trade id.
		*/
	tradeId: Long
)
