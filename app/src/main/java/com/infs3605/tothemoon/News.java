package com.infs3605.tothemoon;

/*
INFS3605 Capstone Project T1 2021
To the Moon
Caitlin O'Dowd z5183007
Sharon Cheung z5162825
Neil Matani z5162753
Aiden Mansley z5265120
Connor Williams z5189800
Timothy Baker z5162709
*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//News class required by News API
public class News {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResult")
    @Expose
    private int totalResult;

    @SerializedName("articles")
    @Expose
    private List<Article> article;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
