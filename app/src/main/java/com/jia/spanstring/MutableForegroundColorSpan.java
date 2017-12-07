package com.jia.spanstring;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

/**
 * Description:
 * Created by jia on 2017/12/7.
 * 人之所以能，是相信能
 */
public class MutableForegroundColorSpan extends CharacterStyle implements UpdateAppearance {

    private int mColor = Color.BLACK;
    private int mAlpha = 0 ;

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setColor(mColor);
        tp.setAlpha(mAlpha);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }
}
