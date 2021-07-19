package com.denis.store.utility;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {
    public static List<SortRules> parceXml(String fileName) {
        List<SortRules> fieldToOrder = new ArrayList<>();
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
                    fieldToOrder.add(new SortRules(key, xmlr.getText()));
                    key = "";
                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        return fieldToOrder;
    }

    public static class SortRules {
        public String keyName;
        public String sortRule;

        public SortRules(String keyName, String sortRule) {
            this.keyName = keyName;
            this.sortRule = sortRule;
        }
    }
}
