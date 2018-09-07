package org.bt.exchange.impl

import org.bt.exchange.api
import org.bt.exchange.api.{ExchangeService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the ExchangeService.
  */
class ExchangeServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends ExchangeService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the Exchange entity for the given ID.
    val ref = persistentEntityRegistry.refFor[ExchangeEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the Exchange entity for the given ID.
    val ref = persistentEntityRegistry.refFor[ExchangeEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }


  override def greetingsTopic(): Topic[api.GreetingMessageChanged] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream(ExchangeEvent.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent(helloEvent: EventStreamElement[ExchangeEvent]): api.GreetingMessageChanged = {
    helloEvent.event match {
      case GreetingMessageChanged(msg) => api.GreetingMessageChanged(helloEvent.entityId, msg)
    }
  }
}
