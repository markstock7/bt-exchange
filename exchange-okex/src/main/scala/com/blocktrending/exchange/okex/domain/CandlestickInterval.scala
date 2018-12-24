package com.blocktrending.exchange.okex.domain

object CandlestickInterval extends Enumeration {
  type CandlestickInterval = Value

  val ONE_MINUTE = Value("60")
  val THREE_MINUTES = Value("180")
  val FIVE_MINUTES = Value("300")
  val FIFTEEN_MINUTES = Value("900")
  val HALF_HOURLY = Value("1800")
  val HOURLY = Value("3600")
  val TWO_HOURLY = Value("7200")
  val FOUR_HOURLY = Value("14400")
  val SIX_HOURLY = Value("21600")
  val TWELEVE_HOURLY = Value("43200")
  val DAILY = Value("86400")
  val WEEKLY = Value("604800")

  def interval2Period(interval: CandlestickInterval): Long = {
    interval match {
      case ONE_MINUTE      => 60 * 1000
      case THREE_MINUTES   => 3 * 60 * 1000
      case FIFTEEN_MINUTES => 15 * 60 * 1000
      case HALF_HOURLY     => 30 * 60 * 1000
      case HOURLY          => 60 * 60 * 1000
      case SIX_HOURLY      => 6 * 60 * 60 * 1000
      case TWELEVE_HOURLY  => 12 * 60 * 60 * 1000
      case DAILY           => 24 * 60 * 60 * 1000
      case WEEKLY          => 7 * 24 * 60 * 60 * 1000
    }
  }
}
