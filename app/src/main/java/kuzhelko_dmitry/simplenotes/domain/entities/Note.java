package kuzhelko_dmitry.simplenotes.domain.entities;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class Note extends RealmObject {

    @PrimaryKey
    @Required
    private String id;
    private String title;
    private String description;

    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    public Note(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
