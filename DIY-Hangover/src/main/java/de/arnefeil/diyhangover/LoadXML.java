package de.arnefeil.diyhangover;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arne on 11/4/13.
 */
public class LoadXML {



    public LoadXML() {

    }

    public ArrayList<Action>[] parse(InputStream is) throws Exception {
        try {

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        parser.nextTag();
        return readFeed(parser);
        } finally {
            is.close();
        }

    }

    private ArrayList<Action>[] readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Action> actions_p70 = new ArrayList<Action>();
        ArrayList<Action> actions_p20 = new ArrayList<Action>();
        ArrayList<Action> actions_p10 = new ArrayList<Action>();
        parser.require(XmlPullParser.START_TAG, null, "actions");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("actions-p70")) {
                actions_p70.add(readAction(parser, "actions-p70"));
            }
            else if (name.equals("actions-p20")) {
                actions_p20.add(readAction(parser, "actions-p20"));
            }
            else if (name.equals("actions-p10")) {
                actions_p10.add(readAction(parser, "actions-p10"));
            }
        }

        ArrayList<Action>[] actions = new ArrayList[3];
        actions[0] = actions_p70;
        actions[1] = actions_p20;
        actions[2] = actions_p10;

        return actions;
    }

    private Action readAction(XmlPullParser parser, String start_tag) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, start_tag);
        String item = null;
        String tooltip = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item"))
                item = readItem(parser);
            else if (name.equals("tooltip"))
                tooltip = readTooltip(parser);
        }
        return new Action(item, tooltip);
    }

    private String readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "item");
        String item = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "item");
        return item;
    }

    private String readTooltip(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "tooltip");
        String tooltip = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "tooltip");
        return tooltip;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
