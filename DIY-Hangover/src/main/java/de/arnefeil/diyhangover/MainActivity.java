package de.arnefeil.diyhangover;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View button)
    {
        switch(button.getId())
        {
            case R.id.btn_add: addSpieler(); break;
            case R.id.btn_start: startGame(); break;


        }
    }

    private void addSpieler() {
        userList.add(userName.getText().toString());
        updateView();

    }

    private void updateView() {
        String user = "";
        for(String s : userList)
        {
            user += s+ "\n";

        }
        userName.setText("");
        users.setText(user);

    }

    private void startGame() {
        Intent game  = new Intent(this, GameActivity.class);
        game.putStringArrayListExtra("users", userList);
        startActivity(game);
    }

}
