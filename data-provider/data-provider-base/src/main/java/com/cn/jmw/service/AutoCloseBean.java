package com.cn.jmw.service;

import javax.management.timer.Timer;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月06日 10:03
 * @Version 1.0
 */
public abstract class AutoCloseBean extends Timer implements Closeable {

    public AutoCloseBean() {
        start();
        initTimer();
    }

    /**
     * 超时时间
     *
     * @return
     */
    private void initTimer() {
        addNotificationListener((notification, handback) -> {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, null, null);
        addNewNotification();
    }

    /**
     * @return 超时关闭的时间，单位：毫秒
     */
    public abstract int timeoutMillis();

    private void addNewNotification() {
        addNotification("close", "time to close", this, new Date(System.currentTimeMillis() + timeoutMillis()));
    }

}
