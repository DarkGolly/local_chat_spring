package danya.chatclient.controllers;

import danya.chatclient.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

@Component
@FxmlView("main.fxml")
public class MainController extends StompSessionHandlerAdapter{

    @FXML
    private TextField ipField;

    private String IP_ADDR = "localhost";
    private StompSession session;
    @FXML
    private TextArea log;
    @FXML
    private TextField name;
    private String nick = "Steve";
    @FXML
    private TextField text;

    @FXML
    void onSendClick(MouseEvent event) {
        try {
            String msg = text.getText();
            if (msg.equals("")) return;
            text.setText(null);
            session.send("/topic/messages", new InComingMessage(nick, msg));
        }catch (Exception e){
            shotMsg(e.toString());
        }
    }
    @FXML
    void onSetName(MouseEvent event) {
        nick = name.getText();
    }
    @FXML
    void onSetIp(MouseEvent event){
        log.clear();
        try {
            IP_ADDR = ipField.getText();
            WebSocketClient client = new StandardWebSocketClient();

            WebSocketStompClient webSocketStompClient = new WebSocketStompClient(client);
            webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
            StompSessionHandlerAdapter stompSessionHandlerAdapter = new StompSessionHandlerAdapter() {
                @Override
                public Type getPayloadType(StompHeaders headers){
                    return OutGoingMessage.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload){
                    shotMsg(((OutGoingMessage)payload).getFrom()+": "+ ((OutGoingMessage)payload).getMessage());
                    System.out.println(((OutGoingMessage)payload).getFrom()+": "+ ((OutGoingMessage)payload).getMessage());
                }
            };
            ListenableFuture<StompSession> sessionAsinc =
                    webSocketStompClient.connect("ws://"+IP_ADDR+":8080/websocket",stompSessionHandlerAdapter);
            session = sessionAsinc.get();
            session.subscribe("/topic/messages",stompSessionHandlerAdapter);
        }catch (Exception e) {
            shotMsg(e.toString());
        }
    }
    public void shotMsg(String msg){
        log.setText(log.getText()+msg+"\n");
    }
    @FXML
    void initialize()  {
    }
}
