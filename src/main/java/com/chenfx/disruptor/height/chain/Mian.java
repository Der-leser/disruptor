package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
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
                        return new Trade();
                    }
                },
                1024 * 1024,
                es2,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());
        //设置消费者
        //串行操作
//        disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler2()).handleEventsWith(new Handler3());
        //并行操作1
//        disruptor.handleEventsWith(new Handler1());
//        disruptor.handleEventsWith(new Handler2());
//        disruptor.handleEventsWith(new Handler3());
        //并行操作2
//        disruptor.handleEventsWith(new Handler3(),new Handler1());

        //棱形操作1
//        disruptor.handleEventsWith(new Handler1(),new Handler2()).handleEventsWith(new Handler3());
        //棱形操作2
//        EventHandlerGroup eventHandlerGroup = disruptor.handleEventsWith(new Handler1(), new Handler2());
//        eventHandlerGroup.then(new Handler3());
        //六边形操作
        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();
        disruptor.handleEventsWith(h1,h4);
        disruptor.after(h1).handleEventsWith(h2);
        disruptor.after(h4).handleEventsWith(h5);
        disruptor.after(h2,h5).handleEventsWith(h3);

        //启动
        RingBuffer start = disruptor.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        long startTime = System.currentTimeMillis();
        es1.submit(new TradePushlisher(disruptor,countDownLatch));
        countDownLatch.await();
//        disruptor.shutdown();
//        es1.shutdown();
//        es2.shutdown();
        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime));
    }
}
