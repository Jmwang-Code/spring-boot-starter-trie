package com.cn.jmw.adapter;

import com.cn.jmw.color.ThreadColor;
import com.cn.jmw.entity.DataSource;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.entity.MultiCodeMode;
import com.mongodb.reactivestreams.client.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.sql.SQLException;

/**
 * @author jmw
 * @Description
 * @date 2023年05月06日 9:32
 * @Version 1.0
 */
@Slf4j
public class MongoDbAdapter extends JdbcAdapter {

    private MongoClient mongoClient;

    private MongoCollection<Document> documentMongoCollection;

    public MongoDbAdapter(DataSource dataSource, Tire trieNode) {
        super(dataSource, trieNode);
        this.documentMongoCollection = connectMongoDB();
    }

    //JDBC连接MongoDB
    public MongoCollection<Document> connectMongoDB() {
        MongoCollection<Document> collection = null;
        try {

//            mongodb://Mongo:123456@152.136.154.249:27017/Mongo
            mongoClient = MongoClients.create(dataSource.getUrl());

            // 获取MongoDB数据库
            MongoDatabase db = mongoClient.getDatabase(dataSource.getUrl().split("/")[3]);

            // 获取MongoDB集合
            collection =  db.getCollection(dataSource.getSqlCode().get(0).getSql().split("from")[1].split(" ")[1]);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return collection;
    }

    @Override
    public Boolean streamingRead() {
        try {
//            MongoCollection<Document> documentMongoCollection = connectMongoDB();
            FindPublisher<Document> documentFindPublisher = documentMongoCollection.find().batchSize(1000);
            documentFindPublisher.subscribe(new Subscriber<Document>() {

                private Subscription subscription;

                /**
                 * Reactive Streams 的规范中定义了一个 Subscription 接口，
                 * 它是用来控制 Publisher 和 Subscriber 之间的数据流的。
                 * Subscription 有两个方法：request(long n) 和 cancel()。
                 * request(long n) 方法是用来告诉 Publisher Subscriber 能够处理多少个数据，
                 * 也就是请求的数量。cancel() 方法是用来取消订阅的，
                 * 也就是告诉 Publisher 不再发送数据给 Subscriber。
                 */
                @Override
                public void onSubscribe(Subscription s) {
                    this.subscription = s;
//                    s.request(Long.MAX_VALUE);
                    s.request(1);
                }

                @Override
                public void onNext(Document document) {
                    //select 判断
                    if (dataSource.getSqlCode().get(0).getSql().contains("*")) {
                        //select *
                        String str = dataSource.getSqlCode().get(0).getStr();
                        String name = (String) document.get(str);
                        log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName() + "MongoDB流式获取成功： " + name));
                        //TODO 注入前缀树
                        trieNode.add(name, MultiCodeMode.Drop);
                    }
                    subscription.request(1);
                }

                @Override
                public void onError(Throwable t) {
                    log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName() + "MongoDB流式获取失败"));
                }

                @Override
                public void onComplete() {
                    log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName() + "MongoDB流式获取完成"));
                    if (mongoClient != null) {
                        mongoClient.close();
                    }
                }
            });
        } catch (Exception e) {
            log.error(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName() + "MongoDB流式获取失败"), e);
            return false;
        }
        return true;
    }


}
