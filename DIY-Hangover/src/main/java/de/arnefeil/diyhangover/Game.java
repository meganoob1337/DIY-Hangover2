package de.arnefeil.diyhangover;

import android.util.Log;

import org.apache.http.protocol.RequestUserAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.List;


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
                ArrayList<Action> actions10)
    {
        mUsers = users;
        mActions70 = actions70;
        mActions20 = actions20;
        mActions10 = actions10;
        mActiveRules = new HashSet<Rule>();
        mCurrentPlayer = -1;
        next();
    }


    public void next()
    {
        Random rnd = new Random();
        int a = rnd.nextInt(99);
       if(a<10)
       {
           mCurrentAction = mActions10.get(rnd.nextInt(mActions10.size()-1));
       }
        if(9<a && a<30)
        {
            mCurrentAction = mActions20.get(rnd.nextInt(mActions20.size()-1));
        }
        if(29<a && a< 100)

            {
                mCurrentAction = mActions70.get(rnd.nextInt(mActions70.size()-1));
            }

        mCurrentPlayer = (++mCurrentPlayer)%(mUsers.size());
        if (mCurrentAction instanceof Rule) {
            Rule r = (Rule) mCurrentAction;
            if (r.isWithPlayer()) {
                if (mActiveRules.contains(r)) {
                    mActiveRules.remove(r);
                }
                r.setUser(mUsers.get(mCurrentPlayer));
            }
            r.setActive(true);
            mActiveRules.add(r);
        }
    }

    public Action getCurrentAction()

    {
        return mCurrentAction;
    }

    public String getCurrentPlayer()
    {
        return mUsers.get(mCurrentPlayer) + ":";
    }

    public ArrayList<Rule> getActiveRules() {
        return new ArrayList<Rule>(mActiveRules);
    }

    public int getCountUser()
    {
        return mUsers.size();
    }

}
