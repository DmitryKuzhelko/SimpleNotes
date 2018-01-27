package kuzhelko_dmitry.simplenotes.domain.interactors.detailNote;

import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public interface INoteDetailInteractor {

    Note getNote(String noteId);
}
