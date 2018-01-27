package kuzhelko_dmitry.simplenotes.DI.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import kuzhelko_dmitry.simplenotes.data.NotesRepository;
import kuzhelko_dmitry.simplenotes.domain.interactors.notesList.NotesInteractor;
import kuzhelko_dmitry.simplenotes.presentation.notesList.presenter.NotesPresenter;

/**
 * Created by kuzhe on 27.01.2018.
 */

@Module
public class NotesPresenterModule {

    Context mContext;

    public NotesPresenterModule(Context context) {
        mContext = context;
    }

    @Provides
    NotesPresenter provideNotesPresenter(Context context, NotesInteractor notesInteractor) {
        return new NotesPresenter(context, notesInteractor);
    }

    @Provides
    NotesInteractor provideNotesInteractor(NotesRepository notesRepository) {
        return new NotesInteractor(notesRepository);
    }

    @Provides
    NotesRepository provideNotesRepository() {
        return new NotesRepository();
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}