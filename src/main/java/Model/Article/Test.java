/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

import dal.articleDAO.ArticleDAO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        ArticleDAO ad = new ArticleDAO();
//        Article a = (Article)ad.getById(1);
//        System.out.println(a);

//        Article art;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        art = new Article(1, 1, 1, 1, "hi", "hello", "hello", "hello",
//                "#", new Date(dateFormat.parse("2003-06-02").getTime()), 
//                new Date(dateFormat.parse("2003-06-02").getTime()), true, new UserFake(1));
//        System.out.println(art);
//        ad.addObject(art);

//        Article a = (Article)ad.getById(2);
//        a.setArticle_name("Ronaldo > Messi");
//        ad.updateObject(a);

        ArrayList<Article> arr = ad.getListArticleInQueue();
        System.out.println(arr.size());
    }
}