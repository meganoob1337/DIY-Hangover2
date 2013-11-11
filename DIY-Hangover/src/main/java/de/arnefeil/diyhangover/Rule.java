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

    @Override
    public int hashCode() {
        return (this.getUser().hashCode() + this.getAction().getName().hashCode()) % (Integer.MAX_VALUE-1);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Rule) {
            Rule r = (Rule) o;
            result = r.getUser().equals(this.getUser()) && r.getAction().getName().equals(this.getAction().getName());
        }
        return result;
    }
}
