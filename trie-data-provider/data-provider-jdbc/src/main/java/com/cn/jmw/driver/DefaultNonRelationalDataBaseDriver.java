package com.cn.jmw.driver;

import com.cn.jmw.color.ThreadColor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年05月06日 14:35
 * @Version 1.0
 */
@Slf4j
public class DefaultNonRelationalDataBaseDriver {

    public DefaultNonRelationalDataBaseDriver(){
        log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——创建默认非关系型数据库驱动"));
    }
}
