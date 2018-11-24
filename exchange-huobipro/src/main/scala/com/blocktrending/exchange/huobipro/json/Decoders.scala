package com.blocktrending.exchange.huobipro.json

import io.circe.Decoder
import com.blocktrending.util.json.EnumTranscoder

abstract class Decoders extends com.blocktrending.exchange.base.json.Decoders {
	//	implicit lazy val CandlestickIntervalDecoder: Decoder[CandlestickInterval] = new EnumTranscoder(CandlestickInterval)
}

