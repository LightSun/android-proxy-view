package com.heaven7.android.proxyview.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1();

        startActivity(new Intent(this, TestProxyViewAc.class));
    }
    //get system resource id. from 'com.android.internal.R'
    private void test1() {
        int id = Resources.getSystem().getIdentifier("ic_text_dot", "drawable", "android");
        System.out.println("LinearLayout_Layout : id = " + id); //ok
        try {
            //attr 可以拿到。styleable 拿不到
            id = Resources.getSystem().getIdentifier("layout_weight", "attr", "android");
            System.out.println("LinearLayout_Layout : id = " + id);
            id = Resources.getSystem().getIdentifier("orientation", "attr", "android");
            System.out.println("LinearLayout_Layout : id = " + id);

            //failed
            Field field = Class.forName("com.android.internal.R$Styleable").getField("LinearLayout");
            System.out.println("LinearLayout_Layout : id = " + field.get(null));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
