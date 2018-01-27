package kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import kuzhelko_dmitry.simplenotes.data.NoteDetailRepository;
import kuzhelko_dmitry.simplenotes.domain.interactors.detailNote.DetailNoteInteractor;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.INoteDetailView;

/**
 * Created by kuzhe on 26.01.2018.
 */

@InjectViewState
public class NoteDetailPresenter extends MvpPresenter<INoteDetailView> {

    private Context context;
    private DetailNoteInteractor interactor;

    public NoteDetailPresenter() {
    }

    public NoteDetailPresenter(Context context, DetailNoteInteractor iteractor) {
        this.context = context;
        this.interactor = new DetailNoteInteractor(context, new NoteDetailRepository());
    }

    public void getDetailInfo(String noteId) {
        getViewState().fillInFields(interactor.getNote(noteId));
    }
}
