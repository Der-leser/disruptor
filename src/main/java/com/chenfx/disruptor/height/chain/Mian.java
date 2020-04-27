package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mian {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es1 = Executors.newFixedThreadPool(1);
        ExecutorService es2 = Executors.newFixedThreadPool(5);
        Disruptor disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
                    @Override
                    public Trade newInstance() {
                        return null;
                    }
                },
                1024 * 1024,
                es2,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());
        //设置消费者
        //启动
        RingBuffer start = disruptor.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        long startTime = System.currentTimeMillis();
        es1.submit(new TradePushlisher(disruptor,countDownLatch));
        countDownLatch.await();
        disruptor.shutdown();
        es1.shutdown();
        es2.shutdown();
        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime));
    }
}
