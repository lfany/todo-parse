package cc.ifnot.todoparse;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.parse.Parse;

/**
 * Created by dp on 2016/11/22.
 */

public class TodoApp extends Application {
    private static final String SERVER_ADDRESS = "https://parse.ifnot.cc";
    private static final String appId = "cL8taRQ1PItCTj9daMKCDLYaMwY5KKpd7fFt2m1E";
    private static final String TAG = "Todo";

    @Override
    public void onCreate() {
        super.onCreate();
//        initialize parse SDK
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(appId)
                .server(SERVER_ADDRESS)
                .enableLocalDataStore()
                .build()
        );

        /**
         * initialize logger
         * */

        Logger.init(TAG)
                .methodCount(3)
                .methodOffset(2)
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);

    }
}
