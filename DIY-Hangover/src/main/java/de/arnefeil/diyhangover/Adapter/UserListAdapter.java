package de.arnefeil.diyhangover.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.arnefeil.diyhangover.R;

/**
 * Created by arnefeil on 09.11.13.
 */
public class UserListAdapter extends ArrayAdapter<String> {

    private ArrayList<String> mUsers;
    private Context mContext;

    public UserListAdapter(Context context, int textViewResourceId,
                           ArrayList<String> users) {
        super(context, textViewResourceId, users);
        mUsers = users;
        mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listitem_user, null);
        }

        TextView username = (TextView) v.findViewById(R.id.tv_user);
        if (username != null) {
            username.setText(mUsers.get(position));
        }

        final ImageView delete = (ImageView) v.findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage(mUsers.get(position) + " löschen?");
                builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mUsers.remove(position);
                        UserListAdapter.this.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Nein", null);
                builder.show();
            }
        });

        return v;
    }


}
