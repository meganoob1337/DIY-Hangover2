package de.arnefeil.diyhangover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private EditText userName;
    private TextView users;
    private ArrayList<String> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.et_name);
        users = (TextView)findViewById(R.id.tv_names);
        userList = new ArrayList<String>();
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
            userName.setError("keine Name angegeben!");
        }



    }

    private void updateView() {
        if (userList.size() > 0) {
            String user = "";
            for(String s : userList)
            {
                user += s+ "\n";

            }
            userName.setText("");
            users.setText(user);
        }

    }

    private void startGame() {
        if (users.length() > 0) {
            Intent game  = new Intent(this, GameActivity.class);
            game.putStringArrayListExtra("users", userList);
            startActivity(game);
        }
    }

}
