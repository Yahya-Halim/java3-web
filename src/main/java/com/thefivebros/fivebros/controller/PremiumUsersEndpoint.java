package com.thefivebros.fivebros.controller;


import com.thefivebros.shared.MyDecoder;
import com.thefivebros.shared.MyEncoder;
import com.thefivebros.shared.MyJson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ServerEndpoint(
        value = "/chat/endpoint",
        encoders = {MyEncoder.class},
        decoders = {MyDecoder.class}
)
public class PremiumUsersEndpoint {
//     Set will eliminate duplicate Sessions
//     HashSet means the Sessions are added in no particular order
    private static final Set<Session> subscribers = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        subscribers.add(session);
        System.out.println("Subscriber count: " + subscribers.size());
    }
    @OnMessage
    public void onMessage(MyJson myJson, Session session) throws EncodeException, IOException {
        // session refers to the user who is sending the message
//        System.out.println(myJson);
        for(Session s : subscribers) {
            if(!s.equals(session)) {
                // deliver the message to all other subscribers
                s.getBasicRemote().sendObject(myJson);
            }
        }
    }
    @OnClose
    public void onClose(Session session) {
        subscribers.remove(session);
//        System.out.println("Subscriber count: " + subscribers.size());

    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("ERROR: " + error.getMessage());

    }
}
