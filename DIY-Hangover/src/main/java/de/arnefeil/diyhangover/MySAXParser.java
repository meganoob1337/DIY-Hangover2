package de.arnefeil.diyhangover;

import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import java.text.ParseException;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.List;

/**
 * Created by arnefeil on 07.11.13.
 */
public class MySAXParser extends DefaultHandler {

    private static final String ACTIONS_P70 = "actions-p70";
    private static final String ACTIONS_P20 = "actions-p20";
    private static final String ACTIONS_P10 = "actions-p10";
    private static final String ACTION = "action";
    private static final String ITEM = "item";
    private static final String TOOLTIP = "tooltip";


    List<Action> mActionList70;
    List<Action> mActionList20;
    List<Action> mActionList10;
    private List<Action> mActions;
    private String  tmpValue;
    public Action tmpAction;
    public MySAXParser() {



    }
    private void parseDocument() {
        // parse
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse("actions.xml", this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


    public List<Action> getList(int p)
    {
        switch(p)
        {
            case 70: return mActionList70;
            case 20: return mActionList20;
            case 10: return mActionList10;
            default: return mActionList10 ;
        }
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book , create new book
        // clear tmpValue on start of element

        if (elementName.equalsIgnoreCase("action"))
        {

        }
        if (elementName.equalsIgnoreCase("action")) {
            tmpAction = new Action();

        }

    }
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        // if end of book element add to list
        if (element.equals("book")) {
            mActions.add(tmpAction);
                    }
        if(element.equals("name"))
        {
           tmpAction.setName(tmpValue);

        }
        if(element.equals("tooltip"))
        {
            tmpAction.setTooltip(tmpValue);
        }
        if(element.equals("prozent"))
        {
            tmpAction.setProzent(tmpValue);


        }


    }
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }

    public void sortActions()
    {
        for(Action a: mActions)
        {
            switch(a.getProzent())
            {
                case 70: mActionList70.add(a);
                case 20: mActionList20.add(a);
                case 10: mActionList10.add(a);
            }
        }
    }
}
