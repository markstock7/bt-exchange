package com.blocktrending.exchange.base

import io.circe.{Decoder, parser}
import okhttp3.ResponseBody
import retrofit2.{Call, Callback, Response}

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Success, Try}

object RunRequest {
	def apply[T: Decoder](call: Call[ResponseBody])(implicit ex: ExecutionContext): Future[T] = {
		val promise = Promise[T]

		call.enqueue(new Callback[ResponseBody]() {

			override def onResponse(call: Call[ResponseBody], response: Response[ResponseBody]): Unit = {
				if (response.isSuccessful) parser.decode[T](response.body.string()) match {
					case Left(error) => promise.failure(error)
					case Right(t) => promise.success(t)
				} else {
					if (response.code == 504) {
						// HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
						// It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
						return
					}

					val errorBodyString = response.errorBody().string()

					Try(parser.decode[ApiBaseError](errorBodyString)) match {
						case Success(Right(parsedError)) => promise.failure(ApiExceptionError(parsedError))
						case _ =>
							promise.failure(
								ApiExceptionMsg(
									s"Failed request: http code: ${response.code}, body: $errorBodyString"
								)
							)
					}
				}
			}

			override def onFailure(call: Call[ResponseBody], t: Throwable): Unit = promise.failure(t)

		})

		promise.future
	}
}