package com.yueyue.photowallfallsdemo;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * author : yueyue on 2018/3/30 11:31
 * desc   :
 */

public class EnvUtil {

    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
