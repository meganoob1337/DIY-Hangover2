package de.arnefeil.diyhangover;

/**
 * Created by arne on 11/4/13.
 */
public class Action {
    private String mName;
    private String mTooltip;
    private int mCounting;
    private String mProzent;

    public Action() {
        mName = "";
        mProzent = "";
        mTooltip = "";
        mCounting = 0;

    }

    public void setName(String name) {
        mName = name;
    }
    public void setTooltip(String tooltip) {
        mTooltip = tooltip;
    }
    public void setProzent(String prozent) {mProzent = prozent;}
    public void increaseCounting() {
        mCounting++;
    }


    public String getName() {
        return mName;
    }
    public int getProzent(){return Integer.parseInt(mProzent)
            ;}
    public String getTooltip() {
        return mTooltip;
    }
    public int getCounting() {
        return mCounting;
    }
}
