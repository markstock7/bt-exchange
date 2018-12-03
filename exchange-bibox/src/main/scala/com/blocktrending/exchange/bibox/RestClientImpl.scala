package com.blocktrending.exchange.bibox

import com.blocktrending.exchange.base.{IAsyncRestClient, Status}
import com.blocktrending.exchange.base.Status.Status
import com.blocktrending.exchange.base.domain._
import com.blocktrending.exchange.bibox.domain.CandlestickInterval.CandlestickInterval
import com.blocktrending.exchange.bibox.domain._
import com.blocktrending.exchange.bibox.json.RestDecoders._
import com.blocktrending.util.http.RunRequest

import scala.concurrent.{ExecutionContext, Future}

class RestClientImpl(service: RestApiService)(implicit ex: ExecutionContext)
    extends IAsyncRestClient {

  def ping: Future[Status] =
    RunRequest
      .apply1[ServerTimeResponse](
      service.ping
    )
      .map(_ => Status.OK)
      .recover {
        case _ => Status.NON_RESPONSE
      }

  override def symbols: Future[Seq[NestedSymbol]] =
    RunRequest.apply1[PairResponse](
      service.pairList
    ).map(_.result)

  def candles(pair: String, period: CandlestickInterval, size: Int): Future[Seq[Candle]] =
    RunRequest.apply1[CandleRepsonse](
      service.candlesWithPair(pair, "30min", size)
    ).map(_.result).map(candles => candles.map(_.copy(symbol = pair, interval = period.toString)))
}
