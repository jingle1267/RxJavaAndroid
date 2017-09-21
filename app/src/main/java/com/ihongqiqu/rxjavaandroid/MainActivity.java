package com.ihongqiqu.rxjavaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_simple)
    Button btnSimple;
    @BindView(R.id.btn_middle)
    Button btnMiddle;
    @BindView(R.id.btn_others)
    Button btnOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_simple, R.id.btn_middle, R.id.btn_others})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
                break;
            case R.id.btn_middle:
                startActivity(new Intent(MainActivity.this, MiddleActivity.class));
                break;
            case R.id.btn_others:
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
                break;
        }
    }
}
