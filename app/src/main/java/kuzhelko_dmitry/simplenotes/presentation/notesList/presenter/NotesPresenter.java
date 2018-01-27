package kuzhelko_dmitry.simplenotes.presentation.notesList.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import kuzhelko_dmitry.simplenotes.data.NotesRepository;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.domain.interactors.notesList.NotesInteractor;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.NoteDetailActivity;
import kuzhelko_dmitry.simplenotes.presentation.notesList.view.INotesView;

/**
 * Created by kuzhe on 26.01.2018.
 */

@InjectViewState
public class NotesPresenter extends MvpPresenter<INotesView> {

    private Context context;
    private NotesInteractor interactor;

    public NotesPresenter() {
    }

    public NotesPresenter(Context context) {
        this.context = context;
        this.interactor = new NotesInteractor(context, new NotesRepository());
    }

    public void getNotes() {
        getViewState().addDataToAdapter(interactor.getNotes());
    }

    public void itemClick(String noteId) {
        getViewState().startDetailActivity(NoteDetailActivity.getNoteIntent(context, noteId));
    }

    public void editClick(String noteId) {
        getViewState().startDetailActivity(NoteDetailActivity.getNoteIntent(context, noteId));
    }

    public void deleteClick(Note note, int position) {
        getViewState().deleteNote(position);
        interactor.removeNote(note.getId());
    }
}
