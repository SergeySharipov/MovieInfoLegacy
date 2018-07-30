package ca.sharipov.serhii.movieinfo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ca.sharipov.serhii.movieinfo.Constants;

public class Movie {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("budget")
    private int budget;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagline;

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("popularity")
    private double popularity;

    public Movie(int id, String title, String overview, String releaseDate, List<Genre> genres,
                 boolean adult, String originalLanguage, String originalTitle, int budget,
                 String homepage, int revenue, int runtime, String status, String tagline,
                 String posterPath, String backdropPath, String imdbId, boolean video,
                 double voteAverage, int voteCount, double popularity) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.adult = adult;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.budget = budget;
        this.homepage = homepage;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.imdbId = imdbId;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.popularity = popularity;
    }

    public String getPosterLink() {
        return Constants.TMDB_POSTER_URL + posterPath;
    }

    public String getBackdropLink() {
        return Constants.TMDB_BACKDROP_URL + backdropPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
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

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
