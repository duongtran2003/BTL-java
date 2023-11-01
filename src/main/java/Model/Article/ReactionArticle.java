/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

/**
 *
 * @author Hanh
 */
public class ReactionArticle {
    private int reaction_article_id;
    private boolean reation_type;
    private UserFake user;
    private Article article;

    public ReactionArticle(int reaction_article_id, boolean reation_type, UserFake user, Article article) {
        this.reaction_article_id = reaction_article_id;
        this.reation_type = reation_type;
        this.user = user;
        this.article = article;
    }
    
}