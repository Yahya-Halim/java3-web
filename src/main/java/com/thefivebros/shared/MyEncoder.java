package com.thefivebros.shared;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class MyEncoder implements Encoder.Text<MyJson> {
    @Override
    public String encode(MyJson object) throws EncodeException {
        return object.toString();
    }
}
