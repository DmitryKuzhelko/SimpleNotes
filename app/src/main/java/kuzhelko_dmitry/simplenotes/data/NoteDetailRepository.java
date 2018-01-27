package kuzhelko_dmitry.simplenotes.data;

import android.support.annotation.NonNull;

import io.realm.Realm;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class NoteDetailRepository implements INoteDetailRepository {

    private static final String NOTE_ID = "note id";
    private Realm mRealm = Realm.getDefaultInstance();

    @Override
    public Note getNote(String noteId) {
        return mRealm.where(Note.class).equalTo(NOTE_ID, noteId).findFirst();
    }

    @Override
    public void removeNote(final String noteId) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                getNote(noteId).deleteFromRealm();
            }
        });
    }

    @Override
    public void createNote(final Note note) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                mRealm.copyToRealmOrUpdate(note);
            }
        });
    }
}
