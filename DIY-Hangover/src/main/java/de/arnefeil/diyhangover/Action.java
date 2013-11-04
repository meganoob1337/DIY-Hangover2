package de.arnefeil.diyhangover;

/**
 * Created by arne on 11/4/13.
 */
public class Action {
    private String mName;
    private String mTooltip;
    private int mCounting;

    public Action(String name, String tooltip) {
        mName = name;
        mTooltip = tooltip;
        mCounting = 0;
    }

    public void setName(String name) {
        mName = name;
    }
    public void setTooltip(String tooltip) {
        mTooltip = tooltip;
    }
    public void increaseCounting() {
        mCounting++;
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

}
