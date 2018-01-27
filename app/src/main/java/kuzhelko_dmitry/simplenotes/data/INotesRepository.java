package kuzhelko_dmitry.simplenotes.data;

import java.util.List;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 27.01.2018.
 */

public interface INotesRepository {

    List<Note> getNotes();

    void removeNote(String noteId);
}
