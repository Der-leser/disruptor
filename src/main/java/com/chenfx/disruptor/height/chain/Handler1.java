package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;


public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        System.err.println("handler 1 : SET NAME");
        Thread.sleep(1000);
        trade.setName("H1");
    }
}
