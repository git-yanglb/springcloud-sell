package com.sell.order.message;

import java.util.Date;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sell.order.AppTest;

@Component
public class MqReceiverTest extends AppTest {

	@Autowired
	private AmqpTemplate amqpTemplate;

	// @Autowired RabbitTemplate rabbitTemplate;

	@Test
	public void test() {
		amqpTemplate.convertAndSend("order", "friut", "now：" + new Date());
	}

}
