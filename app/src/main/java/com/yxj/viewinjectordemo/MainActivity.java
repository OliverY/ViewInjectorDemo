package com.yxj.viewinjectordemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yxj.viewinjectordemo.ioc.annotation.InjectView;
import com.yxj.viewinjectordemo.ioc.annotation.ViewInjectUtils;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tvContent)
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInjectUtils.inject(this);

        tvContent.setText("hurry up");
    }

}
