package kuzhelko_dmitry.simplenotes.presentation.detailNote.view;

import com.arellomobile.mvp.MvpView;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public interface INoteDetailView extends MvpView {

    void fillInFields(Note note);

    Note getUserData();

    void closeActivity();
}
