package kuzhelko_dmitry.simplenotes.domain.interactors.detailNote;

import android.content.Context;

import kuzhelko_dmitry.simplenotes.data.INoteDetailRepository;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class DetailNoteInteractor implements IDetailNoteInteractor {

    private Context context;
    private INoteDetailRepository noteDetailRepository;

    public DetailNoteInteractor(Context context, INoteDetailRepository noteDetailRepository) {
        this.context = context;
        this.noteDetailRepository = noteDetailRepository;
    }

    @Override
    public Note getNote(String noteId) {
        return noteDetailRepository.getNote(noteId);
    }

    @Override
    public void removeNote(String noteId) {
        noteDetailRepository.removeNote(noteId);
    }

    @Override
    public void createNote(String title, String description) {
        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        noteDetailRepository.createNote(note);
    }
}