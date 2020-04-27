package com.chenfx.disruptor.height.chain;


import com.lmax.disruptor.EventHandler;

public class Handler3 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.err.println("handler 3 : NAME: " + trade.getName() + ",ID " + trade.getId() + "price:" + trade.getPrice() + " INSTANCE:" + trade.toString());
    }
}
