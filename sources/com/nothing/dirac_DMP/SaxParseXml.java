package com.nothing.dirac_DMP;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: classes4.dex */
public class SaxParseXml extends DefaultHandler {
    private List<UserMode> list;
    private String tagName;
    private UserMode userMode;

    public List<UserMode> getList() {
        return this.list;
    }

    public void setList(List<UserMode> list) {
        this.list = list;
    }

    public UserMode getUserMode() {
        return this.userMode;
    }

    public void setUserMode(UserMode userMode) {
        this.userMode = userMode;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.list = new ArrayList();
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("usermode")) {
            UserMode userMode = new UserMode();
            this.userMode = userMode;
            userMode.setName(attributes.getValue(1).toString());
        }
        this.tagName = qName;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("usermode")) {
            this.list.add(this.userMode);
        }
        this.tagName = null;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.tagName != null) {
            String data = new String(ch, start, length);
            if (this.tagName.equals("tonal_balance")) {
                this.userMode.setTonalBalance(Float.valueOf(data).floatValue());
            }
            if (this.tagName.equals("stereo_width")) {
                this.userMode.setStereoWidth(Float.valueOf(data).floatValue());
            }
        }
    }
}
