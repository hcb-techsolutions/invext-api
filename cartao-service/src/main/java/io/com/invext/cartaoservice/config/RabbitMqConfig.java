package io.com.invext.cartaoservice.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.com.invext.cartaoservice.service.AtendenteService;


@Configuration
public class RabbitMqConfig {

	private final AtendenteService service;
	
	private final int MAX_SOL_POR_ATENDENTE = 3;
	
	public RabbitMqConfig(AtendenteService service) {
		this.service = service;
	}

	@Value("${app.rabbitmq.cartao.queue}")
	private String cartaoQueue;

    @Value("${app.rabbitmq.cartao.exchange}")
    private String cartaoExchange;

    @Value("${app.rabbitmq.cartao.routing-key}")
    private String cartaoRoutingKey;
    
    @Bean
    Queue queue() {
    	return new Queue(cartaoQueue, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(cartaoExchange);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(cartaoRoutingKey);
    }

	@Bean
	Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = 
        		new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        int qtdAtendente = service.listarAtendente().size();
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setPrefetchCount(MAX_SOL_POR_ATENDENTE*qtdAtendente);
        return factory;
    }
}
