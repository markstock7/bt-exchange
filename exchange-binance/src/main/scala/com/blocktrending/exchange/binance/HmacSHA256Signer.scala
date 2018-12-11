package com.blocktrending.exchange.binance

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import org.apache.commons.codec.binary.Hex

/**
	* Utility class to sign messages using HMAC-SHA256.
	*/
object HmacSHA256Signer {

	/**
		* Sign the given message using the given secret.
		*
		* @param message message to sign
		* @param secret  secret key
		* @return a signed message
		*/
	def apply(message: String, secret: String): String = {
		val sha256_HMAC   = Mac.getInstance("HmacSHA256")
		val secretKeySpec = new SecretKeySpec(secret.getBytes, "HmacSHA256")
		sha256_HMAC.init(secretKeySpec)
		new String(Hex.encodeHex(sha256_HMAC.doFinal(message.getBytes)))
	}
}
