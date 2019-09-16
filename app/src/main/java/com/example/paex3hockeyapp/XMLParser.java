package com.example.paex3hockeyapp;

import android.provider.DocumentsContract;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {

    public XMLParser(){}

    public String getXML(String URL) {
        String xml = null;
        try {

            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL);
            HttpResponse httpResponse = client.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }


    public Document getDomElement(String XML) {
        Document document = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(XML));
            document = builder.parse(source);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    public final String getElement(Node element){
        Node childElement;

        if(element != null) {
            if(element.hasChildNodes()) {
                for(childElement = element.getFirstChild(); childElement != null; childElement = childElement.getNextSibling()) {
                    if(childElement.getNodeType() == Node.TEXT_NODE){
                        return childElement.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public String getValue(Element item, String string){
        NodeList nodeList = item.getElementsByTagName(string);
        return this.getElement(nodeList.item(0));
    }
}
