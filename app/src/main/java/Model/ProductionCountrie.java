package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eltonmelo on 4/23/16.
 */
public class ProductionCountrie {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso;

    @SerializedName("name")
    @Expose
    private String name;
}
