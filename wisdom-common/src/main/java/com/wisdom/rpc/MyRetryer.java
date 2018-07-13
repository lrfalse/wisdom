package com.wisdom.rpc;

import feign.RetryableException;
import feign.Retryer;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MyRetryer implements Retryer {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OkHttpLoggingInterceptor.class);

    private final int maxAttempts;
    private final long period;
    private final long maxPeriod;
    int attempt;
    long sleptForMillis;

    public MyRetryer() {
        this(100L, TimeUnit.SECONDS.toMillis(1L), 5);
    }

    public MyRetryer(long period, long maxPeriod, int maxAttempts) {
        this.period = period;
        this.maxPeriod = maxPeriod;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public void continueOrPropagate(RetryableException e) {
        if (this.attempt++ >= this.maxAttempts) {
            ///打印错误日志
            LOGGER.error(e.getMessage());
            throw e;
        } else {
            long interval;
            if (e.retryAfter() != null) {
                interval = e.retryAfter().getTime() - this.currentTimeMillis();
                if (interval > this.maxPeriod) {
                    interval = this.maxPeriod;
                }

                if (interval < 0L) {
                    return;
                }
            } else {
                interval = this.nextMaxInterval();
            }

            try {
                Thread.sleep(interval);
            } catch (InterruptedException var5) {
                Thread.currentThread().interrupt();
            }

            this.sleptForMillis += interval;
        }
    }

    long nextMaxInterval() {
        long interval = (long) ((double) this.period * Math.pow(1.5D, (double) (this.attempt - 1)));
        return interval > this.maxPeriod ? this.maxPeriod : interval;
    }

    public Retryer clone() {
        return new MyRetryer(this.period, this.maxPeriod, this.maxAttempts);
    }



}