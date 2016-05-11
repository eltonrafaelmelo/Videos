package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import io.realm.RealmObject;

/**
 * Created by eltonmelo on 4/23/16.
 */

public class SpokenLanguage extends RealmObject {

    @SerializedName("iso_639_1")
    @Expose
    private String iso;

    @SerializedName("name")
    @Expose
    private String name;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
