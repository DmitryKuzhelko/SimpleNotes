package kuzhelko_dmitry.simplenotes.domain.interactors.detailNote;

import android.content.Context;

import kuzhelko_dmitry.simplenotes.data.INotesRepository;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NoteDetailInteractor implements INoteDetailInteractor {

    private Context context;
    private INotesRepository notesRepository;

    public NoteDetailInteractor(Context context, INotesRepository notesRepository) {
        this.context = context;
        this.notesRepository = notesRepository;
    }

    @Override
    public Note getNote(String noteId) {
        return notesRepository.getNote(noteId);
    }

    @Override
    public void removeNote(String noteId) {
        notesRepository.removeNote(noteId);
    }

    @Override
    public void createOrUpdateNote(Note note) {
        notesRepository.createOrUpdateNote(note);
    }

}