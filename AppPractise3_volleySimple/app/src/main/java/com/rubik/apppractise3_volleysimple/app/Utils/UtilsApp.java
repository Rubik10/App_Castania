package com.rubik.apppractise3_volleysimple.app.Utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Rubik on 29/7/16.
 */
public class UtilsApp {

    public static void showToast(Context cxt, String msg) {
        Toast.makeText(cxt,msg, Toast.LENGTH_LONG).show();
    };
    public static void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    };
}
