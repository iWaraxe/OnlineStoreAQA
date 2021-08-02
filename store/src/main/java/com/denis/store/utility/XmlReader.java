package com.denis.store.utility;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlReader {
    public static Map<String, String> parceXml(String fileName) {
        Map<String, String> fieldToOrder = new HashMap<>();
        InputStream istream = XmlReader.class.getResourceAsStream(fileName);
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName,
                    istream
            );
            String key = "";
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {
                    key = xmlr.getLocalName();
                } else if (xmlr.isEndElement()) {
                    key = xmlr.getLocalName();
                } else if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                    fieldToOrder.put(key, xmlr.getText());
                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        return fieldToOrder;
    }
}
