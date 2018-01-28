package kuzhelko_dmitry.simplenotes.DI.component;

import dagger.Component;
import kuzhelko_dmitry.simplenotes.DI.modules.NoteDetailPresenterModule;
import kuzhelko_dmitry.simplenotes.DI.modules.NotesPresenterModule;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.NoteDetailActivity;
import kuzhelko_dmitry.simplenotes.presentation.notesList.view.NotesActivity;

/**
 * Created by kuzhe on 27.01.2018.
 */

@Component(modules = {NotesPresenterModule.class, NoteDetailPresenterModule.class})
public interface AppComponent {

    void inject(NotesActivity notesActivity);
    void inject(NoteDetailActivity noteDetailActivity);
}