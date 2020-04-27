package com.chenfx.disruptor.height.chain;


import com.lmax.disruptor.EventHandler;

import java.util.UUID;

public class Handler2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.err.println("handler2:SET ID");
        trade.setId(UUID.randomUUID().toString());
    }
}
