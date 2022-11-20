package com.demovehiclepro.util;

import com.demovehiclepro.service.vehicle.VehicleListDisplay;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new VehicleListDisplay(VehicleListDisplay.VehicleData.getInstance()), "/vehicle-list");
    }
}
