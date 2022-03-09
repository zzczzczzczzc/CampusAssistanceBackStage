import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select pw from campus_assistance.users where userId =" + req.getParameter("id");
            String newPassword = req.getParameter("newPassword");
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("pw").equals(req.getParameter("password"))) {
                    String changePasswordSQL = "update campus_assistance.users set pw = '"+newPassword+"'"  + " where userId = " + req.getParameter("id");
                    stmt.execute(changePasswordSQL);
                    out.println("True");
                } else {
                    out.println("False");
                }
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
