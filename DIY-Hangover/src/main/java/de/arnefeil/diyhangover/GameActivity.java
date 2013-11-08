package de.arnefeil.diyhangover;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends ActionBarActivity {

    private Game mGame;
    private TextView mCurrentUser;
    private TextView mCurrentAction;
    private TooltipActivity mTooltip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    private void startTooltip() {
        Intent tooltip  = new Intent(this, TooltipActivity.class);
        String b  = "bla123";
        // TODO ue bergeben vom tooltib  und dann wird er angezeigt ;D  
      //  tooltip.putExtra("tooltip", mGame.getCurrentAction().getTooltip());
         tooltip.putExtra("tooltip",b);
                startActivity(tooltip);
    }

    public void onClick(View btn) {
    switch(btn.getId())
        {
            case R.id.button: mGame.next(); break;
            case R.id.btn_tooltip: startTooltip(); break;


        }

        updateView();
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
