package ru.grobikon.springbootrabbitmqexample.dto

data class Order(
    var orderId: String,
    var name: String,
    var count: Int,
    var price: Double,
)
