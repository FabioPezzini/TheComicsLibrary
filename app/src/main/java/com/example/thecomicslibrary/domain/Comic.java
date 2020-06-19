package com.example.thecomicslibrary.domain;


import java.io.Serializable;

public class Comic implements Serializable {
    private String issue_title;
    private String link_albo;
    private String issue_subtitle;
    private String serie_title;
    private String serie_year;
    private String issue_date;
    private String issue_originalstories;
    private String publisher;
    private String issue_link_image;
    private String issue_description;
    private String serie_numbers;

    public String getIssue_title() {
        return issue_title;
    }

    public void setIssue_title(String issue_title) {
        this.issue_title = issue_title;
    }

    public String getLink_albo() {
        return link_albo;
    }

    public void setLink_albo(String link_albo) {
        this.link_albo = link_albo;
    }

    public String getIssue_subtitle() {
        return issue_subtitle;
    }

    public void setIssue_subtitle(String issue_subtitle) {
        this.issue_subtitle = issue_subtitle;
    }

    public String getSerie_title() {
        return serie_title;
    }

    public void setSerie_title(String serie_title) {
        this.serie_title = serie_title;
    }

    public String getSerie_year() {
        return serie_year;
    }

    public void setSerie_year(String serie_year) {
        this.serie_year = serie_year;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getIssue_originalstories() {
        return issue_originalstories;
    }

    public void setIssue_originalstories(String issue_originalstories) {
        this.issue_originalstories = issue_originalstories;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIssue_link_image() {
        return issue_link_image;
    }

    public void setIssue_link_image(String issue_link_image) {
        this.issue_link_image = issue_link_image;
    }

    public String getIssue_description() {
        return issue_description;
    }

    public void setIssue_description(String issue_description) {
        this.issue_description = issue_description;
    }

    public String getSerie_numbers() {
        return serie_numbers;
    }

    public void setSerie_numbers(String serie_numbers) {
        this.serie_numbers = serie_numbers;
    }

    public Comic(String issue_title, String link_albo, String issue_subtitle, String serie_title, String serie_year, String issue_date, String issue_originalstories, String publisher, String issue_link_image, String issue_description, String serie_numbers) {
        this.issue_title = issue_title;
        this.link_albo = link_albo;
        this.issue_subtitle = issue_subtitle;
        this.serie_title = serie_title;
        this.serie_year = serie_year;
        this.issue_date = issue_date;
        this.issue_originalstories = issue_originalstories;
        this.publisher = publisher;
        this.issue_link_image = issue_link_image;
        this.issue_description = issue_description;
        this.serie_numbers = serie_numbers;
    }

    public Comic() {
    }
}
