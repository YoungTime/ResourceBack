package com.example.duzeming.resourceback.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.duzeming.resourceback.R;

import java.lang.reflect.Method;

/**
 * Created by DuZeming on 2018/3/23.
 */
public class SnackBarMethod {
    public static void setSnackbarColor(View view,String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout ve = (Snackbar.SnackbarLayout)snackbar.getView();
        ve.setBackgroundColor(0xff40E0D0);//设置背景Se
        ve.setAlpha(0.9f);//设置透明度
        ((TextView) ve.findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#ffffff"));
        snackbar.show();

    }

}
