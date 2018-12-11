package com.blocktrending.exchange.binance.domain

import play.api.libs.json.{Format, JsString, JsSuccess, JsValue}

object OrderType extends Enumeration {
	type OrderType = Value
	val LIMIT, MARKET, STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, TAKE_PROFIT_LIMIT, LIMIT_MAKER = Value

	implicit val orderTypeFormat = new Format[OrderType] {
		def reads(json: JsValue) = JsSuccess(OrderType.withName(json.as[String]))
		def writes(myEnum: OrderType) = JsString(myEnum.toString)
	}
}
