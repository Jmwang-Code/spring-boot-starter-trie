package com.cn.jmw.loading.lo;

import com.cn.jmw.JdbcProvider;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.TrieNode;
import org.springframework.stereotype.Component;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public class LoadTireServiceImpl<T,R> implements LoadTireService{

    private ProviderEntity providerEntity;

    private Tire trieNode;

    public LoadTireServiceImpl(ProviderEntity providerEntity,Tire trieNode){
        this.providerEntity = providerEntity;
        this.trieNode = trieNode;
    }

    //TODO 全部改成工厂方法创建，通过TYEP 比如JDBC ES 等等
    @Override
    public void loadConsumer(final ConsumerHandler handler) {
        handler.handle(new JdbcProvider(trieNode));
    }

    @Override
    public Object loadFunction(FunctionHandler handler) {
        return handler.handle(new JdbcProvider(trieNode));
    }

    @Override
    public boolean loadPredicate(final PredicateHandler handler) {
        return handler.handle(new JdbcProvider(trieNode));
    }

    @Override
    public R loadSupplier(final SupplierHandler handler) {
        return (R) handler.handle();
    }


}
