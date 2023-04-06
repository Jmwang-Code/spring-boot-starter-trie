package com.cn.jmw.reader;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.cn.jmw.entity.TrieConfig;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jmw
 * @Warning If you use the "XmlToObject()" method , corresponding objects must be created and annotated .
 * 如果使用XmlToObject()方法，必须创建对应的对象，并且注释标记。
 * @Description XML -> MAP ,XML -> JSON ,JSON -> XML
 * @date 2022年12月06日 23:56
 * @Version 1.0
 */
public class XmlUtils implements InterUtils {

    /**
     * @return T
     * @throws
     * @Param [t, file]
     * @Date 2022/12/9 17:34
     * @see XmlUtils
     * Warning
     */
    public static <T> T XmlToObject(Class<T> t, File file) {
        URL resource = XmlUtils.class.getResource(file.getAbsolutePath());
        T config = XmlUtils.toBean(resource, t);
        return config;
    }

    /**
     * @return T
     * @throws
     * @Param [t, file]
     * @Date 2022/12/9 17:40
     * @see XmlUtils
     * Warning
     */
    public static <T> T XmlToObject(Class<T> t, String file) {
        try (InputStream in = XmlUtils.class.getResourceAsStream(file);) {
            T config = XmlUtils.toBean(new InputStreamReader(in,
                    "UTF-8"), t);
            return config;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T toBean(URL url, Class<T> c) {
        return toBeanDeep(url,c);
    }

    @SuppressWarnings("unchecked")
    private static <T> T toBean(Reader reader, Class<T> c) {
        return toBeanDeep(reader,c);
    }

    private static <T, K> K toBeanDeep(T t,Class<K> c) {
        K k = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            if (t instanceof File) {
                k = (K) unmarshaller.unmarshal((File) t);
            } else if (t instanceof InputStream) {
                k = (K) unmarshaller.unmarshal((InputStream) t);
            } else if (t instanceof Reader) {
                k = (K) unmarshaller.unmarshal((Reader) t);
            } else if (t instanceof URL) {
                k = (K) unmarshaller.unmarshal((URL) t);
            } else if (t instanceof InputSource) {
                k = (K) unmarshaller.unmarshal((InputSource) t);
            } else if (t instanceof Node) {
                k = (K) unmarshaller.unmarshal((Node) t);
            } else if (t instanceof Source) {
                k = (K) unmarshaller.unmarshal((Source) t);
            } else if (t instanceof XMLStreamReader) {
                k = (K) unmarshaller.unmarshal((XMLStreamReader) t);
            } else if (t instanceof XMLEventReader) {
                k = (K) unmarshaller.unmarshal((XMLEventReader) t);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return k;
    }


    /**
     * JSON对象转成xml文件
     *
     * @param jsonInfo json信息
     * @param filePath 文件路径，格式：D:\\file\\demo.xml
     * @return 是否成功
     */
    public static boolean jsonToXml(JSONObject jsonInfo, String filePath) throws Exception {
        File file = new File(filePath);
        // 创建根元素
        Element rootElement = new Element("description").setAttribute("title", FileUtil.getPrefix(filePath));
        // 将JSON信息转成Element
        Element xmlElement = convertObjectToXmlElement(jsonInfo, "xml", rootElement);
        Document document = new Document(xmlElement);
        XMLOutputter xmlOut = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setExpandEmptyElements(true);
        xmlOut.setFormat(format);
        try (FileOutputStream filefos = new FileOutputStream(file)) {
            xmlOut.output(document, filefos);
        } catch (IOException e) {
            throw new Exception(StrUtil.format("生成XML文件失败{}", e));
        } finally {
            xmlOut.clone();
        }
        return true;
    }

    /**
     * xml文件转JSON
     *
     * @param filePath xml文件
     * @return JSONObject
     * @throws Exception
     */
    public static JSONObject xmlToJson(String filePath) throws Exception {
        try {
            File file = new File(filePath);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(file);
            Element element = document.getRootElement();
            Map elementMap = xmlElementToMap(element);
            return JSON.parseObject(JSON.toJSONString(elementMap));
        } catch (Exception e) {
            throw new Exception(StrUtil.format("解析XML失败，文件地址为【{}】", filePath));
        }
    }

    /**
     * xml文件转Map
     *
     * @param filePath xml文件
     * @return JSONObject
     * @throws Exception
     */
    public static Map xmlToMap(String filePath)  {
        try {
            InputStream resourceAsStream = XmlUtils.class.getResourceAsStream(filePath);
//            File file = new File(filePath);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(resourceAsStream);
            Element element = document.getRootElement();
            Map elementMap = xmlElementToMap(element);
            return elementMap;
        } catch (PropertiesIsNullOrUnknownException e) {
            throw new PropertiesIsNullOrUnknownException(StrUtil.format("解析XML失败，文件地址为【{}】", filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象转成xml元素
     *
     * @param json          对象
     * @param parentElement 父级元素
     */
    private static Element convertObjectToXmlElement(Object json, String code, Element parentElement) {
        Element child;
        String eleStr;
        Object childValue;
        if (json instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) json;
            for (int i = 0; i < jsonArray.size(); i++) {
                childValue = jsonArray.get(i);
                child = new Element(code);
                parentElement.addContent(child);
                convertObjectToXmlElement(childValue, code, child);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) json;
            for (Object temp : jsonObject.keySet()) {
                eleStr = temp.toString();
                childValue = jsonObject.get(temp);
                child = new Element(eleStr);
                if (childValue instanceof JSONArray) {
                    convertObjectToXmlElement(childValue, eleStr, parentElement);
                } else {
                    parentElement.addContent(child);
                    convertObjectToXmlElement(childValue, eleStr, child);
                }
            }
        } else if (json instanceof Date) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parentElement.setText(sf.format((Date) json));
        } else {
            parentElement.setText(json.toString());
        }
        return parentElement;
    }

    private static Map xmlElementToMap(Element root) {
        List childrenList = root.getChildren();
        Element element = null;
        Map map = new HashMap(16);
        List list = null;
        for (int i = 0; i < childrenList.size(); i++) {
            list = new ArrayList();
            element = (Element) childrenList.get(i);
            if (!element.getChildren().isEmpty()) {
                if (root.getChildren(element.getName()).size() > 1) {
                    if (map.containsKey(element.getName())) {
                        list = (List) map.get(element.getName());
                    }
                    list.add(xmlElementToMap(element));
                    map.put(element.getName(), list);
                } else {
                    map.put(element.getName(), xmlElementToMap(element));
                }
            } else {
                if (root.getChildren(element.getName()).size() > 1) {
                    if (map.containsKey(element.getName())) {
                        list = (List) map.get(element.getName());
                    }
                    list.add(element.getTextTrim());
                    map.put(element.getName(), list);
                } else {
                    map.put(element.getName(), element.getTextTrim());
                }
            }
        }
        return map;
    }

//    public static void main(String[] args) {
//        TrieConfig trieConfig = XmlUtils.XmlToObject(TrieConfig.class, "/triedata-config.xml");
//        System.out.println(trieConfig);
//    }

}
