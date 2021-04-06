package cn.apilabs.spring.springwebsocket.handle;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.NativeWebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/30
 * @className MyWebSocketHandler
 */
public class MyWebSocketHandler extends TextWebSocketHandler {

    /**
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        session.sendMessage(new TextMessage("你好"));
        System.out.println(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final URI uri = session.getUri();
        if (Objects.nonNull(uri)) {
            final String query = uri.getQuery();
            System.out.println(query);
        }

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage("我收到了"));
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


