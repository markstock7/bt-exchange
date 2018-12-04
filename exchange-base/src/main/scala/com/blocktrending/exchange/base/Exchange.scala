package com.blocktrending.exchange.base

import play.api.libs.json.{Format, JsString, JsSuccess, JsValue}
import play.api.mvc.QueryStringBindable

object Exchange extends Enumeration {
  type Exchange = Value

  // @formatter:off
	val BINANCE,
			HUOBIPRO,
			OKEX,
			BITTREX,
		  BIBOX,
		  POLONIEX,
	    GATEIO,
		  DIGFINEX,
		  HITBTC,
	    COINBENE,
	    BITZ,
		  UPBIT,
	    KRAKEN,
	    COINW,
	    FCOIN,
	    KUCOIN,
		  LIQUID,
			BITFINEX = Value
	// @formatter:on

  implicit val ExchangeFormat: Format[Exchange.Exchange] =
    new Format[Exchange] {
      override def reads(json: JsValue) =
        JsSuccess(Exchange.withName(json.as[String]))
      override def writes(o: Exchange.Exchange): JsValue = JsString(o.toString)
    }

  implicit object ExchangeQueryStringBinder
      extends QueryStringBindable.Parsing[Exchange.Exchange](
        withName _,
        _.toString,
        (k: String, e: Exception) =>
          "Cannot parse %s as Exchange: %s".format(k, e.getMessage())
      )
}
