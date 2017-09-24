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


@WebServlet("/CancelBooking")
public class CancelBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CancelBooking() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hotel = request.getParameter("cancel_hotel");
		String car = request.getParameter("cancel_car");
		String motor = request.getParameter("cancel_motor");
		String flight = request.getParameter("cancel_flight");
		String ship = request.getParameter("cancel_ship");
		String excursion = request.getParameter("cancel_excursion");
		String bookingId = request.getParameter("booking_id");

		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		Connection conn = null;
		PreparedStatement pst = null;
		Statement stm = null;
		ResultSet rs = null;
		int result = 0;
		String query = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");

			if (hotel != null) {
				
				pst = conn.prepareStatement("delete from Hotel_Bookings where booking_id=?");

				pst.setString(1, bookingId);
				result = pst.executeUpdate();

			} else if (car != null) {
				
				pst = conn.prepareStatement("delete from Car_Bookings where booking_id=?");

				pst.setString(1, bookingId);
				result = pst.executeUpdate();
				
			} else if (motor != null) {
				
				pst = conn.prepareStatement("delete from Motor_Bookings where booking_id=?");

				pst.setString(1, bookingId);
				result = pst.executeUpdate();
				
			} else if (flight != null) {
				
				pst = conn.prepareStatement("delete from Flight_Bookings where booking_id=?");

				pst.setString(1, bookingId);
				result = pst.executeUpdate();
				
			} else if (ship != null) {
				
				pst = conn.prepareStatement("delete from Ship_Bookings where booking_id=?");

				pst.setString(1, bookingId);
				result = pst.executeUpdate();
				
			} else if (excursion != null) {
				
				pst = conn.prepareStatement("delete from Excursion_Bookings where booking_id=?");

				pst.setString(1, bookingId);
				result = pst.executeUpdate();
				
			}

			if (result > 0){				
				writer.println("<script type=\"text/javascript\"> if(confirm('Your booking has been canceled') == true) window.location='http://localhost:8080/Thesis_User/index.html'; </script>");
			}
			
			
			pst.close();
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
