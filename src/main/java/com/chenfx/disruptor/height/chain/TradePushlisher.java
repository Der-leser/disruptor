package com.chenfx.disruptor.height.chain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TradePushlisher implements Runnable {

    private Disruptor<Trade> disruptor;
    private CountDownLatch countDownLatch;

    private static int PUSHBLISH_COUNT = 1;

    public TradePushlisher(Disruptor<Trade> disruptor, CountDownLatch countDownLatch) {
        this.disruptor = disruptor;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        TradeEventTranslate tradeEventTranslate = new TradeEventTranslate();
        for (int i = 0; i < PUSHBLISH_COUNT; i++) {
            //提交任务
            disruptor.publishEvent(tradeEventTranslate);
        }
        countDownLatch.countDown();
    }

}

class TradeEventTranslate implements EventTranslator<Trade> {
    private Random random = new Random();

    @Override
    public void translateTo(Trade trade, long l) {
        this.generateTrade(trade);
    }

    private void generateTrade(Trade event) {
        event.setPrice(random.nextDouble() * 9999);
    }
}