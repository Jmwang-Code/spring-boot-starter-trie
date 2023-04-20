package com.cn.jmw.adapter;

import com.cn.jmw.AdapterEnum;
import com.cn.jmw.color.ThreadColor;
import com.cn.jmw.entity.DataSource;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jmw
 * @date 2023年04月10日 18:07
 * @Version 1.0
 */
@Slf4j
public class AdapterFactory {
    //这里快速创建一个适配器，根据数据源类型，创建对应的适配器
    public static Adapter createDataAdapter(DataSource dataSource) {
        AdapterEnum adapterEnum = AdapterEnum.getAdapterEnum(dataSource.getType());
        log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——创建适配器:"+adapterEnum));
        try {
            Class<?> aClass = Class.forName(adapterEnum.getClassName());
            Object jdbcAdapter = aClass.getDeclaredConstructor().newInstance();
            JdbcAdapter jd = jdbcAdapter instanceof Adapter ? (JdbcAdapter) jdbcAdapter : null;
            jd.init(dataSource);
            return jd;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
