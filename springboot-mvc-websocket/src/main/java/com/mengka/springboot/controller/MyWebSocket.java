package com.mengka.springboot.controller;

import com.mengka.springboot.service.PushTimeService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 *  the Java source code for the WebSocket server endpoint implementation.
 *
 * @author huangyy
 * @version farabbit2.0, 2019/7/17
 * @since farabbit2.0
 */
@ServerEndpoint("/WebSocketServer/endpoint")
public class MyWebSocket {

    private static PushTimeService pst;

    /**
     *  @OnOpen – Called when a client connects
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
    }

    /**
     *  @OnClose – Called when a client connection is closed
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
    }

    /**
     *  @OnMessage – Called when a message is received by the client
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);

        try {
            session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  @OnError – Called when an error for this endpoint occurred
     *
     * @param t
     */
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}