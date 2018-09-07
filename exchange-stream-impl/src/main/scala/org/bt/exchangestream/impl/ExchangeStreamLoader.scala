package org.bt.exchangestream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import org.bt.exchangestream.api.ExchangeStreamService
import org.bt.exchange.api.ExchangeService
import com.softwaremill.macwire._

class ExchangeStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new ExchangeStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new ExchangeStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[ExchangeStreamService])
}

abstract class ExchangeStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[ExchangeStreamService](wire[ExchangeStreamServiceImpl])

  // Bind the ExchangeService client
  lazy val exchangeService = serviceClient.implement[ExchangeService]
}
