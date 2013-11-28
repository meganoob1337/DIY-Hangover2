package de.arnefeil.diyhangover.Game;

/**
 * Created by arnefeil on 11.11.13.
 */
public class Rule extends Action {

    private String mUser;
    private boolean mWithPlayer;
    private boolean mActive;
    private boolean mVisible;

    public Rule(boolean withPlayer) {
        mUser = "";
        mWithPlayer = withPlayer;
        mActive = false;
        mVisible = false;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public void setActive(boolean active) {
        mActive = active;
    }

    public void setVisible(boolean visible)
    {
        mVisible = visible;
    }


    public String getUser() {
        return mUser;
    }

    public boolean isActive() {
        return mActive;
    }

    public boolean isWithPlayer() {
        return mWithPlayer;
    }

    public boolean isVisible()
    {
        return mVisible;
    }
    @Override
    public int hashCode() {
        int withPlayer = 0;
        if (isWithPlayer())
            withPlayer = 1;
        return mName.hashCode() + withPlayer % (Integer.MAX_VALUE-1);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Rule) {
            Rule r = (Rule) o;
            result = mName.equals(r.getName()) && mWithPlayer == r.isWithPlayer();
        }
        return result;
    }
}
