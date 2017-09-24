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


@WebServlet("/RentalsThanksPage")
public class RentalsThanksPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RentalsThanksPage() {
        super();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rentalOffice = request.getParameter("rental_office");
		String car_id = request.getParameter("car_id");
		String car_pickup = request.getParameter("car_pickup");
		String car_dropoff = request.getParameter("car_dropoff");
		String motor_pickup = request.getParameter("motor_pickup");
		String motor_dropoff = request.getParameter("motor_dropoff");
		String motor_id = request.getParameter("motor_id");
		long charge = Integer.parseInt(request.getParameter("charge"));
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
		String query = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");

			if (request.getParameter("booknow_car") != null) {
				pst = conn.prepareStatement("insert into Car_Bookings values (?,?,?,?,?,?,?,?,?,?)");

				pst.setString(1, null);
				pst.setString(2, rentalOffice);
				pst.setString(3, car_id);
				pst.setDate(4, java.sql.Date.valueOf(car_pickup));
				pst.setDate(5, java.sql.Date.valueOf(car_dropoff));
				pst.setLong(6, charge);
				pst.setString(7, name);
				pst.setString(8, surname);
				pst.setString(9, phone);
				pst.setString(10, email);
				result = pst.executeUpdate();

				query = "select max(Booking_id) from car_bookings";
				
			} else if (request.getParameter("booknow_motor") != null) {
				
				pst = conn.prepareStatement("insert into Motor_Bookings values (?,?,?,?,?,?,?,?,?,?)");

				pst.setString(1, null);
				pst.setString(2, rentalOffice);
				pst.setString(3, motor_id);
				pst.setDate(4, java.sql.Date.valueOf(motor_pickup));
				pst.setDate(5, java.sql.Date.valueOf(motor_dropoff));
				pst.setLong(6, charge);
				pst.setString(7, name);
				pst.setString(8, surname);
				pst.setString(9, phone);
				pst.setString(10, email);
				result = pst.executeUpdate();
				
				query = "select max(Booking_id) from motor_bookings";		
			}

			if (result > 0){
				stm = conn.createStatement();
				rs = stm.executeQuery(query);
				rs.next();
				
				writer.println("<script type=\"text/javascript\"> if(confirm('Success! Your booking id is " + rs.getString("max(Booking_ID)")
						 + ", back to home Page') == true) window.location='http://localhost:8080/Thesis_User/index.html'; </script>");
			}
			
			pst.close();
			conn.close();
			rs.close();
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
