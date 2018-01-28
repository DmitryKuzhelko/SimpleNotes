package kuzhelko_dmitry.simplenotes.data;

import java.util.List;

import io.realm.Realm;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;
import kuzhelko_dmitry.simplenotes.utils.Constants;

/**
 * Created by kuzhe on 27.01.2018.
 */

public class NotesRepository implements INotesRepository {

    private Realm mRealm = Realm.getDefaultInstance();

    public NotesRepository() {
    }

    @Override
    public Note getNote(String noteId) {
        return mRealm.where(Note.class).equalTo(Constants.NOTE_ID, noteId).findFirst();
    }

    @Override
    public List<Note> getNotes() {
        return mRealm.where(Note.class).findAll();
    }

    @Override
    public void removeNote(final String noteId) {
        mRealm.executeTransaction(realm -> getNote(noteId).deleteFromRealm());
    }

    @Override
    public void createOrUpdateNote(Note note) {
        mRealm.executeTransaction(realm -> mRealm.copyToRealmOrUpdate(note));
    }
}
