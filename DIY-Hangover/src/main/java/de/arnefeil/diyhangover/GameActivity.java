package de.arnefeil.diyhangover;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class GameActivity extends ActionBarActivity {

    private Game mGame;
    private TextView mCurrentUser;
    private TextView mCurrentAction;
    private ImageView mBtnTooltip;


    //private TooltipActivity mTooltip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> users = getIntent().getStringArrayListExtra("users");
        InputStream is = getResources().openRawResource(R.raw.actions);
        MySAXParser parser = new MySAXParser(is);
        Log.v("GameActivity", ""+parser.getmActionList70().size());
        Log.v("GameActivity", ""+parser.getmActionList20().size());
        Log.v("GameActivity", ""+parser.getmActionList10().size());
        mGame = new Game(users, parser.getmActionList70(),
                parser.getmActionList20(),
                parser.getmActionList10());


        mCurrentUser = (TextView) findViewById(R.id.tv_user);
        mCurrentAction = (TextView) findViewById(R.id.tv_action);
        mBtnTooltip = (ImageView) findViewById(R.id.btn_tooltip);

        updateView();
    }

    public void updateView() {
        mCurrentAction.setText(mGame.getCurrentAction().getName());
        mCurrentUser.setText(mGame.getCurrentPlayer());
        if (mGame.getCurrentAction().hasTooltip()) {
            mBtnTooltip.setVisibility(View.VISIBLE);
        } else {
            mBtnTooltip.setVisibility(View.GONE);
        }
    }
    private void startTooltip() {
        AlertDialog.Builder tooltip = new AlertDialog.Builder(this);
        tooltip.setMessage(mGame.getCurrentAction().getTooltip());
        tooltip.setCancelable(true);
        tooltip.setPositiveButton("Schließen", null);
        tooltip.show();
    }

    private void startActiveRules() {
        AlertDialog.Builder rules = new AlertDialog.Builder(this);
        ActiveRuleAdapter adapter = new ActiveRuleAdapter(this,
                android.R.layout.simple_list_item_2, mGame.getActiveRules());
        rules.setAdapter(adapter, null);
        rules.setCancelable(true);
        rules.setPositiveButton("Schließen", null);
        rules.show();
    }

    public void onClick(View btn) {
    switch(btn.getId())
        {
            case R.id.button:
                mGame.next();
                updateView();
                break;
            case R.id.btn_tooltip:
                startTooltip();
                break;
            case R.id.btn_show_rules:
                startActiveRules();
                break;
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
