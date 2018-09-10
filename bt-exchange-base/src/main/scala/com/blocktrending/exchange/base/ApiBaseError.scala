package com.blocktrending.exchange.base

case class ApiBaseError(
	/**
		* Error code.
		*/
	code: Int,
	/**
		* Error message.
		*/
	msg: String
)

object ApiBaseError {
	import io.circe._

	implicit lazy val ApiErrorDecoder: Decoder[ApiBaseError] = Decoder.forProduct2("code", "msg")(ApiBaseError.apply)
}

final case class ApiExceptionError[E <: ApiBaseError](error: E)     extends RuntimeException(error.msg)
final case class ApiExceptionMsg(value: String)                     extends RuntimeException(value)
final case class ApiExceptionCause(cause: Throwable)                extends RuntimeException(cause)
