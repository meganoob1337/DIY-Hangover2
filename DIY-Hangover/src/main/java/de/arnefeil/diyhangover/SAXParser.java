package de.arnefeil.diyhangover;

import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * Created by arnefeil on 07.11.13.
 */
public class SAXParser extends DefaultHandler {

    private static final String ACTIONS_P70 = "actions-p70";
    private static final String ACTIONS_P20 = "actions-p20";
    private static final String ACTIONS_P10 = "actions-p10";
    private static final String ACTION = "action";
    private static final String ITEM = "item";
    private static final String TOOLTIP = "tooltip";


    List<Action> mActionList;

    public SAXParser() {

    }
}
