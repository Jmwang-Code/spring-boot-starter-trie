package com.cn.jmw.utils;

import com.alibaba.fastjson.JSONObject;
import com.cn.jmw.uitls.reader.XmlUtils;
import org.junit.jupiter.api.Test;
/**
 * @author jmw
 * @Description XML单元测试
 * @date 2022年12月09日 17:33
 * @Version 1.0
 */
public class XmlUtilsTest {

    @Test
    public void test() {
        JSONObject jsonObject = null;
        try {
            jsonObject = XmlUtils.xmlToJson("C:\\Users\\jmw\\Desktop\\jmw-code\\regression-search-tree\\src\\main\\resources\\triedata-config.xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonObject);
//反例
//        TrieNode trieNode = XmlUtils.XmlToObject(TrieNode.class, "/triedata-config.xml");
//        System.out.println(trieNode);
    }
}


