package LifeManager.WebServer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class WebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

	@Bean
    public Queue queueHB() {
        return new Queue("heart_beat",true, false, false);
    }

    @Bean
    public Queue queueBT() {
        return new Queue("body_temp",true, false, false);
    }

    @Bean
    public Queue queueSL() {
        return new Queue("sugar_level",false, false, false);
    }
	@Bean
    public Queue queueOL() {
        return new Queue("oxygen_level",true, false, false);
    }

    @Bean
    public Queue queueBP() {
     	return new Queue("blood_pressure",false, false, false);
    }
//------------------------------------------------------------------------------------------------------

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("logs");
	}

//------------------------------------------------------------------------------------------------------
	@Bean
    public Binding bindingHB() {
        return BindingBuilder.bind(queueHB()).to(exchange()).with("heartbeat");
    }

    @Bean
    public Binding bindingBT() {
        return BindingBuilder.bind(queueBT()).to(exchange()).with("temperature");
    }

    @Bean
    public Binding bindingBP() {
        return BindingBuilder.bind(queueBP()).to(exchange()).with("blood_pressure");
    }
    @Bean
    public Binding bindingSL() {
        return BindingBuilder.bind(queueSL()).to(exchange()).with("sugar_level");
    }
    @Bean
    public Binding bindingOL() {
        return BindingBuilder.bind(queueOL()).to(exchange()).with("oxygen_level");
    }


//------------------------------------------------------------------------------------------------------
	@Bean
	public MessageBroker receiver() {
		return new MessageBroker();
	}

}
