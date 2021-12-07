package ru.grobikon.springbootrabbitmqexample.publisher

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.*
import ru.grobikon.springbootrabbitmqexample.constant.EXCHANGE
import ru.grobikon.springbootrabbitmqexample.constant.ROUTING_KEY
import ru.grobikon.springbootrabbitmqexample.dto.Order
import ru.grobikon.springbootrabbitmqexample.dto.OrderStatus
import java.util.*

/**
 * Создатель заказов
 */
@RestController
@RequestMapping("/order")
class OrderPublisher(
    val rabbitTemplate: RabbitTemplate
) {

    @PostMapping("/{restaurantName}")
    fun bookOrder(@RequestBody order: Order, @PathVariable restaurantName: String): String {
        order.orderId = UUID.randomUUID().toString()        //устанавливаем идентификатор заказа
        //Далее могут быть выполнены седующие действия:
            //1 отправиться restaurant service, выполняться Бизнес логика
            //2 отправиться в платёжную службу payment service, выполняться логика оплаты
        //Но мы не хотим, чтобы пользователь ждал так долга пока будут выполняться эти сервисы 1 и 2
        //Мы будем отправлять пользователю сообщение прогресс - progress
        val orderStatic = OrderStatus(order, "Обрабатывается", "заказ успешно размещен в $restaurantName")
        //теперь orderStatic отправим в очередь RabbitMQ
        //и это сообщение получит очереди которые связаны с EXCHANGE по ключу ROUTING_KEY
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, orderStatic)
        return "Success!!!"
    }
}