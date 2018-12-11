package com.blocktrending.exchange.binance.domain.account

import com.blocktrending.exchange.binance.domain.OrderSide.OrderSide
import com.blocktrending.exchange.binance.domain.OrderStatus.OrderStatus
import com.blocktrending.exchange.binance.domain.OrderType.OrderType


/**
  * Trade order information.
  */
case class Order(
    /**
      * Symbol that the order was put on.
      */
    symbol: String,
    /**
      * Order id.
      */
    orderId: Long,

    /**
      * Price.
      */
    price: BigDecimal,
    /**
      * Original quantity.
      */
    origQty:     BigDecimal,

    executedQty: BigDecimal,
    /**
      * Order status.
      */
    status: OrderStatus,
    /**
      * Type of order.
      */
    `type`: OrderType,
    /**
      * Buy/Sell order side.
      */
    side: OrderSide
)