package kuzhelko_dmitry.simplenotes.DI.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import kuzhelko_dmitry.simplenotes.data.NotesRepository;
import kuzhelko_dmitry.simplenotes.domain.interactors.detailNote.NoteDetailInteractor;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter;

/**
 * Created by kuzhe on 27.01.2018.
 */

@Module
public class NoteDetailPresenterModule {

    Context mContext;

    public NoteDetailPresenterModule(Context context) {
        mContext = context;
    }

    @Provides
    NoteDetailPresenter provideNoteDetailPresenter(NoteDetailInteractor noteDetailInteractor) {
        return new NoteDetailPresenter(noteDetailInteractor);
    }

    @Provides
    NoteDetailInteractor provideNoteDetailInteractor(NotesRepository notesRepository) {
        return new NoteDetailInteractor(notesRepository);
    }
}
