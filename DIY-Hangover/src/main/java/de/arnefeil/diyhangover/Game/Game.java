package de.arnefeil.diyhangover.Game;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


/**
 * Created by Andrei on 01.11.13.
 */
public class Game {
    private ArrayList<String> mUsers;
    private ArrayList<Action> mActions70;
    private ArrayList<Action> mActions20;
    private ArrayList<Action> mActions10;
    private HashSet<Rule> mActiveRules;
    private int mCurrentPlayer;
    private Action mCurrentAction;


    public Game(ArrayList<String> users, ArrayList<Action> actions70, ArrayList<Action> actions20,
                ArrayList<Action> actions10) {
        mUsers = users;
        mActions70 = actions70;
        mActions20 = actions20;
        mActions10 = actions10;
        mActiveRules = new HashSet<Rule>();
        mCurrentPlayer = -1;
        next();
    }


    public void next() {
        Random rnd = new Random();
        int a = rnd.nextInt(99);
        int next = 0;
        if (a < 10) {
            if (mActions10.size() > 1)
                next = rnd.nextInt(mActions10.size() - 1);
            mCurrentAction = mActions10.get(next);
        }
        if (9 < a && a < 30) {
            if (mActions20.size() > 1)
                next = rnd.nextInt(mActions20.size() - 1);
            mCurrentAction = mActions20.get(next);
        }
        if (29 < a && a < 100)
        {
            if (mActions70.size() > 1)
                next = rnd.nextInt(mActions70.size() - 1);
            mCurrentAction = mActions70.get(next);
        }

        mCurrentPlayer = (++mCurrentPlayer) % (mUsers.size());

        if (mCurrentAction instanceof Rule) {

           /* Rule r = (Rule) mCurrentAction;
            Log.v("Game", r.toString());
            if (mActiveRules.contains(r))
            {
                if (r.isWithPlayer())
                {
                    if (r.getUser().equals(mUsers.get(mCurrentPlayer)))
                    {
                        r.setUser(mUsers.get(mCurrentPlayer));
                    } else
                    {
                        r.setActive(false);
                        mActiveRules.remove(r);
                    }
                } else
                {
                    r.setActive(false);
                    mActiveRules.remove(r);
                }

            } else
            {
                if (r.isWithPlayer())
                {
                    r.setUser(mUsers.get(mCurrentPlayer));
                }
                r.setActive(true);
                mActiveRules.add(r);
            }*/


            Rule r = (Rule) mCurrentAction;
            if (r.isWithPlayer())
            {
                r.setUser(mUsers.get(mCurrentPlayer));
                mActiveRules.add(r);
            } else
            {
            if (mActiveRules.contains(r)) {
                 mActiveRules.remove(r);


            } else
            {
                    mActiveRules.add(r);
            }

            }
        }

    }

    public Action getCurrentAction()

    {
        return mCurrentAction;
    }

    public String getCurrentPlayer() {
        return mUsers.get(mCurrentPlayer) + ":";
    }

    public ArrayList<Rule> getActiveRules() {
        return new ArrayList<Rule>(mActiveRules);
    }

    public int getCountUser() {
        return mUsers.size();
    }

}
