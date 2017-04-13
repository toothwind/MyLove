package com.yaya25001.mylove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BeisaierLove beisaierLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beisaierLove = (BeisaierLove) findViewById(R.id.love);
    }

    public void change(View view) {
        beisaierLove.start();
    }
}
