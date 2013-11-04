package de.arnefeil.diyhangover;

import android.content.Context;
import android.content.res.XmlResourceParser;
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

    private Context mContext;

    public LoadXML(Context context) {
        mContext = context;
    }

    public List parse(InputStream is) throws Exception {
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

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();
        parser.require(XmlPullParser.START_TAG, null, "actions");
        while (parser.next() != XmlPullParser.END_TAG) {
            String name = parser.getName();

        }

        return entries;
    }

}
