package kuzhelko_dmitry.simplenotes.presentation.notesList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.presentation.Application.App;
import kuzhelko_dmitry.simplenotes.presentation.detailNote.view.NoteDetailActivity;
import kuzhelko_dmitry.simplenotes.presentation.notesList.presenter.NotesPresenter;

public class NotesActivity extends MvpAppCompatActivity implements INotesView {

    private LinearLayoutManager layoutManager;
    private NotesAdapter adapter;
    private Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.empty_screen_state)
    ImageView emptyScreen;

    @Inject
    NotesPresenter daggerPresenter;

    @InjectPresenter
    NotesPresenter notesPresenter;

    @ProvidePresenter
    NotesPresenter daggerPresenter(){
        App.getComponent().inject(this);
        return daggerPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);

        notesPresenter.setScreen();

        adapterClickListener();
    }

    private void adapterClickListener() {
        adapter.setClickListener(new NotesAdapter.ClickListener() {
            @Override
            public void onItemClick(Note note) {
                notesPresenter.itemClick(note.getId());
            }

            @Override
            public void onEditClick(Note note, int position) {
                notesPresenter.editClick(note.getId());
            }

            @Override
            public void onDeleteClick(Note note, int position) {
                notesPresenter.deleteClick(note, position);
            }
        });
    }


    @Override
    public void addDataToAdapter(List<Note> notes) {
        adapter.updateAdapter(notes);
    }

    @Override
    public void startDetailActivity(Intent intent) {
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String id = data.getStringExtra(NoteDetailActivity.NOTE_ID);
        String title = data.getStringExtra(NoteDetailActivity.TITLE);
        String description = data.getStringExtra(NoteDetailActivity.DESCRIPTION);
        notesPresenter.createOrUpdateNote(id, title, description);
    }


    @OnClick({R.id.fab})
    void onClickAdd(View view) {
        notesPresenter.addNote();
    }

    @Override
    public void deleteNote(int position) {
        adapter.removeNote(position);
    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.all_notes));
    }

    @Override
    public void showEmptyScreen() {
        emptyScreen.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyScreen() {
        emptyScreen.setVisibility(View.GONE);
    }
}