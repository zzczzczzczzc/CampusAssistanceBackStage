import com.mysql.cj.util.StringUtils;
import entity.Good;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;


public class GoodsRequest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        ArrayList<Good> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();

            String searchText = req.getParameter("search");
            String sql = "";
            if (StringUtils.isNullOrEmpty(searchText)) {
                sql = "select * from campus_assistance.goods order by 'times' desc";
            } else {
                sql = "select * from campus_assistance.goods where description_message like '" + "%" + new String(searchText.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) + "%" + "'";
            }

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Good good = new Good();
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
