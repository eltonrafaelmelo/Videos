package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eltonmelo on 4/23/16.
 */
public class BelongsToCollection {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

}
