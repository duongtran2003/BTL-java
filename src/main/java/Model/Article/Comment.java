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
public class Comment {
    private int comment_id, likes, dislikes;
    private String comment_content;
    private Date comment_time;
    private Article article;
    private UserFake user; // fake

    public Comment(int comment_id, int likes, int dislikes, String comment_content, 
            Date comment_time, Article article, UserFake user) {
        this.comment_id = comment_id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comment_content = comment_content;
        this.comment_time = comment_time;
        this.article = article;
        this.user = user;
    }
    
    
}
