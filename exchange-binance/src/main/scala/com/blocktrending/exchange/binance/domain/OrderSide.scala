package com.blocktrending.exchange.binance.domain

import play.api.libs.json.{Format, JsString, JsSuccess, JsValue}

object OrderSide extends Enumeration {
	type OrderSide = Value
	val BUY, SELL = Value

	implicit val orderSideFormat = new Format[OrderSide] {
		def reads(json: JsValue) = JsSuccess(OrderSide.withName(json.as[String]))
		def writes(myEnum: OrderSide) = JsString(myEnum.toString)
	}
}
