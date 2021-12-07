package ru.grobikon.springbootrabbitmqexample.dto

data class OrderStatus(
    var orser: Order,
    var status: String, //прогресс - progress, выполнено - completed
    var message: String
)
