package com.blocktrending.exchange.binance.json

import com.blocktrending.exchange.base.domain.{AggTrade, Candle, Depth, Ticker}
import io.circe.{Decoder, HCursor}

object SocketDecoders extends Decoders {


	/**
	"e": "depthUpdate", // Event type
    "E": 123456789,     // Event time
    "s": "BNBBTC",      // Symbol
    "U": 157,           // First update ID in event
    "u": 160,           // Final update ID in event
    "b": [              // Bids to be updated
      [
        "0.0024",       // price level to be updated
        "10",
        []              // ignore
      ]
    ],
    "a": [              // Asks to be updated
      [
        "0.0026",       // price level to be updated
        "100",          // quantity
        []              // ignore
      ]
    ]
		*/
	implicit lazy val DepthEvent: Decoder[Depth] = Decoder.forProduct3("s", "b", "a")(Depth.apply)

	/**
		* "e": "aggTrade",  // Event type
  "E": 123456789,   // Event time
  "s": "BNBBTC",    // Symbol
  "a": 12345,       // Aggregate trade ID
  "p": "0.001",     // Price
  "q": "100",       // Quantity
  "f": 100,         // First trade ID
  "l": 105,         // Last trade ID
  "T": 123456785,   // Trade time
  "m": true,        // Is the buyer the market maker?
  "M": true         // Ignore
		*/
	implicit lazy val AggTradeDecoder: Decoder[AggTrade] = Decoder.forProduct5(
		"s",
		"p",
		"q",
		"T",
		"m"
	)(AggTrade.apply)

	/**
	"e": "24hrTicker",  // Event type
    "E": 123456789,     // Event time
    "s": "BNBBTC",      // Symbol
    "p": "0.0015",      // Price change
    "P": "250.00",      // Price change percent
    "w": "0.0018",      // Weighted average price
    "x": "0.0009",      // Previous day's close price
    "c": "0.0025",      // Current day's close price
    "Q": "10",          // Close trade's quantity
    "b": "0.0024",      // Best bid price
    "B": "10",          // Best bid quantity
    "a": "0.0026",      // Best ask price
    "A": "100",         // Best ask quantity
    "o": "0.0010",      // Open price
    "h": "0.0025",      // High price
    "l": "0.0010",      // Low price
    "v": "10000",       // Total traded base asset volume
    "q": "18",          // Total traded quote asset volume
    "O": 0,             // Statistics open time
    "C": 86400000,      // Statistics close time
    "F": 0,             // First trade ID
    "L": 18150,         // Last trade Id
    "n": 18151          // Total number of trades
		*/
	implicit lazy val TickerDecoder: Decoder[Ticker] = Decoder.forProduct9(
		"s",
		"c",
		"o",
		"h",
		"l",
		"v",
		"q",
		"O",
		"C"
	)(Ticker.apply)


//
//	data : {
//		"e": "kline",     // Event type
//		"E": 123456789,   // Event time
//		"s": "BNBBTC",    // Symbol
//		"k": {
//			"t": 123400000, // Kline start time
//			"T": 123460000, // Kline close time
//			"s": "BNBBTC",  // Symbol
//			"i": "1m",      // Interval
//			"f": 100,       // First trade ID
//			"L": 200,       // Last trade ID
//			"o": "0.0010",  // Open price
//			"c": "0.0020",  // Close price
//			"h": "0.0025",  // High price
//			"l": "0.0015",  // Low price
//			"v": "1000",    // Base asset volume
//			"n": 100,       // Number of trades
//			"x": false,     // Is this kline closed?
//			"q": "1.0000",  // Quote asset volume
//			"V": "500",     // Taker buy base asset volume
//			"Q": "0.500",   // Taker buy quote asset volume
//			"B": "123456"   // Ignore
//		}
//	}
	implicit val CandleDecoder: Decoder[Candle] = new Decoder[Candle] {
		final def apply(c: HCursor): Decoder.Result[Candle] = {
			val data = c.downField("data").downField("k")
			for {
				openTime <- data.downField("t").as[Long]
				closeTime <- data.downField("T").as[Long]
				symbol <- data.downField("s").as[String]
				interval <- data.downField("i").as[String]
				open <- data.downField("o").as[Double]
				close <- data.downField("c").as[Double]
				high <- data.downField("h").as[Double]
				low <- data.downField("l").as[Double]
				volume <- data.downField("v").as[Double]
				quoteAssetVolume <- data.downField("q").as[Double]
				numberOfTrades <- data.downField("n").as[Int]
			} yield Candle(symbol, interval, openTime, closeTime, open, close, high, low, volume, quoteAssetVolume, numberOfTrades)
		}
	}
}
