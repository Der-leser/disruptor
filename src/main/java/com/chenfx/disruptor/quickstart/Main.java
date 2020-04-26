package com.chenfx.disruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2020/4/25 0025.
 */
public class Main {
    public static void main(String[] args) {

        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int bufferSiez = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //1.实例化一个disruptor对象
        Disruptor<OrderEvent> orderEventDisruptor = new Disruptor<OrderEvent>(
                orderEventFactory,
                bufferSiez,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );
        orderEventDisruptor.handleEventsWith(new OrderEventHandler());
        //启动
        orderEventDisruptor.start();

        //获取实际存储数据容器
        RingBuffer<OrderEvent> ringBuffer = orderEventDisruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer bb = java.nio.ByteBuffer.allocate(8);

        for (long i = 0; i < 100; i ++) {
            bb.putLong(0,i);
            producer.sendData(bb);
        }

        orderEventDisruptor.shutdown();
        executorService.shutdown();
    }
}
