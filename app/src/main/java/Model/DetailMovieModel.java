package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by eltonmelo on 4/23/16.
 */
public class DetailMovieModel extends RealmObject {

    @SerializedName("adult")
    @Expose
    private boolean adult;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("belongs_to_collection")
    @Expose
    private BelongsToCollection belongsToCollection;

    @SerializedName("budget")
    @Expose
    private int budget;

    @SerializedName("genres")
    @Expose
    private RealmList<GenreModel> genres;
//    private ArrayList<GenreModel> genres;

    @SerializedName("homepage")
    @Expose
    private String homepage;

    @SerializedName("id")
    @Expose
    private int idDetailMovie;

    @SerializedName("imdb_id")
    @Expose
    private String imdbId;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("popularity")
    @Expose
    private double popularity;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("production_companies")
    @Expose
    private RealmList<ProductionCompanie> productionCompanies;
//    private ArrayList<ProductionCompanie> productionCompanies;

    @SerializedName("production_countries")
    @Expose
    private RealmList<ProductionCountrie> productionCountries;
//    private ArrayList<ProductionCountrie> productionCountries;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("revenue")
    @Expose
    private int revenue;

    @SerializedName("runtime")
    @Expose
    private int runtime;

    @SerializedName("spoken_languages")
    @Expose
    private RealmList<SpokenLanguage> spokenLanguages;
//    private ArrayList<SpokenLanguage> spokenLanguages;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("video")
    @Expose
    private boolean video;

    @SerializedName("vote_average")
    @Expose
    private int voteAverage;

    @SerializedName("vote_count")
    @Expose
    private int voteCount;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getIdDetailMovie() {
        return idDetailMovie;
    }

    public void setIdDetailMovie(int idDetailMovie) {
        this.idDetailMovie = idDetailMovie;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public RealmList<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<GenreModel> genres) {
        this.genres = genres;
    }

    public RealmList<ProductionCompanie> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(RealmList<ProductionCompanie> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public RealmList<ProductionCountrie> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(RealmList<ProductionCountrie> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public RealmList<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(RealmList<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }
}
