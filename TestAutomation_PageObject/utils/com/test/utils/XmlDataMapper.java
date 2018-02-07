package com.test.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDataMapper {

	public static Map getDataFromXML(String XMLPath, String testName) {

		//System.out.println("BeforeTest:" + testName);
		Map<String, String> map = new HashMap<String, String>();

		try {

			File stocks = new File(XMLPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();

			// System.out.println("root of xml file" +
			// doc.getDocumentElement().getNodeName());
			// System.out.println("TestName:" +testName);
			NodeList nodes = doc.getElementsByTagName("test");
			int totalTestNodes = nodes.getLength();

			for (int i = 0; i < totalTestNodes; i++) {
				Node singleTestNode = nodes.item(i);
				if (singleTestNode.getNodeType() == Node.ELEMENT_NODE) {
					Element nodeElement = (Element) singleTestNode;
					if (testName.equals(nodeElement.getAttribute("name"))) {
						// System.out.println("nodeElement.getAttribute(\"name\")" +
						// nodeElement.getAttribute("name"));
						NodeList dataSetNode = singleTestNode.getChildNodes();
						int count = dataSetNode.getLength();
						for (int j = 0; j < count; j++) {
							Node current = dataSetNode.item(j);
							if (current.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) current;
								if (element.getTagName().equalsIgnoreCase("dataset")) {
									// System.out.println("In dataset:" + element.getTagName());
									NodeList parameterList = element.getChildNodes();
									int parameterCount = parameterList.getLength();
									for (int k = 0; k < parameterCount; k++) {
										Node currentParameter = parameterList.item(k);
										// System.out.println(currentParameter.getNodeName());
										if (currentParameter.getNodeType() == Node.ELEMENT_NODE) {
											Element elementParameter = (Element) currentParameter;
											if (elementParameter.getTagName().equalsIgnoreCase("parameter")) {
												String key = elementParameter.getAttribute("name");
												String value = elementParameter.getAttribute("value");
												// System.out.println("Key=" + key + " Value: " + value);
												map.put(key, value);
											}
										}
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//System.out.println("After Test:" + testName);
		return map;
	}

}
