package thesisadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterUser() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String username = request.getParameter("reg_username");
		String password = request.getParameter("reg_password");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root"); 
			
			ps = conn.prepareStatement("insert into  Users values(?,?)");
			
			ps.setString(1 , username);
			ps.setString(2 , password);
			rs = ps.executeQuery();
			
			
			if(rs.next()){
				writer.println("<script type=\"text/javascript\"> if(confirm('Success!') == true) window.location='http://localhost:8080/ThesisAdmin/login.jsp'; </script>");
				
				ps.close();
				rs.close();
				conn.close();
				
			} else {
				writer.println("<script type=\"text/javascript\"> if(confirm('Error!') == true) window.location='http://localhost:8080/ThesisAdmin/login.jsp'; </script>");
			}
	
			
		} catch (SQLException se) {
			se.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {

				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {

				}
			}
		}
	}

}
