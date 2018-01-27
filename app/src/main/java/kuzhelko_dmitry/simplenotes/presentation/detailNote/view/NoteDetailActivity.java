package kuzhelko_dmitry.simplenotes.presentation.detailNote.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.presentation.Application.App;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NoteDetailActivity extends MvpAppCompatActivity implements INoteDetailView {

    public static final String NOTE_ID = "note id";
    public static final String TITLE = "note title";
    public static final String DESCRIPTION = "note description";
    private String noteId;

    @BindView(R.id.etNoteTitle)
    EditText noteTitle;

    @BindView(R.id.etNoteDescription)
    EditText noteDescription;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    @InjectPresenter
    NoteDetailPresenter mNoteDetailPresenter;

    @ProvidePresenter
    NoteDetailPresenter providePresenter() {
        return mNoteDetailPresenter;
    }

    public static Intent getNoteIntent(Context context, String noteId) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        intent.putExtra(NOTE_ID, noteId);
        return intent;
    }

    public static Intent getNoteIntent(Context context) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        setToolbar();
        ButterKnife.bind(this);

        App.getComponent().inject(this);

        noteId = getIntent().getStringExtra(NOTE_ID);
        mNoteDetailPresenter.getDetailInfo(noteId);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.add_note));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                Intent intent = new Intent();
                intent.putExtra(NOTE_ID, noteId);
                intent.putExtra(TITLE, noteTitle.getText().toString());
                intent.putExtra(DESCRIPTION, noteDescription.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillInFields(Note note) {
        noteTitle.setText(note.getTitle());
        noteDescription.setText(note.getDescription());
    }
}