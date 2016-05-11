package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by elton.melo on 04/05/2016.
 */
public class TOPhoto {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("backdrops")
    @Expose
    private ArrayList<BackdropModel> backdrops;

    @SerializedName("posters")
    @Expose
    private ArrayList<PosterModel> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<BackdropModel> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<BackdropModel> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<PosterModel> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<PosterModel> posters) {
        this.posters = posters;
    }
}
