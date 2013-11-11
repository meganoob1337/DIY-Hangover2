package de.arnefeil.diyhangover;

/**
 * Created by arne on 11/4/13.
 */
public class Action {
    private String mName;
    private String mTooltip;
    private boolean mRule;
    private int mCounting;

    public void setName(String name) {
        mName = name;
    }
    public void setTooltip(String tooltip) {
        mTooltip = tooltip;
    }
    public void setRule(boolean rule) {
        mRule = rule;
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
    public boolean isRule() {
        return mRule;
    }
}
