package org.bt.exchange.binance.json

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import org.bt.exchange.binance.domain.{TickerDaily, _}

object RestDecoders extends Decoders {

	/**
		* [
  [
    1499040000000,      // Open time
    "0.01634790",       // Open
    "0.80000000",       // High
    "0.01575800",       // Low
    "0.01577100",       // Close
    "148976.11427815",  // Volume
    1499644799999,      // Close time
    "2434.19055334",    // Quote asset volume
    308,                // Number of trades
    "1756.87402397",    // Taker buy base asset volume
    "28.46694368",      // Taker buy quote asset volume
    "17928899.62484339" // Ignore
    ]
		]
		*/
	implicit lazy val CandleDecoder: Decoder[Candle] =
		Decoder
			.decodeTuple12[Long, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, Long, BigDecimal, Int, BigDecimal, BigDecimal, String]
			.map {
				case (openTime, open, high, low, close, volume, closeTime, quoteV, trades, bakerByBaseV, takerBuyQuoteV, _) =>
					Candle(openTime, closeTime, open, close, high, low, volume, quoteV, trades)
			}


	/**
	"lastUpdateId": 1027024,
		"bids": [
			[
				"4.00000000",     // PRICE
				"431.00000000",   // QTY
				[]                // Ignore.
			]
		],
      "asks": [
        [
          "4.00000200",
          "12.00000000",
          []
        ]
      ]
		*/
	implicit lazy val DepthDecoder: Decoder[Depth] = Decoder.forProduct2(
		"bids",
		"asks"
	)((bids: List[OrderBookEntry], asks: List[OrderBookEntry]) => Depth("", bids, asks))


	/**
	{
    "a": 26129,         // Aggregate tradeId
    "p": "0.01633102",  // Price
    "q": "4.70443515",  // Quantity
    "f": 27781,         // First tradeId
    "l": 27781,         // Last tradeId
    "T": 1498793709153, // Timestamp
    "m": true,          // Was the buyer the maker?
    "M": true           // Was the trade the best price match?
  }
		*/
	implicit lazy val AggTradeDecoder: Decoder[AggTrade] = Decoder.forProduct3(
		"p",
		"q",
		"T"
	)((price: BigDecimal, quantity: BigDecimal, timestamp: Long) => AggTrade("", price, quantity, timestamp))


	implicit lazy val ExchangeSymbolDecoder:  Decoder[ExchangeSymbol]      = deriveDecoder[ExchangeSymbol]
	implicit lazy val ExchangeInfoDecoder:    Decoder[ExchangeInfo]        = deriveDecoder[ExchangeInfo]
	implicit lazy val ServerTimeDecoder:      Decoder[ServerTime]          = deriveDecoder[ServerTime]
	implicit lazy val TradeDecoder:           Decoder[Trade]               = deriveDecoder[Trade]
	implicit lazy val HistoricalTradeDecoder: Decoder[HistoricalTrade]     = deriveDecoder[HistoricalTrade]
	implicit lazy val TickerDailyDecoder:     Decoder[TickerDaily]         = deriveDecoder[TickerDaily]
	implicit lazy val TickerPriceDecoder:     Decoder[TickerPrice]         = deriveDecoder[TickerPrice]
	implicit lazy val TickerBookDecoder:      Decoder[TickerBook]          = deriveDecoder[TickerBook]

}
