package com.blocktrending.exchange.binance.domain

import play.api.libs.json.{Format, JsString, JsSuccess, JsValue}

object ExecutionType extends Enumeration {
	type ExecutionType = Value
	val NEW, CANCELED, REPLACED, REJECTED, TRADE, EXPIRED = Value

	implicit val executionTypeFormat = new Format[ExecutionType] {
		def reads(json: JsValue) = JsSuccess(ExecutionType.withName(json.as[String]))
		def writes(myEnum: ExecutionType) = JsString(myEnum.toString)
	}
}