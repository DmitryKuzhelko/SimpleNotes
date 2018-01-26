package kuzhelko_dmitry.simplenotes.presentation.notesList.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.presentation.notesList.presenter.NotesPresenter;

public class NotesActivity extends MvpAppCompatActivity implements INotesView {

    @InjectPresenter
    NotesPresenter mNotesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
