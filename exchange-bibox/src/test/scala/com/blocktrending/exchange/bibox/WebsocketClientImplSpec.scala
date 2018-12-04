package com.blocktrending.exchange.bibox

import okhttp3.{Response, WebSocket}
import org.scalatest._

import scala.concurrent.{Future, Promise}

//import scala.concurrent.{Future, Promise}

class WebsocketClientImplSpec extends AsyncFlatSpec with Matchers {

  "onAllCandleUpdateEvent" should "Working Well" in {

    val client = new WebsocketClientImpl {
      override def handleClosed(webSocket: WebSocket, code: Int, reason: String): Unit = {
        println(reason)
      }

      override def handleClosing(webSocket: WebSocket, code: Int, reason: String): Unit = {
        println(reason)
      }
    }

    val p = Promise[Assertion]()
    Future {
      client.onCandleUpdateEvent(Seq("BTC_USDT"), "1min") {
        case Left(e) =>
          client.close()
          p.success(assert(false))
        case Right(payload) =>
          client.close()
          p.success(assert(true))
      }
    }
    p.future
  }
}
