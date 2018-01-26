package kuzhelko_dmitry.simplenotes.domain.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by kuzhe on 26.01.2018.
 */

public class Note extends RealmObject {

    @PrimaryKey
    @Required
    private Integer id;
    private String title;
    private String description;

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
