/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Article;
import Model.Article.UserFake;
import dal.articleDAO.ArticleDAO;
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
@WebServlet(name="WriteArticleServlet", urlPatterns={"/article/write"})
public class WriteArticleServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String article_name = request.getParameter("article_name");
        String image = request.getParameter("image");
        String content = request.getParameter("content");
        String article_category = request.getParameter("article_category");
        String article_tag = request.getParameter("article_tag");
        
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        JSONObject jsonObject = new JSONObject(json);
        int user_id = jsonObject.getInt("user_id");
        // gọi user ở đây (getUserById)
        
        //fake User
        UserFake uk = new UserFake(1);
        Date today = new Date(System.currentTimeMillis());
        boolean stt = false; // chỉnh sau khi có user thật        
        // check role ở đây, nếu role 3 thì duyệt luôn, role 2 thì chờ duyệt
        Article art = new Article(0, 0, 0, 0, article_name, article_category, 
                article_tag, content, image, today, today, stt, uk);
        ArticleDAO ad = new ArticleDAO();
        ad.addObject(art);
        
        // response ở đây ( đã đăng hay chờ duyệt)
    }
}