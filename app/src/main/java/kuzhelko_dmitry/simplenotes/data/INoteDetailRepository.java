package kuzhelko_dmitry.simplenotes.data;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 27.01.2018.
 */

public interface INoteDetailRepository {

    Note getNote(String noteId);

    void removeNote(String noteId);

    void createNote(Note note);
}
