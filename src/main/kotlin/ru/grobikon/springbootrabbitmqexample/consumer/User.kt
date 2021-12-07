package ru.grobikon.springbootrabbitmqexample.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.grobikon.springbootrabbitmqexample.constant.QUEUE
import ru.grobikon.springbootrabbitmqexample.dto.OrderStatus

@Component
class User {

    /**
     * Получаем сообщение из очереди RabbitMQ
     * queues = [QUEUE] - очередь из которой будем получать сообщение
     * после получения сообщения из очереди это сообщение будет удалено в очереди
     */
    @RabbitListener(queues = [QUEUE])
    fun consumeMessageFromQueue(orderStatus: OrderStatus) {
        println("Сообщение получено из очереди RabbitMQ: ${orderStatus.message}")
    }
}