import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        boolean flag = false;
//        try {
//            Connection con = DBUtil.getConnection();
//            Statement stmt = con.createStatement();
//            String sql = "select PW from testtomcat.demotable where ID=" + req.getParameter("id");
//            ResultSet rs = stmt.executeQuery(sql);
//            if (rs.next()) {
//                if (rs.getString("PW").equals(req.getParameter("pw"))) {
//                    flag = true;
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            DBUtil.Close();
//        }
//        resp.getWriter().append(flag ? "Gettrue" : "false");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select pw from campus_assistance.users where userId =" + req.getParameter("id");
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("pw").equals(req.getParameter("password"))) {
                    out.write("True");
                } else {
                    out.write("False");
                }
            } else {
                out.write("False");
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
