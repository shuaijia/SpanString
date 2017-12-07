package com.jia.spanstring;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

/**
 * Description: 彩色文本
 * Created by jia on 2017/12/7.
 * 人之所以能，是相信能
 */
public class RainbowSpan extends CharacterStyle implements UpdateAppearance {
    private int[] colors;

    public RainbowSpan(Context context) {
        colors = context.getResources().getIntArray(R.array.splash_bg);
    }

    @Override
    public void updateDrawState(TextPaint paint) {
        paint.setStyle(Paint.Style.FILL);
        Shader shader = new LinearGradient(0, 0, 0, paint.getTextSize() * colors.length, colors, null,
                Shader.TileMode.MIRROR);
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);
    }
}
