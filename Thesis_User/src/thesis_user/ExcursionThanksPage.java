package thesis_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ExcursionThanksPage")
public class ExcursionThanksPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExcursionThanksPage() {
        super();
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stm = null;
		int result = 0;
		
		String excId = request.getParameter("exc_id");
		String excOffice = request.getParameter("exc_office");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String adults = request.getParameter("adults");
		String kids = request.getParameter("kids");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root"); 
			
			ps = conn.prepareStatement("insert into excursion_bookings values (?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1 , null);
			ps.setString(2, excOffice);
			ps.setString(3, excId);
			ps.setString(4, adults);
			ps.setString(5, kids);
			ps.setString(6, name);
			ps.setString(7, surname);
			ps.setString(8, phone);
			ps.setString(9, email);
			result = ps.executeUpdate();
			
			if (result > 0){
				stm = conn.createStatement();
				rs = stm.executeQuery("select max(Booking_id) from excursion_bookings");
				rs.next();
				
				writer.println("<script type=\"text/javascript\"> if(confirm('Success! Your booking id is " + rs.getString("max(Booking_ID)") +
						", back to home Page') == true) window.location='http://localhost:8080/Thesis_User/index.html'; </script>");
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
