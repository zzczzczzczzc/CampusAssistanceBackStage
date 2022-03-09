import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Base64;

public class Goods extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Connection con = DBUtil.getConnection();

            String userId = req.getParameter("userId");
            Long time = System.currentTimeMillis();
            String goodId = req.getParameter("goodId") + time;
            String prices = req.getParameter("prices");
            String picture = req.getParameter("picture");
            String telephone = req.getParameter("tele");
            String description = new String(req.getParameter("description").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            String insertGoodMessageSQL = "insert into campus_assistance.goods value(?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(insertGoodMessageSQL);
            stmt.setString(1, userId);
            stmt.setString(2, goodId);
            stmt.setTimestamp(3, new Timestamp(time));
            stmt.setInt(4, Integer.parseInt(prices));
            stmt.setBytes(5, Base64.getDecoder().decode(picture));
            stmt.setString(6, telephone);
            stmt.setString(7, description);

            int insertNum = stmt.executeUpdate();
            if (insertNum > 0) {
                out.println("True");
            } else {
                out.println("False");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.Close();
            out.flush();
            out.close();
        }
    }
}
