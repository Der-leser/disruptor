package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;


public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long l, boolean b) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        System.err.println("handler 1 : SET NAME");
        event.setName("H1");
        Thread.sleep(1000);
    }
}
