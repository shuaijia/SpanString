package com.jia.spanstring;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private String diff_color = "<p style=\"color:red\"><font color=\"#ff0000\"><span style=\"font-size: 14px;\"><strong><strong>这位盆友，你真是来送温暖啊</strong></strong></span></p>说得我们这些搬砖的好像很幸福的样子";

    private String img_content =
            "<p>下面是网络图片</p><img src=\"http://static.oschina.net/uploads/space/2015/0720/172817_huYO_2359467.jpg\"/>";

    private String link_content = "这是一个超链接\n" +
            "<a href=\"www.baidu.com\">www.baidu.com</a>";
    private TextView tv_diff_color;
    private TextView tv_img;
    private TextView tv_link;
    private TextView tv_biankuang;
    private TextView tv_ver;
    private TextView tv_color;
    private TextView tv_color_anim;
    private TextView tv_dazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        setText();
    }

    private void setText() {
        tv_diff_color.setText(Html.fromHtml(diff_color));

        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        tv_link.setText(Html.fromHtml(link_content));

        tv_img.setText(Html.fromHtml(img_content, new NetWorkImageGetter(), null));

        SpannableString spannableString = new SpannableString(
                "我爱北京天安门，天安门上太阳升 我爱北京天安门，天安门上太阳升");
        spannableString.setSpan(new FrameSpan(), 0, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv_biankuang.setText(spannableString);
    }

    private void initView() {
        tv_diff_color = (TextView) findViewById(R.id.tv_diff_color);
        tv_img = (TextView) findViewById(R.id.tv_img);
        tv_link = (TextView) findViewById(R.id.tv_link);
        tv_biankuang = (TextView) findViewById(R.id.tv_biankuang);
        tv_ver = (TextView) findViewById(R.id.tv_ver);
        tv_color = (TextView) findViewById(R.id.tv_color);
        tv_color_anim = (TextView) findViewById(R.id.tv_color_anim);
        tv_dazi = (TextView) findViewById(R.id.tv_dazi);
    }

    class NetWorkImageGetter implements Html.ImageGetter {

        @Override
        public Drawable getDrawable(final String source) {
            Drawable dra = new BitmapDrawable(getbitmap(source));
            dra.setBounds(0, 0, dra.getIntrinsicWidth() * 2, dra.getIntrinsicHeight() * 2);
            return dra;
        }

    }

    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return
     */
    public static Bitmap getbitmap(final String imageUri) {

        // 显示网络上的图片
        final Bitmap[] bitmap = {null};

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL myFileUrl = new URL(imageUri);
                    HttpURLConnection conn = (HttpURLConnection) myFileUrl
                            .openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap[0] = BitmapFactory.decodeStream(is);
                    is.close();

                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    bitmap[0] = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    bitmap[0] = null;
                }
            }
        }).start();

        return bitmap[0];
    }
}
