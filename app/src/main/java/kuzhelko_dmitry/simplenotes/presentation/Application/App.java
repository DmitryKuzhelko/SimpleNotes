package kuzhelko_dmitry.simplenotes.presentation.Application;

import android.app.Application;

import io.realm.Realm;
import kuzhelko_dmitry.simplenotes.DI.component.AppComponent;
import kuzhelko_dmitry.simplenotes.DI.component.DaggerAppComponent;
import kuzhelko_dmitry.simplenotes.DI.modules.NoteDetailPresenterModule;
import kuzhelko_dmitry.simplenotes.DI.modules.NotesPresenterModule;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        component = DaggerAppComponent.builder()
                .notesPresenterModule(new NotesPresenterModule(this))
                .noteDetailPresenterModule(new NoteDetailPresenterModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }

}
