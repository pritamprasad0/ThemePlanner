package com.pritamprasad.helloworld.Utility;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Extension of Toast
 */

public class ToastHandler {

    private final Context context;

    public ToastHandler(Context context) {
        this.context = context;
    }

    /**
     * Show Toast
     * @param message message to display
     */
    public void showMsgAsToast(String message)
    {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }



}
