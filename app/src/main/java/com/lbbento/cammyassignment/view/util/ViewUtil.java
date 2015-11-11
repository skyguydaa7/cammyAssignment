package com.lbbento.cammyassignment.view.util;

import android.content.Context;
import android.os.Build;

/**
 * Created by lbbento on 10/11/15.
 */
public class ViewUtil {

    /**
     * find height of the status bar
     * @param ctx Context
     * @return int  status bar height
     */
    public static int getStatusBarHeight(Context ctx) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = ctx.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

}
