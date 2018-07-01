package com.snow.rabobank.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.snow.rabobank.entity.Statement;

@Component
public class XmlStatementService implements IStatementService {

	@Override
	public List<Statement> parse(InputStream inputStream) {
		List<Statement> statements = new ArrayList<>();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputStream);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("record");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					try {
						String description = "";
						try {
							description = eElement.getElementsByTagName("description").item(0).getTextContent();
						} catch (Exception e) {
						}
						Statement statement = new Statement(Long.parseLong(eElement.getAttribute("reference")),
								eElement.getElementsByTagName("accountNumber").item(0).getTextContent(), description,
								Double.parseDouble(
										eElement.getElementsByTagName("startBalance").item(0).getTextContent()),
								Double.parseDouble(eElement.getElementsByTagName("mutation").item(0).getTextContent()),
								Double.parseDouble(
										eElement.getElementsByTagName("endBalance").item(0).getTextContent()));
						statements.add(statement);
					} catch (Exception e) {
						continue;
					}

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
		}

		return statements;
	}

}
