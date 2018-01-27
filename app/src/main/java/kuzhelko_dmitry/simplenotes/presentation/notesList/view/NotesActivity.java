package kuzhelko_dmitry.simplenotes.presentation.notesList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.presentation.notesList.presenter.NotesPresenter;

public class NotesActivity extends MvpAppCompatActivity implements INotesView {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private NotesAdapter adapter;


    @InjectPresenter
    NotesPresenter mNotesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);

        mNotesPresenter.getNotes();

        adapterClickListener();
    }

    private void adapterClickListener() {
        adapter.setClickListener(new NotesAdapter.ClickListener() {
            @Override
            public void onItemClick(Note note) {
                mNotesPresenter.itemClick(note.getId());
            }

            @Override
            public void onEditClick(Note note, int position) {
                mNotesPresenter.editClick(note.getId());
            }

            @Override
            public void onDeleteClick(Note note, int position) {
                mNotesPresenter.deleteClick(note, position);
            }
        });
    }

    @Override
    public void addDataToAdapter(List<Note> notes) {
        adapter.updateAdapter(notes);
    }

    @Override
    public void startDetailActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void deleteNote(int position) {
        adapter.removeNote(position);
    }
}