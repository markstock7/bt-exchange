package com.blocktrending.exchange.huobipro.domain

object CandlestickInterval extends Enumeration {
  type CandlestickInterval = Value

  val ONE_MINUTE = Value("1min")
  val FIVE_MINUTES = Value("5min")
  val FIFTEEN_MINUTES = Value("15min")
  val HALF_HOURLY = Value("30min")
  val HOURLY = Value("60min")
//  val THREE_HOURLY = Value("3h")
//  val SIX_HOURLY = Value("6h")
//  val TWELEVE_HOURLY = Value("12h")
  val DAILY = Value("1day")
  val WEEKLY = Value("1week")
//  val FOURTEEN_DAYS = Value("14D")
  val MONTHLY = Value("1mon")
  val YEARLY = Value("1year")

  def interval2Period(interval: CandlestickInterval): Long = {
    interval match {
      case ONE_MINUTE      => 60 * 1000
      case FIVE_MINUTES    => 5 * 60 * 1000
      case FIFTEEN_MINUTES => 15 * 60 * 1000
      case HALF_HOURLY     => 30 * 60 * 1000
      case HOURLY          => 60 * 60 * 1000
//      case THREE_HOURLY    => 3 * 60 * 60 * 1000
//      case SIX_HOURLY      => 6 * 60 * 60 * 1000
//      case TWELEVE_HOURLY  => 12 * 60 * 60 * 1000
      case DAILY  => 24 * 60 * 60 * 1000
      case WEEKLY => 7 * 24 * 60 * 60 * 1000
//      case FOURTEEN_DAYS   => 14 * 24 * 60 * 60 * 1000
      case MONTHLY => 30 * 24 * 60 * 60 * 1000
      case YEARLY  => 365 * 24 * 60 * 60 * 1000
    }
  }
}
