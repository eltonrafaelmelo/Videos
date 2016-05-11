package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by elton.melo on 04/05/2016.
 */
public class PosterModel {

    @SerializedName("aspect_ratio")
    @Expose
    private double aspectRatio;

    @SerializedName("file_path")
    @Expose
    private String filePath;

    @SerializedName("height")
    @Expose
    private int height;

    @SerializedName("iso_639_1")
    @Expose
    private String iso_639_1;

    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    @SerializedName("vote_count")
    @Expose
    private int voteCount;

    @SerializedName("width")
    @Expose
    private int width;

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
