package ca.sharipov.serhii.movieinfo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieBriefListResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<MovieBrief> results;

    public MovieBriefListResponse(int page, int totalResults, int totalPages, List<MovieBrief> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieBrief> getResults() {
        return results;
    }

    public void setResults(List<MovieBrief> results) {
        this.results = results;
    }
}
