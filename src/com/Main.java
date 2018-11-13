package com;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("prop.xml"));

        Element element = document.getDocumentElement();
        printElements(element.getChildNodes(), 0);
        //        System.out.println(element.getTagName());
//        NodeList nodeList=element.getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            if(nodeList.item(i)instanceof Element)
//            System.out.println(((Element)nodeList.item(i)).getTagName());
//        }
//    }
    }

    static void printElements(NodeList nodeList, int tabs) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                String value = "";
                if (!nodeList.item(i).getTextContent().trim().isEmpty() && !((Text) nodeList.item(i).getFirstChild()).getData().trim().isEmpty() && !((Text) nodeList.item(i).getFirstChild()).getData().trim().equals("\n")) {
                    Text text = (Text) nodeList.item(i).getFirstChild();
                    value += " = " + text.getData().trim();
                }
                System.out.println(getTab(tabs) + nodeList.item(i).getNodeName() + value);

                NamedNodeMap attributes=nodeList.item(i).getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    {
                        Node attribute=attributes.item(j);
                        String name=attribute.getNodeName();
                        String val=attribute.getNodeValue();
                        System.out.println("Atributes + "+name+" = "+val);
                    }

                }


                System.out.println(getTab(tabs) + ((Element) nodeList.item(i)).getTagName() + value);
                if (((Element) nodeList.item(i)).hasAttribute("title")) {
                    System.out.println(((Element) nodeList.item(i)).getAttribute("title"));
                }
                if (nodeList.item(i).hasChildNodes()) {
                    printElements(nodeList.item(i).getChildNodes(), ++tabs);
                }
            }
        }
    }

    static String getTab(int tabs) {
        String str = "";
        for (int i = 0; i < tabs; i++) {
            str += "\t";
        }
        return str;
    }
}































