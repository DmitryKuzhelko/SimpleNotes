package kuzhelko_dmitry.simplenotes.domain.interactors.notesList;

import java.util.List;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public interface INotesInteractor {

    List<Note> getNotes();

    void removeNote(String noteId);

    void createOrUpdateNote(Note note);
}
