package kuzhelko_dmitry.simplenotes.presentation.notesList.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.domain.interactors.notesList.NotesInteractor;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.NoteDetailActivity;
import kuzhelko_dmitry.simplenotes.presentation.notesList.view.INotesView;

/**
 * Created by kuzhe on 26.01.2018.
 */

@InjectViewState
public class NotesPresenter extends MvpPresenter<INotesView> {

    Context context;
    public NotesInteractor interactor;

    public NotesPresenter() {
    }

    @Inject
    public NotesPresenter(Context context, NotesInteractor interactor) {
        this.context = context;
        this.interactor = interactor;
    }

    public void setScreen() {
        if (interactor.getNotes() == null) {
            getViewState().showEmptyScreen();
        } else {
            getViewState().hideEmptyScreen();
            getViewState().addDataToAdapter(interactor.getNotes());
        }
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

    public void addNote() {
        getViewState().startDetailActivity(NoteDetailActivity.getNoteIntent(context));
    }

    public void createOrUpdateNote(String noteId, String title, String description) {
        Note note;
        if (noteId == null) {
            note = new Note(title, description);
            interactor.createOrUpdateNote(note);
        } else {
            note = new Note(noteId, title, description);
            interactor.createOrUpdateNote(note);
        }
    }
}