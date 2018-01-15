package com.sandao.websocket;

import com.sandao.service.impl.SystemWebSocketHandler;
import com.sandao.web.WebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by maoyanting on 2017/11/28.
 *
 * @author sandoa
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private SystemWebSocketHandler systemWebSocketHandler;
    @Autowired
    private WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;

    /**
     * 注册实现类，设置访问WebSocket的地址
     * 注册拦截器
     *
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(systemWebSocketHandler, "/ws")
                .addInterceptors(webSocketHandshakeInterceptor).setAllowedOrigins("http://localhost:8000");
    }
}
