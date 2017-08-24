package com.wangc.test_plan.jmeter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

public class Tools {

    // TODO: wangc@2017/8/2  全局变量的线程同步问题 
    static String targetPath = "";
    
    //返回CSVData的xpath
    public static String getCsvDataXPath(String jmxPath) throws MalformedURLException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(jmxPath));
        Element root = document.getRootElement();

        try{
            read(root);
        }catch (Exception e){
            e.printStackTrace();
        }
        return targetPath;
    }
    private static void read(Element e) {
        String result = "";
        if( !"".equals(targetPath) ){
            return ;
        }
        if (e.nodeCount() > 0) {
            Iterator<Element> it = e.elementIterator();
            while (it.hasNext()) {
                Element ele = it.next();
                if( "CSVDataSet".equals(ele.getName()) ){
                    /*if("".equals(targetPath)){ //查到的第一个 CSVDataSet 保存到参数中，
                        targetPath = ele.getUniquePath();
                    }*/
                    targetPath = ele.getUniquePath();
                }
                read(ele);
            }
        }

    }


    //修改jmx
    public static void updateJmx(String jmxFilePath,String csvFilePath,String csvDataXpath) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document documentNew =  reader.read(new File(jmxFilePath));
        List<Element> list = documentNew.selectNodes(csvDataXpath);
        if( list.size()>1 ){
            System.out.println("报错");
        }else{
            Element e = list.get(0);
            List<Element> eList = e.elements("stringProp");
            for(Element eStringProp:eList){
                if( "filename".equals( eStringProp.attributeValue("name") ) ){
                    System.out.println("==========");
                    System.out.println( eStringProp.getText() );
                    eStringProp.setText(csvFilePath);
                    break;
                }
            }
        }

        XMLWriter writer = new XMLWriter(new FileWriter(new File( jmxFilePath )));
        writer.write(documentNew);
        writer.close();

    }
    
    public static void main(String[] args) throws MalformedURLException, DocumentException {
        System.out.println( getCsvDataXPath("G:\\MyDesktop\\TestPlan.jmx") );
    }
}
