package com.yueyue.recyclerviewtest;

import android.app.Application;

import com.bugtags.library.Bugtags;

/**
 * author : yueyue on 2018/3/25 20:58
 * desc   :
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Bugtags.start("185fa552a152393753684b1c4097677b",
                this,
                Bugtags.BTGInvocationEventBubble);
    }
}
