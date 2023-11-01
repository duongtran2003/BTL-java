/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

import java.sql.Date;

/**
 *
 * @author Hanh
 */
public class Article {
    private int article_id, likes, dislikes, reports;
    private String article_name, article_category, article_tag, content, image;
    private Date time_submit, time_accept;
    private boolean stt;
    private UserFake user; // fake

    public Article(int article_id, int likes, int dislikes, int reports, 
            String article_name, String article_category, String article_tag, 
            String content, String image, Date time_submit, Date time_accept, 
            boolean stt, UserFake user) {
        this.article_id = article_id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reports = reports;
        this.article_name = article_name;
        this.article_category = article_category;
        this.article_tag = article_tag;
        this.content = content;
        this.image = image;
        this.time_submit = time_submit;
        this.time_accept = time_accept;
        this.stt = stt;
        this.user = user;
    }
    
    
}