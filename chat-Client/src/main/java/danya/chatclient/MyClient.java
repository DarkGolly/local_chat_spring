package danya.chatclient;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Component
public class MyClient {


    //private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(client);
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ClientOneSessionHandler clientOneSessionHandler = new ClientOneSessionHandler();
        ListenableFuture<StompSession> sessionAsinc =
                webSocketStompClient.connect("ws://localhost:8080/gs-guide-websocket",clientOneSessionHandler);
        StompSession session = sessionAsinc.get();
        session.subscribe("/topic/greetings",clientOneSessionHandler);
        //String name = in.nextLine();
        while (true){
            session.send("/app/hello", new InComingMessage("g","name"));
        }
    }

}

class ClientOneSessionHandler extends StompSessionHandlerAdapter {
    @Override
    public Type getPayloadType(StompHeaders headers){
        return OutGoingMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload){
        System.out.println("Отправлено : "+ ((OutGoingMessage)payload).getMessage());
    }
}


