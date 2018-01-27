package kuzhelko_dmitry.simplenotes.presentation.notesList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.presentation.Application.App;
import kuzhelko_dmitry.simplenotes.presentation.notesList.presenter.NotesPresenter;

public class NotesActivity extends MvpAppCompatActivity implements INotesView {

    private LinearLayoutManager layoutManager;
    private NotesAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.empty_screen_state)
    ImageView emptyScreen;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    NotesPresenter mNotesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();

        App.getComponent().inject(this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);

        mNotesPresenter.setScreen();

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
        Log.i("TEST", "startDetailActivity");
        startActivity(intent);
    }


    @OnClick({R.id.fab})
    void onClickAdd(View view) {
        Log.i("TEST", "onClickAdd");
        mNotesPresenter.addNote();
    }

    @Override
    public void deleteNote(int position) {
        adapter.removeNote(position);
    }


    private void setToolbar() {
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