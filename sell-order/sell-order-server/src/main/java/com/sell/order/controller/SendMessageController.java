package com.sell.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sell.order.dto.OrderDTO;
import com.sell.order.message.StreamClient;

@RestController
public class SendMessageController {

	@Autowired
	private StreamClient streamClient;

	@GetMapping("/sendMessage")
	public void process() {
		OrderDTO order = new OrderDTO();
		order.setOrderId("123456");
		streamClient.output().send(MessageBuilder.withPayload(order).build());
	}

}
