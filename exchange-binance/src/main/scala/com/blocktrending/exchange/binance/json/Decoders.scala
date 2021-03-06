package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.binance.domain.CandlestickInterval
import io.circe.Decoder
import com.blocktrending.exchange.binance.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.util.json.EnumTranscoder

abstract class Decoders extends com.blocktrending.exchange.base.json.Decoders {
	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)
}

