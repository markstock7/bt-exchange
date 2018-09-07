package org.bt.exchangestream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.bt.exchangestream.api.ExchangeStreamService
import org.bt.exchange.api.ExchangeService

import scala.concurrent.Future

/**
  * Implementation of the ExchangeStreamService.
  */
class ExchangeStreamServiceImpl(exchangeService: ExchangeService) extends ExchangeStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(exchangeService.hello(_).invoke()))
  }
}
