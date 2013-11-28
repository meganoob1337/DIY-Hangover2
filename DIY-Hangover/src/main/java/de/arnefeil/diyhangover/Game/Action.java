package de.arnefeil.diyhangover.Game;

/**
 * Created by arne on 11/4/13.
 */
public class Action {
    protected String mName;
    protected String mTooltip;
    protected int mCounting;

    public void setName(String name) {
        mName = name;
    }
    public void setTooltip(String tooltip) {
        mTooltip = tooltip;
    }

    public void increaseCounting() {
        mCounting++;
    }

    public boolean hasTooltip() {

        return !(mTooltip.replace(" ", "").length() == 0);
    }
    public String getName() {
        return mName;
    }
    public String getTooltip() {
        return mTooltip;
    }
    public int getCounting() {
        return mCounting;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Action) {
            Action a = (Action) o;
            result = a.getName().equals(this.getName());
        }
        return result;
    }
}
