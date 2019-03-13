package com.sell.order.message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqReceiver {

//	@RabbitListener(
//		bindings = @QueueBinding(
//			value = @Queue("myQueue"),
//			exchange = @Exchange("order"),
//			key = "friut"
//		)
//	)
	public void process(String message) {
		log.info("Receive message：{}", message);
	}

}
