package com.example.veronschreuder.higer_lower;

public class ThrowDice {
    private String mThrowDiceText;

    public ThrowDice(String mThrowDiceText) {
        this.mThrowDiceText = mThrowDiceText;
    }

    public String getmThrowDiceText() {
        return mThrowDiceText;
    }

    public void setmThrowDiceText(String mThrowDiceText) {
        this.mThrowDiceText = mThrowDiceText;
    }

    @Override public String toString() {
        return mThrowDiceText;
    }

}
