package de.arnefeil.diyhangover;

import android.util.Log;

import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Created by arnefeil on 07.11.13.
 */
public class MySAXParser extends DefaultHandler {

    private static final String ACTIONS = "actions";
    private static final String CHANCE = "chance";
    private static final String ACTION = "action";
    private static final String NAME = "name";
    private static final String TOOLTIP = "tooltip";
    private static final String RULE = "rule";


    private ArrayList<Action> mActionList70;
    private ArrayList<Action> mActionList20;
    private ArrayList<Action> mActionList10;
    private String  tmpValue;
    private int prozent;
    public Action tmpAction;


    public MySAXParser(InputStream xml) {
        mActionList70 = new ArrayList<Action>();
        mActionList20 = new ArrayList<Action>();
        mActionList10 = new ArrayList<Action>();
        parseDocument(xml);
    }

    public ArrayList<Action> getmActionList70() {
        return mActionList70;
    }

    public ArrayList<Action> getmActionList20() {
        return mActionList20;
    }

    public ArrayList<Action> getmActionList10() {
        return mActionList10;
    }

    private void parseDocument(InputStream xml) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xml, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }



    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        if (elementName.equals(ACTIONS)) {
            prozent = Integer.parseInt(attributes.getValue(CHANCE));
        }
        if (elementName.equals(ACTION)) {
            tmpAction = new Action();
            tmpAction.setRule(attributes.getValue(RULE) != null && attributes.getValue(RULE).equals("true"));
        }
    }
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        if (element.equals(ACTION)) {
            switch (prozent) {
                case 70:
                    mActionList70.add(tmpAction);
                    break;
                case 20:
                    mActionList20.add(tmpAction);
                    break;
                case 10:
                    mActionList10.add(tmpAction);
                    break;
            }
        }
        if (element.equals(NAME))
            tmpAction.setName(tmpValue);
        if (element.equals(TOOLTIP))
            tmpAction.setTooltip(tmpValue);
    }
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }
}
