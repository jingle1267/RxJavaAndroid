package com.ihongqiqu.rxjavaandroid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.URL;

/**
 * 异步下载图片，然后展示到页面上
 */
public class SimpleActivity extends AppCompatActivity {

    @BindView(R.id.btn_show_pic)
    Button btnShowPic;
    @BindView(R.id.iv_web_pic)
    ImageView ivWebPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_show_pic)
    public void onViewClicked() {

        String url = "https://avatars2.githubusercontent.com/u/3887795?v=2&s=60";

        // 1.将网络地址转换为Drawable
        Function<String, Drawable> str2Drawable = new Function<String, Drawable>() {
            @Override
            public Drawable apply(@NonNull String s) throws Exception {
                Drawable drawable = null;
                try {
                    drawable = Drawable.createFromStream(new URL(s).openStream(), "src");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        };

        // 2.将drawable先是到imageview上
        Consumer<Drawable> drawableConsumer = new Consumer<Drawable>() {
            @Override
            public void accept(Drawable drawable) throws Exception {
                ivWebPic.setImageDrawable(drawable);
            }
        };

        // 3.控制步骤1和步骤2执行线程
        Observable.just(url)
                .map(str2Drawable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drawableConsumer);

    }
}
