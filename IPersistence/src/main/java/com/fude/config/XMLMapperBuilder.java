package com.fude.config;

import com.fude.pojo.Configuration;
import com.fude.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author zhoujr
 * created at 2022/5/26 14:35
 *
 **/
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream in) throws Exception {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        Iterator<Element> iterator = rootElement.elementIterator("select");
        while (iterator.hasNext()) {
            Element e = iterator.next();
            String id = e.attributeValue("id");
            String resultType = e.attributeValue("resultType");
            String parameterType = e.attributeValue("parameterType");
            String sqlText = e.getTextTrim();
            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setSql(sqlText);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setResultType(resultType);
            String key = namespace + "." + id;
            configuration.getMapperStatementMap().put(key, mapperStatement);
        }
    }
}
