package kuzhelko_dmitry.simplenotes.presentation.detailNote.view;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NoteDetailActivity extends MvpAppCompatActivity implements INoteDetailView {

    @InjectPresenter
    NoteDetailPresenter mNoteDetailPresenter;

}
