package com.pritamprasad.helloworld.Utility;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by jarvis on 1/28/17.
 */

public class ToastHandler {

    private final Context context;
    private Toast toast = null;

    public ToastHandler(Context context) {
        this.context = context;
    }

    /**
     * Show Toast
     * @param message
     */
    public void showMsgAsToast(String message)
    {
        toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }



}
