package de.arnefeil.diyhangover;

/**
 * Created by arnefeil on 11.11.13.
 */
public class Rule {

    private String mUser;
    private Action mAction;

    public Rule(String user, Action action) {
        mUser = user;
        mAction = action;
    }

    public String getUser() {
        return mUser;
    }

    public Action getAction() {
        return mAction;
    }

}
