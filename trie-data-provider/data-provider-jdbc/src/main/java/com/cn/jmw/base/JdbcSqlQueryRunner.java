package com.cn.jmw.base;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月05日 17:59
 * @Version 1.0
 */
public class JdbcSqlQueryRunner extends QueryRunner {

    @Override
    protected PreparedStatement prepareStatement(Connection conn, String sql)
            throws SQLException {
        /**
         * 这里我提供一定的注释，原生JDBC API这套参数使用情况不多：
         * 第一个参数：resultSetType参数表示ResultSet对象的类型，
         * ResultSet.TYPE_FORWARD_ONLY ： 游标只能在结果集中向前移动
         *
         * 第二个参数resultSetConcurrency参数指定结果集是只读还是可更新。
         * ResultSet.CONCUR_READ_ONLY|创建只读结果集，这是默认值。
         *
         * 第三个参数
         * ResultSet.CLOSE_CURSORS_AT_COMMIT 这里是需要配合事务使用
         * 首先需要JDBC连接需要打开事务，在提交事务的时候，会自动关闭当前事务创建的ResultSet
         *
         */
        PreparedStatement ps = conn.prepareStatement(sql,
                ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
        /**
         * Fetch相当于读缓存，默认Fetch Size值是10，读取10000条记录,总计需要有1000次与DB的交互
         * 这里设计为1000，也就是说一次从数据库查1000条数据，保存在ResultSet中，需要防止OOM
         */
        ps.setFetchSize(1000);
        /**
         * 数据正向保存，从头到尾
         * The constant indicating that the rows in a result set will be processed in a forward direction; first-to-last.
         */
        ps.setFetchDirection(ResultSet.FETCH_FORWARD);
        return ps;
    }
}
