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
import kuzhelko_dmitry.simplenotes.utils.App;
import kuzhelko_dmitry.simplenotes.presentation.notesList.presenter.NotesPresenter;
import kuzhelko_dmitry.simplenotes.utils.Constants;

public class NotesActivity extends MvpAppCompatActivity implements INotesView {

    private LinearLayoutManager layoutManager;
    private NotesAdapter adapter;
    private Toolbar toolbar;

    @BindView(R.id.empty_screen_state)
    ImageView emptyScreen;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    NotesPresenter daggerPresenter;

    @InjectPresenter
    NotesPresenter presenter;

    @ProvidePresenter
    NotesPresenter daggerPresenter() {
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

        adapterClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setScreen();
    }

    private void adapterClickListener() {
        adapter.setClickListener(new NotesAdapter.ClickListener() {
            @Override
            public void onItemClick(Note note, int position) {
                presenter.editClick(note.getId(), position);
            }

            @Override
            public void onEditClick(Note note, int position) {
                presenter.editClick(note.getId(), position);
            }

            @Override
            public void onDeleteClick(Note note, int position) {
                presenter.deleteClick(note, position);
                presenter.setScreen();
            }
        });
    }

    @Override
    public void addDataToAdapter(List<Note> notes) {
        adapter.updateAdapter(notes);
    }

    @Override
    public void startDetailActivity(Intent intent) {
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String id = data.getStringExtra(Constants.NOTE_ID);
        String title = data.getStringExtra(Constants.TITLE);
        String description = data.getStringExtra(Constants.DESCRIPTION);
        presenter.createOrUpdateNote(id, title, description);
    }


    @OnClick({R.id.fab})
    void onClickAdd(View view) {
        presenter.addNote();
    }

    @Override
    public void addNote() {
        adapter.addNote();
    }

    @Override
    public void deleteNote(int position) {
        adapter.removeNote(position);
    }

    @Override
    public void updateNote() {
        adapter.updateNote();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.all_notes));
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