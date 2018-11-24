package com.blocktrending.exchange.huobipro.json

import java.time.ZoneId

import com.blocktrending.exchange.base.domain.{Candle, Depth, NestedSymbol, OrderBookEntry}
import io.circe.Decoder
import com.blocktrending.util.DateTime
import io.circe.generic.semiauto.deriveDecoder

object RestDecoders extends Decoders {

}