package ru.grobikon.springbootrabbitmqexample.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.grobikon.springbootrabbitmqexample.constant.*


@Configuration
class MessagingConfig {

    companion object{
        const val QUEUE1 = "test"
    }

    /**
     * Очередь
     */
    @Bean
    fun queue(): Queue {
        return Queue(QUEUE)
    }

    /**
     * Обменник
     */
    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(EXCHANGE)
    }

    /**
     * Связывание exchange и queue.
     * grobikon_routingKey - это ключ маршрутизации, который связывает обменник и очередь
     * @param queue очередь
     * @param exchange обменник
     */
    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)
    }

    /**
     * Преобразователь сообщений
     */
    @Bean
    fun convector(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    /**
     * Шаблон RabbitMQ - с помощью него можем опубликовать событие или можем опубликовать сообщение.
     *                   И можем его использовать.
     *
     * Создайте шаблон кролика со стратегиями и настройками по умолчанию.
     *  Параметры:
     *  connectionFactory - фабрика соединений для использования
     */
    @Bean
    fun template(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        //Преобразователь сообщений
        rabbitTemplate.messageConverter = convector()
        return rabbitTemplate
    }
}