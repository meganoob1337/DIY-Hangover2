package de.arnefeil.diyhangover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import de.arnefeil.diyhangover.Activities.GameActivity;
import de.arnefeil.diyhangover.Adapter.UserListAdapter;

public class MainActivity extends ActionBarActivity {

    private EditText userName;
    private ListView lv_users;
    private ArrayList<String> userList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.et_name);

        userList = new ArrayList<String>();
        mAdapter = new UserListAdapter(this, R.layout.listitem_user, userList);
        lv_users = (ListView) findViewById(R.id.lv_users);
        lv_users.setAdapter(mAdapter);
        // Testgame start //
       /* userList.add("Test 1");
        //userList.add("Test 2");
        //userList.add("Test 3");
        //userList.add("Test 4");

        startGame();*/
    }


    public void onClick(View button)
    {
        switch(button.getId())
        {
            case R.id.btn_add: addSpieler(); break;
            case R.id.action_start_game: startGame(); break;
            //case R.id.btn_start: startGame(); break;
            case R.id.et_name: userName.setError(null); break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_start_game:
                startGame();
                return true;
            default:
                return false;
        }
    }

    private void addSpieler() {
        if (userList.contains(userName.getText().toString()))
        {
            userName.setError("Spieler schon vorhanden");
        } else if (!userName.getText().toString().equals(""))
        {   userName.setError(null);
            userList.add(userName.getText().toString());
            updateView();
        } else {
            userName.setError(getString(R.string.error_no_name));
        }

        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

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
