package com.jia.spanstring;

import java.util.ArrayList;

/**
 * Description:
 * Created by jia on 2017/12/7.
 * 人之所以能，是相信能
 */
public class TypeWriterSpanGroup {
    private final float mAlpha;
    private final ArrayList<MutableForegroundColorSpan> mSpans;
    public TypeWriterSpanGroup(float alpha) {
        mAlpha = alpha;
        mSpans = new ArrayList<MutableForegroundColorSpan>();
    }
    public void addSpan(MutableForegroundColorSpan span) {
        span.setAlpha((int) (mAlpha * 255));
        mSpans.add(span);
    }
    public void setAlpha(float alpha) {
        int size = mSpans.size();
        float total = 1.0f * size * alpha;
        for(int index = 0 ; index < size; index++) {
            MutableForegroundColorSpan span = mSpans.get(index);
            if(total >= 1.0f) {
                span.setAlpha(255);
                total -= 1.0f;
            } else {
                span.setAlpha((int) (total * 255));
                total = 0.0f;
            }
        }
    }
    public float getAlpha() {
        return mAlpha;
    }
}
