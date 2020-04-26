package com.chenfx.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Administrator on 2020/4/25 0025.
 */
public class OrderEventFactory implements EventFactory<OrderEvent>{

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();    //这个方法就是为了返回空对象（Event）
    }
}
