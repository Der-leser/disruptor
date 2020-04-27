package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.EventHandler;

public class Handler5 implements EventHandler<Trade>{
    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.err.println("handler 5 : GET PRICE:" + trade.getPrice());
        trade.setPrice(trade.getPrice() + 3.0);
    }
}
