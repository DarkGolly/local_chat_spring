package danya.chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);



	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public OutGoingMessage greeting(InComingMessage message) throws Exception {
		//printSubscribedUsers("/topic/greetings");
		//add session users
		//logger.info(message.getFrom() + ": " + message.getMessage());
		//Thread.sleep(1000); // simulated delay
		return new OutGoingMessage(message.getFrom(), message.getMessage());
	}

	/*@MessageMapping("/hello")
	@SendTo("/topic/connections")
	public String connection(String user) throws Exception {
		//add session users
		// var s = StompSession.Receiptable;
		logger.info(user+" connected");
		//Thread.sleep(1000); // simulated delay
		return new String(message.getName(), message.getMessage());
	}*/



}
