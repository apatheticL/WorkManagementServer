package com.nhatle.workmanagement.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhatle.workmanagement.Constant;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketManager {
    private SocketIOServer socketIOServer;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, SocketIOClient> ioClientMap = new HashMap<>();
    @PostConstruct
    public void init(){
        Configuration configuration = new Configuration();
        String ip = null;

        try {
            ip= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            ip = Constant.IP_SERVER;
        }
        System.out.println("id address" + ip);
        configuration.setHostname(ip);
        configuration.setPort(3036);
        socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println(" onConnect test connect.....");
            }
        });
        socketIOServer.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                System.out.println("onDisconnect test Connect...........");
                for (String s :ioClientMap.keySet()){
                    if (ioClientMap.get(s)==socketIOClient){
                        ioClientMap.remove(s);
                    }
                }
            }
        });
        socketIOServer.addEventListener("connected", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                System.out.println("onData Test connect.........." + s);
                ioClientMap.put(s, socketIOClient);
            }
        });

        socketIOServer.start();
    }
}
