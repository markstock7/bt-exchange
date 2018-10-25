package com.blocktrending.exchange.base

import play.api.libs.json.{Format, JsString, JsSuccess, JsValue}

object Exchange extends Enumeration {
	type Exchange = Value

	// @formatter:off
	val BINANCE,
			HUOBIPRO,
			OKEX,
			BITTREX,
			BITFINEX = Value
	// @formatter:on

	implicit val ExchangeFormat: Format[Exchange.Exchange] = new Format[Exchange] {
		override def reads(json: JsValue) = JsSuccess(Exchange.withName(json.as[String]))
		override def writes(o: Exchange.Exchange): JsValue = JsString(o.toString)
	}
}