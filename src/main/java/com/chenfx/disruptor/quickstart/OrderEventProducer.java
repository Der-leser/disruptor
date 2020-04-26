package com.chenfx.disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2020/4/25 0025.
 */
public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer data) {
        //1.生产者发送消息的时候，先从ringBuffer里面获取一个可用的序号
        long sequence = ringBuffer.next();
        //根据序号，找到具体的“OrderEvent”，这个OrderEvent是一个没有被赋值的“空对象”
        OrderEvent orderEvent = ringBuffer.get(sequence);
        //进行赋值
        orderEvent.setValue(data.getLong(0));
        //提交发布操作
        ringBuffer.publish(sequence);
    }
}
