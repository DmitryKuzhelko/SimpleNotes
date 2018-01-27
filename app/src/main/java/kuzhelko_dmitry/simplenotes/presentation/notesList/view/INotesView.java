package kuzhelko_dmitry.simplenotes.presentation.notesList.view;

import android.content.Intent;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public interface INotesView extends MvpView {

    void addDataToAdapter(List<Note> notes);

    void startDetailActivity(Intent intent);

    void deleteNote(int position);

    void hideEmptyScreen();

    void showEmptyScreen();
}
