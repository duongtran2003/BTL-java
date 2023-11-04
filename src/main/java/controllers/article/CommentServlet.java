/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Article;
import Model.Article.Comment;
import Model.Article.UserFake;
import com.google.gson.Gson;
import dal.articleDAO.ArticleDAO;
import dal.articleDAO.CommentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.Date;
import org.json.JSONObject;

/**
 *
 * @author Hanh
 */
//@WebServlet(name="CommentServlet", urlPatterns={"/article/comment"})
public class CommentServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        BufferedReader reader = request.getReader();
//        StringBuilder json = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            json.append(line);
//        }
        String json = "{\n" +
        "user_id: 6,\n" +
        "article_id: 7,\n" +
        "content: \"bai viet that hay va bo ich\"\n" +
        "}";
        System.out.println(json);
        JSONObject jsonObject = new JSONObject(json);
        int user_id = jsonObject.getInt("user_id");
        int article_id = jsonObject.getInt("article_id");
        String comment_content = jsonObject.getString("comment_content");
        
        // gọi user ở đây (getUserById)
        //fake User
        UserFake uk = new UserFake(1);
        Date today = new Date(System.currentTimeMillis());
        ArticleDAO ad = new ArticleDAO();
        Article art = (Article) ad.getById(article_id);
        
        Comment cmt = new Comment(0, 0, 0, comment_content, today, art, uk);
        CommentDAO cd = new CommentDAO();
//        cd.addObject(cmt);
                
        String jsonString = "{\"message\": \"comment successfully\"}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
    }
}