package de.arnefeil.diyhangover;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends ActionBarActivity {

    private Game mGame;
    private TextView mCurrentUser;
    private TextView mCurrentAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ArrayList<String> users = getIntent().getStringArrayListExtra("users");
        String[] actions70 = getResources().getStringArray(R.array.p70);
        String[] actions20 = getResources().getStringArray(R.array.p20);
        String[] actions10 = getResources().getStringArray(R.array.p10);

        mGame = new Game(users, actions70, actions20, actions10);

        mCurrentUser = (TextView) findViewById(R.id.tv_user);
        mCurrentAction = (TextView) findViewById(R.id.tv_action);

        updateView();
    }

    public void updateView() {
        mCurrentAction.setText(mGame.getCurrentAction());
        mCurrentUser.setText(mGame.getCurrentPlayer());
    }

    public void onClick(View btn) {
        mGame.next();
        updateView();
    }
}
