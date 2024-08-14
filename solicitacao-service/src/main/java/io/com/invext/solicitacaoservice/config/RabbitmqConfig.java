package io.com.invext.solicitacaoservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${app.rabbitmq.cartao.exchange}")
    private String cartaoExchange;

    @Value("${app.rabbitmq.cartao.queue}")
    private String cartaoQueue;

    @Value("${app.rabbitmq.cartao.routing-key}")
    private String cartaoRoutingKey;

    @Value("${app.rabbitmq.emprestimo.exchange}")
    private String emprestimoExchange;

    @Value("${app.rabbitmq.emprestimo.queue}")
    private String emprestimoQueue;

    @Value("${app.rabbitmq.emprestimo.routing-key}")
    private String emprestimoRoutingKey;

    @Value("${app.rabbitmq.outro.exchange}")
    private String outroExchange;

    @Value("${app.rabbitmq.outro.queue}")
    private String outroQueue;

    @Value("${app.rabbitmq.outro.routing-key}")
    private String outroRoutingKey;

    @Bean
    TopicExchange cartaoExchange() {
        return new TopicExchange(cartaoExchange);
    }
    
    @Bean
    Queue cartaoQueue() {
    	return new Queue(cartaoQueue);
    }

    @Bean
    Binding cartaoBinding() {
        return BindingBuilder.bind(cartaoQueue()).to(cartaoExchange()).with(cartaoRoutingKey);
    }

    @Bean
    TopicExchange emprestimoExchange() {
        return new TopicExchange(emprestimoExchange);
    }
    
    @Bean
    Queue emprestimoQueue() {
    	return new Queue(emprestimoQueue);
    }

    @Bean
    Binding emprestimoBinding() {
        return BindingBuilder.bind(emprestimoQueue()).to(emprestimoExchange()).with(emprestimoRoutingKey);
    }

    @Bean
    TopicExchange outroExchange() {
        return new TopicExchange(outroExchange);
    }
    
    @Bean
    Queue outroQueue() {
    	return new Queue(outroQueue);
    }

    @Bean
    Binding outroBinding() {
        return BindingBuilder.bind(outroQueue()).to(outroExchange()).with(outroRoutingKey);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
