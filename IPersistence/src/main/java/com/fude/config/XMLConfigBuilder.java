package com.fude.config;

import com.fude.io.Resources;
import com.fude.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author zhoujr
 * created at 2022/5/23 20:35
 *
 **/
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        configuration = new Configuration();
    }

    /**
     * 解析配置文件
     **/
    public Configuration parseConfig(InputStream in) throws Exception {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();

        Iterator<Element> iterator = rootElement.element("dataSource").elementIterator("property");
        Properties properties = new Properties();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            properties.setProperty(name, value);
        }

        // 数据源信息
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        // mapper.xml解析
        Iterator<Element> mapperIter = rootElement.elementIterator("mapper");
        while (mapperIter.hasNext()) {
            Element element = mapperIter.next();
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }
}
