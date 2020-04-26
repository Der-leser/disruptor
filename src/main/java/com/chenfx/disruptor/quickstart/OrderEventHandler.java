package com.chenfx.disruptor.quickstart;

import com.lmax.disruptor.EventHandler;

/**
 * Created by Administrator on 2020/4/25 0025.
 */
public class OrderEventHandler implements EventHandler<OrderEvent>{

    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        System.err.println("消费者：" + orderEvent.getValue());
    }
}
