package com.blocktrending.exchange.bitfinex.json

import com.blocktrending.exchange.bitfinex.domain.CandlestickInterval
import com.blocktrending.exchange.bitfinex.domain.CandlestickInterval.CandlestickInterval
import io.circe.Decoder
import com.blocktrending.util.json.EnumTranscoder

abstract class Decoders extends com.blocktrending.exchange.base.json.Decoders {
	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)
}



