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
import kuzhelko_dmitry.simplenotes.utils.App;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter;
import kuzhelko_dmitry.simplenotes.utils.Constants;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NoteDetailActivity extends MvpAppCompatActivity implements INoteDetailView {

    private String noteId;
    private Toolbar toolbar;


    @BindView(R.id.etNoteTitle)
    EditText noteTitle;

    @BindView(R.id.etNoteDescription)
    EditText noteDescription;

    @Inject
    NoteDetailPresenter daggerPresenter;

    @InjectPresenter
    NoteDetailPresenter mNoteDetailPresenter;

    @ProvidePresenter
    NoteDetailPresenter daggerPresenter() {
        App.getComponent().inject(this);
        return daggerPresenter;
    }

    public static Intent getNoteIntent(Context context, String noteId, int position) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        intent.putExtra(Constants.NOTE_ID, noteId);
        intent.putExtra(Constants.POSITION, position);
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

        noteId = getIntent().getStringExtra(Constants.NOTE_ID);
        mNoteDetailPresenter.getDetailInfo(noteId);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.add_note));
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
                intent.putExtra(Constants.NOTE_ID, noteId);
                intent.putExtra(Constants.TITLE, noteTitle.getText().toString());
                intent.putExtra(Constants.DESCRIPTION, noteDescription.getText().toString());
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