/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.articleDAO;

import Model.Article.Article;
import Model.Article.Comment;
import Model.Article.UserFake;
import dal.DAO.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hanh
 */
public class CommentDAO extends DAO{
    ArticleDAO ad = new ArticleDAO();

    @Override
    public Object getById(int Record_id) {
        try {
            String sql = "select * from comments where comment_id = " + Record_id;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserFake user = new UserFake(1);
                return new Comment(rs.getInt("comment_id"), rs.getInt("likes"), 
                        rs.getInt("dislikes"), rs.getString("comment_content"), 
                        rs.getDate("comment_time"), (Article)ad.getById(rs.getInt("article_id")),
                        user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean addObject(Object object) {
        try {
            Comment comment = (Comment)object;
            String sql = "insert into comments(article_id, user_id, comment_content,"
                    + " comment_time, likes, dislikes) value(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, comment.getArticle().getArticle_id());
            st.setInt(2, comment.getUser().getUser_id());
            st.setString(3, comment.getComment_content());
            st.setDate(4, comment.getComment_time());
            st.setInt(5, comment.getLikes());
            st.setInt(6, comment.getDislikes());
            st.executeUpdate();
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateObject(Object object) {
        try {
            Comment cmt = (Comment)object;
            String sql = "update comments set comment_content = ?, comment_time = ?,"
                    + " likes = ?, dislikes = ? where comment_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cmt.getComment_content());
            st.setDate(2, cmt.getComment_time());
            st.setInt(3, cmt.getLikes());
            st.setInt(4, cmt.getDislikes());
            st.setInt(5, cmt.getComment_id());
            st.executeUpdate();
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteObject(int objectId) {
        try {
            String sql = "delete from comments where comment_id = " + objectId;
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Object> getAllObjects() {
        try {
            String sql = "select * from comments";
            PreparedStatement st = con.prepareStatement(sql);
            List<Object> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            UserFake user = new UserFake(1);
            while (rs.next()) {
                list.add(new Comment(rs.getInt("comment_id"), rs.getInt("likes"), 
                        rs.getInt("dislikes"), rs.getString("comment_content"), 
                        rs.getDate("comment_time"), (Article)ad.getById(rs.getInt("article_id")),
                        user));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
}