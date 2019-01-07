package asus.example.com.fitnessapp;

import android.graphics.Bitmap;
import android.view.View;

class Screenshot {
    private static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
}
    static Bitmap takescreenshotOfRootView(View v){
        return takescreenshot(v.getRootView());
    }
}