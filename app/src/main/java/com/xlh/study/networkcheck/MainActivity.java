package com.xlh.study.networkcheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xlh.study.networkcheck.annotation.NetworkCheck;
import com.xlh.study.networkcheck.annotation.NoNetworkShow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @NetworkCheck
    public void networkCheck(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @NoNetworkShow
    public void noNetworkShow() {
        new AlertDialog.Builder(this)
                .setMessage("没有网络")
                .setNegativeButton("知道了", null)
                .show();
    }

}
