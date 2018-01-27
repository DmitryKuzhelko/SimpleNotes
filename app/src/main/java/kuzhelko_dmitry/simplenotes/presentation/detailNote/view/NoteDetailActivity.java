package kuzhelko_dmitry.simplenotes.presentation.detailNote.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NoteDetailActivity extends MvpAppCompatActivity implements INoteDetailView {

    private static final String NOTE_ID = "note id";
    private String noteId;

    @BindView(R.id.etNoteTitle)
    EditText noteTitle;

    @BindView(R.id.etNoteDescription)
    EditText noteDescription;

    @InjectPresenter
    NoteDetailPresenter mNoteDetailPresenter;

    public static Intent getNoteIntent(Context context, String noteId) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        intent.putExtra(NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        noteId = getIntent().getStringExtra(NOTE_ID);
        mNoteDetailPresenter.getDetailInfo(noteId);
    }

    @Override
    public void fillInFields(Note note) {
        noteTitle.setText(note.getTitle());
        noteDescription.setText(note.getDescription());
    }
}
