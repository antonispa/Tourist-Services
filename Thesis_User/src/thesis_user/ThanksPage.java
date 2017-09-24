package thesis_user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/ThanksPage")
public class ThanksPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ThanksPage() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		PreparedStatement pst = null;
		ResultSet rs = null;
		Statement stm = null;
		Connection conn = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String checkin = request.getParameter("checkin");
			String checkout = request.getParameter("checkout");
			String adults = request.getParameter("adults");
			String kids = request.getParameter("kids");
			String hotelName = request.getParameter("hotel");
			String roomNumber = request.getParameter("number");
			String charge = request.getParameter("price");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
			pst = conn.prepareStatement("insert into Hotel_Bookings values (?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pst.setString(1, null);
			pst.setString(2, hotelName);
			pst.setString(3, roomNumber);
			pst.setDate(4, java.sql.Date.valueOf(checkin));
			pst.setDate(5, java.sql.Date.valueOf(checkout));
			pst.setString(6, adults);
			pst.setString(7, kids);
			pst.setString(8, charge);
			pst.setString(9, name);
			pst.setString(10, surname);
			pst.setString(11, phone);
			pst.setString(12, email);
			result = pst.executeUpdate();
			
			if (result > 0){
				
				stm = conn.createStatement();
				rs = stm.executeQuery("select max(booking_id) from Hotel_Bookings"); 
				rs.next();
				
				writer.println("<script type=\"text/javascript\"> if(confirm('Success! Your booking id is: "+rs.getString("max(booking_id)")+", "+
				"back to home Page') == true) window.location='http://localhost:8080/Thesis_User/index.html'; </script>");
			}

			pst.close();
			stm.close();
			rs.close();
			conn.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(pst != null)
					pst.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
					
				}
			}
		}

}
