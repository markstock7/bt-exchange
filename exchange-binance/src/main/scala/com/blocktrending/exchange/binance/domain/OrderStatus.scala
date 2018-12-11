package com.blocktrending.exchange.binance.domain

import play.api.libs.json.{Format, JsString, JsSuccess, JsValue}

object OrderStatus extends Enumeration {
	type OrderStatus = Value
	val NEW, PARTIALLY_FILLED, FILLED, CANCELED, PENDING_CANCEL, REJECTED, EXPIRED = Value

	implicit val orderStatusFormat = new Format[OrderStatus] {
		def reads(json: JsValue) = JsSuccess(OrderStatus.withName(json.as[String]))
		def writes(myEnum: OrderStatus) = JsString(myEnum.toString)
	}
}

