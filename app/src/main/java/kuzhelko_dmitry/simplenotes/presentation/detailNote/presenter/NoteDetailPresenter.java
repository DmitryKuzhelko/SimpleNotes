package kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import kuzhelko_dmitry.simplenotes.domain.interactors.detailNote.NoteDetailInteractor;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.INoteDetailView;

/**
 * Created by kuzhe on 26.01.2018.
 */

@InjectViewState
public class NoteDetailPresenter extends MvpPresenter<INoteDetailView> {

    public NoteDetailInteractor interactor;

    public NoteDetailPresenter() {
    }

    public NoteDetailPresenter(NoteDetailInteractor interactor) {
        this.interactor = interactor;
    }

    public void getDetailInfo(String noteId) {
        if (noteId != null) {
            getViewState().fillInFields(interactor.getNote(noteId));
        }
    }
}
