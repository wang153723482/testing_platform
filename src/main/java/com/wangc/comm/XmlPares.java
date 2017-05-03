package com.wangc.comm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchao on 2017/5/3.
 */
public class XmlPares {

    Element element = null;
    static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    
    public XmlPares(String filePath) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        //parse an XML file into a DOM tree 
        Document document = builder.parse(new File(filePath));
        //get root element 
        this.element = document.getDocumentElement();
    }

    //获取某个标签的值
    public String getValueByTagAttr(String tagAttrName) {
        ArrayList<String> list = new ArrayList();
        list.add(tagAttrName);
        return getValueByTagAttrs(list).get(tagAttrName);
    }

    //获取一组标签的值
    public Map<String, String> getValueByTagAttrs(List<String> tagAttrNames) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            NodeList nodeList = element.getElementsByTagName("stringProp");
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    for(int j=0; j<tagAttrNames.size(); j++){
                        if( tagAttrNames.get(j).equals( element.getAttribute("name") ) ){
                            resultMap.put( tagAttrNames.get(j),element.getTextContent() );
                        }
                    }
                }
            }
        } catch (Exception e) { //todo 具体异常类
            e.printStackTrace();
        }
        return null;
    }
}
