package kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import kuzhelko_dmitry.simplenotes.data.NotesRepository;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.domain.interactors.detailNote.NoteDetailInteractor;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.INoteDetailView;

/**
 * Created by kuzhe on 26.01.2018.
 */

@InjectViewState
public class NoteDetailPresenter extends MvpPresenter<INoteDetailView> {

    private Context context;
    private NoteDetailInteractor interactor;

    public NoteDetailPresenter() {
    }

    public NoteDetailPresenter(Context context, NoteDetailInteractor interactor) {
        this.context = context;
        this.interactor = new NoteDetailInteractor(context, new NotesRepository());
    }

    public void getDetailInfo(String noteId) {
        getViewState().fillInFields(interactor.getNote(noteId));
    }

    public void createOrUpdateNote(String noteId) {
        if (noteId == null) {
            interactor.createOrUpdateNote(getViewState().getUserData());
        } else {
            Note note = getViewState().getUserData();
            note.setId(noteId);
            interactor.createOrUpdateNote(note);
        }
        getViewState().closeActivity();
    }
}
