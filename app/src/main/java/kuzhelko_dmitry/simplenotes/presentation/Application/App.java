package kuzhelko_dmitry.simplenotes.presentation.Application;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
