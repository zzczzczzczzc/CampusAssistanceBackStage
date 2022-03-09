import entity.Good;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

public class GoodsReleasedRecord extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        ArrayList<Good> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();

            String userId = req.getParameter("userId");
            String sql = "select * from campus_assistance.goods where user_id = '" + userId + "'";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Good good = new Good();
                good.setTime(rs.getTimestamp(3).toString());
                good.setPrices(rs.getString(4));
                good.setPicture(Base64.getEncoder().encodeToString(rs.getBlob(5).getBytes(1, (int) rs.getBlob(5).length())));
                good.setTelephone(rs.getString(6));
                good.setDescription(rs.getString(7));
                list.add(good);
            }
            if (list.size() == 0) {
                writer.println(resp.getStatus());
            } else {
                writer.println(list.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.Close();
            writer.flush();
            writer.close();
        }
    }
}
