package kuzhelko_dmitry.simplenotes.domain.interactors.detailNote;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public interface IDetailNoteInteractor {

    Note getNote(String noteId);

    void removeNote(String noteId);

    void createNote(String title, String description);
}
