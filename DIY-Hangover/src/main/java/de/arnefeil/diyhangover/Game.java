package de.arnefeil.diyhangover;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andrei on 01.11.13.
 */
public class Game {
    private ArrayList<String> mUsers;
    private String[] mActions70;
    private String[] mActions20;
    private String[] mActions10;
    private int mCurrentPlayer;
    private String mCurrentAction;


    public Game(ArrayList<String> users, String[] actions70, String[] actions20, String[] actions10)
    {
        mUsers = users;
        mActions70 = actions70;
        mActions20 = actions20;
        mActions10 = actions10;
        mCurrentPlayer = -1;
        next();
    }


    public void next()
    {
        Random rnd = new Random();
        int a = rnd.nextInt(99);
       if(a<10)
       {
           mCurrentAction = mActions10[rnd.nextInt(mActions10.length-1)];
       }
        if(9<a && a<30)
        {
            mCurrentAction = mActions20[rnd.nextInt(mActions20.length-1)];
        }
        if(29<a && a< 100)

            {
                mCurrentAction = mActions70[rnd.nextInt(mActions70.length-1)];
            }

        mCurrentPlayer = (++mCurrentPlayer)%(mUsers.size());
    }

    public String getCurrentAction()

    {
        return mCurrentAction;
    }
    public String getCurrentPlayer()
    {
        return mUsers.get(mCurrentPlayer) + ":";
    }

}
