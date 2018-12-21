package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.binance.domain._
import io.circe.Decoder
import com.blocktrending.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.binance.domain.ExecutionType.ExecutionType
import com.blocktrending.exchange.binance.domain.OrderRejectReason.OrderRejectReason
import com.blocktrending.exchange.binance.domain.OrderSide.OrderSide
import com.blocktrending.exchange.binance.domain.OrderStatus.OrderStatus
import com.blocktrending.exchange.binance.domain.OrderType.OrderType
import com.blocktrending.exchange.binance.domain.TimeInForce.TimeInForce
import com.blocktrending.exchange.binance.domain.market.TickerPrice
import com.blocktrending.util.json.EnumTranscoder

abstract class Decoders extends com.blocktrending.exchange.base.json.Decoders {
	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)

	/* java enums */
	implicit lazy val OrderSideDecoder:           Decoder[OrderSide]           = new EnumTranscoder(OrderSide)
	implicit lazy val OrderStatusDecoder:         Decoder[OrderStatus]         = new EnumTranscoder(OrderStatus)
	implicit lazy val OrderTypeDecoder:           Decoder[OrderType]           = new EnumTranscoder(OrderType)
	implicit lazy val TimeInForceDecoder:         Decoder[TimeInForce]         = new EnumTranscoder(TimeInForce)
	implicit lazy val ExecutionTypeDecoder:       Decoder[ExecutionType]       = new EnumTranscoder(ExecutionType)
	implicit lazy val OrderRejectReasonDecoder:   Decoder[OrderRejectReason]   = new EnumTranscoder(OrderRejectReason)
	implicit lazy val AssetBalanceDecoder: Decoder[AssetBalance] =
		Decoder.forProduct3("a", "f", "l")(AssetBalance.apply)
}

