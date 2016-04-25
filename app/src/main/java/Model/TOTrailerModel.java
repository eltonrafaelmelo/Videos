package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by eltonmelo on 4/25/16.
 */
public class TOTrailerModel {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("results")
    @Expose
    private ArrayList<TrailerModel> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TrailerModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<TrailerModel> results) {
        this.results = results;
    }
}
