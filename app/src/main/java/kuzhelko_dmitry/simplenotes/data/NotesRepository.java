package kuzhelko_dmitry.simplenotes.data;

import io.realm.Realm;
import io.realm.RealmResults;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 27.01.2018.
 */

public class NotesRepository implements INotesRepository {

    private static final String NOTE_ID = "note id";
    private Realm mRealm = Realm.getDefaultInstance();

    @Override
    public RealmResults<Note> getNotes() {
        return Realm.getDefaultInstance().where(Note.class).findAll();
    }

    @Override
    public void removeNote(final String noteId) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.where(Note.class).equalTo(NOTE_ID, noteId).findFirst().deleteFromRealm();
            }
        });
    }
}
