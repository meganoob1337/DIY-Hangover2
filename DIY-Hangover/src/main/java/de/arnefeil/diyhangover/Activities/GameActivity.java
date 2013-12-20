package de.arnefeil.diyhangover.Activities;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import de.arnefeil.diyhangover.Adapter.ActiveRuleAdapter;
import de.arnefeil.diyhangover.Game.Game;
import de.arnefeil.diyhangover.Tools.MySAXParser;
import de.arnefeil.diyhangover.R;

public class GameActivity extends ActionBarActivity {

    private Game mGame;

    //private ViewPager mViewPager;
    //private ActionPageAdapter mActionPageAdapter;
    private TextView mCurrentUser;
    private TextView mCurrentAction;
    private ImageView mBtnTooltip;

    private View mPager;

    private Animation mMoveOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> users = getIntent().getStringArrayListExtra("users");
        InputStream is = getResources().openRawResource(R.raw.actions);
        MySAXParser parser = new MySAXParser(is);
        mGame = new Game(users, parser.getmActionList70(),
                parser.getmActionList20(),
                parser.getmActionList10());

        mMoveOut = AnimationUtils.loadAnimation(this, R.anim.move_out);
        mMoveOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mPager = findViewById(R.id.main_view);
        mPager.setOnTouchListener(new View.OnTouchListener() {
            int startX;
            int lastX;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int currentX = (int) event.getRawX();
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        startX = currentX;
                        lastX = currentX;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int delta = currentX - lastX;
                        if (delta < 0)
                            v.setLeft(delta);
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (startX > currentX)
                        {
                            mGame.next();
                            startAnimatedUpdateView();
                        }
                        break;
                }
                return true;
            }
        });

        mCurrentUser = (TextView) findViewById(R.id.tv_user);
        mCurrentAction = (TextView) findViewById(R.id.tv_action);
        mBtnTooltip = (ImageView) findViewById(R.id.btn_tooltip);

        updateView();


    }

    private void startAnimatedUpdateView() {
        mPager.startAnimation(mMoveOut);
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
        if (mGame.getActiveRules().size() == 0) {
            rules.setMessage("Keine Regeln aktiv");
        } else {
            ActiveRuleAdapter adapter = new ActiveRuleAdapter(this,
                    android.R.layout.simple_list_item_2, mGame.getActiveRules());
            rules.setAdapter(adapter, null);
        }
        rules.setCancelable(true);
        rules.setPositiveButton("Schließen", null);
        rules.show();
    }

    public void onClick(View btn) {
    switch(btn.getId())
        {
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

