package com.rikkei.android_trainning;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private Button btnFragment1;
    private Button btnFragment2;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        btnFragment1 = findViewById(R.id.btnFragment1);
        btnFragment2 = findViewById(R.id.btnFragment2);
        btnFragment1.setOnClickListener(this);
        btnFragment2.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFragment1:
                fragment1 = new Fragment1();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.flMain, fragment1);
                fragmentTransaction1.addToBackStack(Fragment1.class.toString());
                fragmentTransaction1.commit();
                break;
            case R.id.btnFragment2:
                fragment2 = new Fragment2();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.flMain, fragment2);
                fragmentTransaction2.addToBackStack(Fragment2.class.toString());
                fragmentTransaction2.commit();
                break;
            default:
                break;
        }
    }
}
