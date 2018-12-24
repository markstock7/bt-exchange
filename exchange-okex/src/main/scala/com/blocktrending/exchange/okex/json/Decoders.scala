package com.blocktrending.exchange.okex.json

import com.blocktrending.exchange.okex.domain.CandlestickInterval
import io.circe.Decoder
import com.blocktrending.exchange.okex.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.util.json.EnumTranscoder

abstract class Decoders extends com.blocktrending.exchange.base.json.Decoders {
	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)
}

