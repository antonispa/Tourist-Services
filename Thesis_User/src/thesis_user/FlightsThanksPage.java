package thesis_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FlightsThanksPage")
public class FlightsThanksPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FlightsThanksPage() {
        super();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String company_name = request.getParameter("company_name");
		String airplane_id = request.getParameter("airplane_id");
		String flight_id = request.getParameter("flight_id");
		int adults_ticket = Integer.parseInt(request.getParameter("adults_ticket"));
		int childrens_ticket = Integer.parseInt(request.getParameter("kids_ticket"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		Connection conn = null;
		PreparedStatement pst = null;
		Statement stm = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");

			pst = conn.prepareStatement("insert into Flight_Bookings values (?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, null);
			pst.setString(2, company_name);
			pst.setString(3, airplane_id);
			pst.setString(4, flight_id);
			pst.setInt(5, adults_ticket);
			pst.setInt(6, childrens_ticket);
			pst.setString(7, name);
			pst.setString(8, surname);					
			pst.setString(9, phone);
			pst.setString(10, email);
			result = pst.executeUpdate();

			if (result > 0){

				stm = conn.createStatement();
				rs = stm.executeQuery("select max(Booking_ID) from Flight_Bookings");
				rs.next();
				
				writer.println("<script type=\"text/javascript\"> if(confirm('Success! Your Booking id is: " + rs.getString("max(Booking_ID)")
						+ ", back to home Page') == true) window.location='http://localhost:8080/Thesis_User/index.html'; </script>");

			}
			
			pst.close();
			stm.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
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
