package com.sunsun.shadowtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sunsun.constant.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
        setRequestPermissions();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, PluginLoadActivity.class);
        intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, Constant.PART_KEY_PLUGIN_MAIN_APP);
        intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.sunsun.app_lib.splash.SplashActivity");
        startActivity(intent);
    }

    private void setRequestPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        throw new RuntimeException("必须赋予权限.");
                    }
                }
            }
        }
    }

}
