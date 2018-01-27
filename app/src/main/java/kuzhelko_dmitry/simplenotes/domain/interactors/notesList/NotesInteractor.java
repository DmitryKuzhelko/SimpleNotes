package kuzhelko_dmitry.simplenotes.domain.interactors.notesList;

import android.content.Context;

import java.util.List;

import kuzhelko_dmitry.simplenotes.data.INotesRepository;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NotesInteractor implements INotesInteractor {

    private Context context;
    private INotesRepository notesRepository;

    public NotesInteractor(Context context, INotesRepository notesRepository) {
        this.context = context;
        this.notesRepository = notesRepository;
    }

    @Override
    public List<Note> getNotes() {
        return notesRepository.getNotes();
    }

    @Override
    public void removeNote(String noteId) {
        notesRepository.removeNote(noteId);
    }
}
