package ru.mirea.Gavrilova.movies;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTO {
    @JsonProperty(value = "title")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String title;
    @JsonProperty(value = "year")
    private int year;
    @JsonProperty(value = "cast")
    private List<String> cast;
    @JsonProperty(value = "genres")
    private List<String> genres;

    public DTO() {
    }
    public DTO(String title, int year, List<String> cast, List<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
    }
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    public List<String> getCast() { return cast; }
    public List<String> getGenres() { return genres; }

    @Override
    public String toString() {
        return "Title = " + title +
                "\nYear = " + year +
                "\nCast = " + cast +
                "\nGenres = " + genres;
    }
}
