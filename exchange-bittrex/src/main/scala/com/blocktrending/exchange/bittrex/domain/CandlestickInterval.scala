package com.blocktrending.exchange.bittrex.domain

object CandlestickInterval extends Enumeration {
  type CandlestickInterval = Value

  val ONE_MINUTE = Value("oneMin")
  val FIVE_MINUTES = Value("fiveMin")
  val HALF_HOURLY = Value("thirtyMin")
  val HOURLY = Value("hour")
  val DAILY = Value("day")

  def interval2Period(interval: CandlestickInterval): Long = {
    interval match {
      case ONE_MINUTE => 60 * 1000
      case FIVE_MINUTES => 5 * 60 * 1000
      case HALF_HOURLY => 30 * 60 * 1000
      case HOURLY => 60 * 60 * 1000
      case DAILY => 24 * 60 * 60 * 1000
    }
  }
}
