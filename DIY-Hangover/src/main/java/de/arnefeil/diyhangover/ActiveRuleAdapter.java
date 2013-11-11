package de.arnefeil.diyhangover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by arnefeil on 11.11.13.
 */
public class ActiveRuleAdapter extends ArrayAdapter<Rule> {

    private ArrayList<Rule> mRules;
    private Context mContext;

    public ActiveRuleAdapter(Context context, int textViewResourceId,
                             ArrayList<Rule> rules) {
        super(context, textViewResourceId, rules);
        mRules = rules;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView textfield1 = (TextView) v.findViewById(android.R.id.text2);
        TextView textfield2 = (TextView) v.findViewById(android.R.id.text1);

        Rule rule = mRules.get(position);

        if (textfield1 != null) {
            textfield1.setText(rule.getUser());
        }

        if (textfield2 != null) {
            textfield2.setText(rule.getAction().getName());
        }

        return v;
    }
}
