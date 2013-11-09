package de.arnefeil.diyhangover;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private EditText userName;
    private ArrayList<String> userList;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.et_name);

        userList = new ArrayList<String>();
        mAdapter = new UserListAdapter(this, R.layout.listitem_user, userList);
        ListView userListView = (ListView) findViewById(R.id.lv_users);
        userListView.setAdapter(mAdapter);
    }


    public void onClick(View button)
    {
        switch(button.getId())
        {
            case R.id.btn_add: addSpieler(); break;
            case R.id.btn_start: startGame(); break;
            case R.id.et_name: userName.setError(null); break;

        }
    }

    private void addSpieler() {
        if (!userName.getText().toString().equals(""))
        {   userName.setError(null);
            userList.add(userName.getText().toString());
            updateView();
        } else {
            userName.setError(getString(R.string.error_no_name));
        }



    }

    private void updateView() {
        userName.setText("");
        mAdapter.notifyDataSetChanged();
    }

    private void startGame() {
        if (userList.size() > 0) {
            Intent game  = new Intent(this, GameActivity.class);
            game.putStringArrayListExtra("users", userList);
            startActivity(game);
        }
    }

}
