package ru.grobikon.springbootrabbitmqexample.dto

data class OrderStatus(
    var orser: Order? = null,
    var status: String? = null, //прогресс - progress, выполнено - completed
    var message: String? = null
)
