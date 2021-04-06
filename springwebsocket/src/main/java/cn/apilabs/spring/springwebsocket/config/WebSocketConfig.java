package cn.apilabs.spring.springwebsocket.config;

import cn.apilabs.spring.springwebsocket.handle.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/30
 * @className WebSocketConfig
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/mh")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        registry.addHandler(myWebSocketHandler(), "mh2")
                .setAllowedOrigins("*");
    }

    @Bean
    public MyWebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler();
    }
}
