package com.cn.jmw.result;

import com.cn.jmw.entity.DataSource;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月05日 18:12
 * @Version 1.0
 */
public interface TrieResultSetHandler extends ResultSetHandler<Long> {

    /**
     * 结果集处理
     *
     * @param rs 结果集
     * @return 处理的结果集件数
     */
    public Long handle(ResultSet rs) throws SQLException;
}
