package ru.grobikon.springbootrabbitmqexample.dto

data class Order(
    var orderId: String? = null,
    var name: String? = null,
    var count: Int? = null,
    var price: Double? = null,
)
