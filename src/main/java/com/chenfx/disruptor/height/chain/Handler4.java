package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.EventHandler;

public class Handler4 implements EventHandler<Trade>{
    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.err.println("handler 4 : SET PRICE");
        trade.setPrice(17.0);
    }
}
