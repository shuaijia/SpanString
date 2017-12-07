package com.jia.spanstring;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Property;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView tv_diff_color;
    private TextView tv_bg_color;
    private TextView tv_link;
    private TextView tv_biankuang;
    private TextView tv_color;
    private TextView tv_color_anim;
    private TextView tv_dazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();

        setForegroundColor();

        setBackgroundColor();

        setLink();

        addBox();

        setColofulText();

        setColofulAnimText();

        setColofulAnimText();

        addTyping();
    }

    /**
     * 设置不同颜色文字
     */
    private void setForegroundColor() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");

        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_diff_color.setText(spannableString);
    }

    private void setBackgroundColor() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");

        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_bg_color.setText(spannableString);
    }

    /**
     * 设置超链接
     */
    private void setLink() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");
        //设置下划线
        spannableString.setSpan(new UnderlineSpan(), 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_link.setText(spannableString);
    }

    /**
     * 给文字加边框
     */
    private void addBox() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");
        spannableString.setSpan(new FrameSpan(), 0, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_biankuang.setText(spannableString);
    }

    /**
     * 设置彩色字体
     */
    private void setColofulText() {
        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");
        spannableString.setSpan(new RainbowSpan(this), 0, 15, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_color.setText(spannableString);
    }

    /**
     * 设置彩色动画
     */
    private void setColofulAnimText() {
        final SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");
        AnimatedColorSpan span = new AnimatedColorSpan(this);

        spannableString.setSpan(span, 0, 15, 0);

        // 设置动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                span, ANIMATED_COLOR_SPAN_FLOAT_PROPERTY, 0, 100);
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_color_anim.setText(spannableString);
            }
        });
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(DateUtils.MINUTE_IN_MILLIS * 2);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    /**
     * 彩色动画 属性变化器
     */
    private static final Property<AnimatedColorSpan, Float> ANIMATED_COLOR_SPAN_FLOAT_PROPERTY
            = new Property<AnimatedColorSpan, Float>(Float.class, "ANIMATED_COLOR_SPAN_FLOAT_PROPERTY") {
        @Override
        public void set(AnimatedColorSpan span, Float value) {
            span.setTranslateXPercentage(value);
        }

        @Override
        public Float get(AnimatedColorSpan span) {
            return span.getTranslateXPercentage();
        }
    };

    /**
     * 打字效果
     */
    private void addTyping() {

        String content = "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升";

        final SpannableString spannableString = new SpannableString(content);
        // 添加Span
        final TypeWriterSpanGroup group = new TypeWriterSpanGroup(0);
        for (int index = 0; index <= content.length() - 1; index++) {
            MutableForegroundColorSpan span = new MutableForegroundColorSpan();
            group.addSpan(span);
            spannableString.setSpan(span, index, index + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        // 添加动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(group, TYPE_WRITER_GROUP_ALPHA_PROPERTY, 0.0f, 1.0f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //refresh
                tv_dazi.setText(spannableString);
            }
        });
        objectAnimator.setDuration(5000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();

    }

    /**
     * 打字动画  属性变化器
     */
    private static final Property<TypeWriterSpanGroup, Float> TYPE_WRITER_GROUP_ALPHA_PROPERTY =
            new Property<TypeWriterSpanGroup, Float>(Float.class, "TYPE_WRITER_GROUP_ALPHA_PROPERTY") {
                @Override
                public void set(TypeWriterSpanGroup spanGroup, Float value) {
                    spanGroup.setAlpha(value);
                }

                @Override
                public Float get(TypeWriterSpanGroup spanGroup) {
                    return spanGroup.getAlpha();
                }
            };

    private void initView() {
        tv_diff_color = (TextView) findViewById(R.id.tv_diff_color);
        tv_bg_color = (TextView) findViewById(R.id.tv_bg_color);
        tv_link = (TextView) findViewById(R.id.tv_link);
        tv_biankuang = (TextView) findViewById(R.id.tv_biankuang);
        tv_color = (TextView) findViewById(R.id.tv_color);
        tv_color_anim = (TextView) findViewById(R.id.tv_color_anim);
        tv_dazi = (TextView) findViewById(R.id.tv_dazi);
    }

}
