package io.com.invext.outroassuntoservice.config;

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

import io.com.invext.outroassuntoservice.service.AtendenteService;


@Configuration
public class RabbitMqConfig {

	private final AtendenteService service;
	
	private final int MAX_SOL_POR_ATENDENTE = 3;
	
	public RabbitMqConfig(AtendenteService service) {
		this.service = service;
	}

    @Value("${app.rabbitmq.outro.queue}")
    private String outroQueue;

    @Value("${app.rabbitmq.outro.exchange}")
    private String outroExchange;

    @Value("${app.rabbitmq.outro.routing-key}")
    private String outroRoutingKey;
    
    @Bean
    Queue queue() {
    	return new Queue(outroQueue, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(outroExchange);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(outroRoutingKey);
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
