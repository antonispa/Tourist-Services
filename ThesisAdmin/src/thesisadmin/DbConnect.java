package thesisadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DbConnect")
public class DbConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DbConnect() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		int result = 0;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		
		///////////// HOTELS ///////////////////////
		
		if(request.getParameter("hotelButton") != null){
			String hotelName = request.getParameter("hotelName");
			String hotelCategory = request.getParameter("hotelCategory");
			String hotelAddress = request.getParameter("hotelAddress");
			String hotelPhone = request.getParameter("hotelPhone");
			String hotelEmail = request.getParameter("hotelEmail");
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Hotels values (?,?,?,?,?)");
				pst.setString(1, hotelName);
				pst.setString(2, hotelCategory);
				pst.setString(3, hotelAddress);
				pst.setString(4, hotelPhone);
				pst.setString(5, hotelEmail);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Hotels/hotelsForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();		
			}
		}
		
		if(request.getParameter("roomButton") != null){
			String roomHotelName = request.getParameter("roomHotelName");
			String roomNumber = request.getParameter("roomNumber");
			String roomType = request.getParameter("roomType");
			String roomPrice = request.getParameter("roomPrice");

			try{

				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Rooms values (?,?,?,?)");
				pst.setString(1, roomHotelName);
				pst.setString(2, roomNumber);
				pst.setString(3, roomType);
				pst.setString(4, roomPrice);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Hotels/roomsForm.jsp");	
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();		
			}		
		}
		
		if(request.getParameter("servicesButton") != null){
			String hotelName = request.getParameter("roomHotelName");
			String roomNumber = request.getParameter("roomNumber");
			String wifi = request.getParameter("wifiBox");
			String balcony = request.getParameter("balconyBox");
			String seaview = request.getParameter("seaviewBox");
			String services = "";
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				PreparedStatement pst = conn.prepareStatement("insert into RoomServices values (?,?,?)");
				
				if(wifi != null){
					services += "wifi";
				}
				
				if(balcony != null){
					if(services != "")
						services += ",Balcony";
					else
						services += "Balcony";
				}
				
				if(seaview != null){
					if(services != "")
						services += ",Sea-view";
					else
						services += "Sea-view";
				}
				
				pst.setString(1 , hotelName);
				pst.setString(2, roomNumber);
				pst.setString(3, services);
				pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Hotels/serviceForm.jsp");
				
				conn.close();
				pst.close();
	
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("hotelTableBtn") != null){
			String query = "select * from Hotels";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "hotelName";
						else if (i == 2)
							name = "hotelCategory";
						else if (i == 3)
							name = "hotelAddress";
						else if (i == 4)
							name = "hotelPhone";
						else
							name = "hotelEmail";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td> <input type=submit value=update name=updateBtn_Hotel> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Hotel> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}

		}
				
		if(request.getParameter("roomTableBtn") != null){
			String query = "select * from Rooms";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");
				pw.println();
				
				String name = "";
				int cnt = 0;
				
				while(rs.next()){
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "hotelName";
						else if (i == 2)
							name = "roomNumber";
						else if (i == 3)
							name = "roomType";
						else if (i == 4)
							name = "roomPrice";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td> <input type=submit value=update name=updateBtn_Room> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Room> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					stm.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("servicesTableBtn") != null){
			String query = "select * from RoomServices";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "hotelName";
						else if (i == 2)
							name = "hotelAddress";
						else if (i == 3)
							name = "roomService";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_Serv> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Serv> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
				
					conn.close();
					st.close();
					rs.close();
					
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("hotelBookingsBtn") != null){
			String query = "select * from Hotel_bookings";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "booking_id";
						else if (i == 2)
							name = "hotel_name";
						else if (i == 3)
							name = "room_number";
						else if (i == 4)
							name = "check_in";
						else if (i == 5)
							name = "check_out";
						else if (i == 6)
							name = "adults";
						else if (i == 7)
							name = "kids";
						else if (i == 8)
							name = "charge";
						else if (i == 9)
							name = "name";
						else if (i == 10)
							name = "surname";
						else if (i == 11)
							name = "phone";
						else if (i == 12)
							name = "email";


						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td> <input type=submit value=update name=updateHotelBooking> </td>");
						pw.println("<td><input type=submit value=delete name=deleteHotelBooking> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		
		/////////////////////// FINISH //////////////////////
		
		
		//////////////////// RENTALS //////////////////////
		
		if(request.getParameter("rentalOfficeButton") != null){
			String rentalOfficeName = request.getParameter("rentalOfficeName");
			String rentalOfficeAddress = request.getParameter("rentalOfficeAddress");
			String rentalOfficePhone = request.getParameter("rentalOfficePhone");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Rental_Offices values (?,?,?)");
				pst.setString(1, rentalOfficeName);
				pst.setString(2, rentalOfficeAddress);
				pst.setString(3, rentalOfficePhone);
				result = pst.executeUpdate();
								
				response.sendRedirect("http://localhost:8080/ThesisAdmin/RentalOffices/rentalOfficesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();		
			}
		}
		
		if(request.getParameter("carButton") != null){
			String rentalOfficeNameCar = request.getParameter("rentalOfficeNameCar");
			String carId = request.getParameter("carId");
			String carCc = request.getParameter("carCc");
			String passengers = request.getParameter("passengers");
			String carCategory = request.getParameter("carCategory");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Cars values (?,?,?,?,?)");
				pst.setString(1, rentalOfficeNameCar);
				pst.setString(2, carId);
				pst.setString(3, carCc);
				pst.setString(4, passengers);
				pst.setString(5, carCategory);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/RentalOffices/carsForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();	
			}
		}
		
		if(request.getParameter("carChargesBtn") != null){
			String rentalOfficeNameCar = request.getParameter("rentalOfficeNameCar");
			String carId = request.getParameter("carId");
			String carDays = request.getParameter("days");
			int carCharges = Integer.parseInt(request.getParameter("charge"));

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Car_Charges values (?,?,?,?)");
				pst.setString(1, rentalOfficeNameCar);
				pst.setString(2, carId);
				pst.setString(3, carDays);
				pst.setInt(4, carCharges);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/RentalOffices/carCharges.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();		
			}
		}
		
		if(request.getParameter("motorcycleButton") != null){
			String rentalOfficeNameMotor = request.getParameter("rentalOfficeNameMotor");
			String motorcycleId = request.getParameter("motorcycleId");
			String motorcycleCc = request.getParameter("motorcycleCc");
			String motorcycleCategory = request.getParameter("motorcycleCategory");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Motorcycles values (?,?,?,?)");
				pst.setString(1, rentalOfficeNameMotor);
				pst.setString(2, motorcycleId);
				pst.setString(3, motorcycleCc);
				pst.setString(4, motorcycleCategory);
				result = pst.executeUpdate();
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();		
			}
		}
		
		if(request.getParameter("motorChargesBtn") != null){
			String rentalOfficeNameCar = request.getParameter("rentalOfficeNameMotor");
			String motorId = request.getParameter("motorId");
			String motorDays = request.getParameter("days");
			int motorCharges = Integer.parseInt(request.getParameter("charge"));

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Motorcycle_Charges values (?,?,?,?)");
				pst.setString(1, rentalOfficeNameCar);
				pst.setString(2, motorId);
				pst.setString(3, motorDays);
				pst.setInt(4, motorCharges);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/RentalOffices/motorcycleCharges.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();	
			}
		}
		
		if(request.getParameter("rentalsTableBtn") != null){
			String query = "select * from Rental_Offices";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "rentOffName";
						else if (i == 2)
							name = "rentOffAddress";
						else if (i == 3)
							name = "rentOffPhone";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_RentOff> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_RentOff> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("carsBtnTable") != null){
			String query = "select * from Cars";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "rentOffName";
						else if (i == 2)
							name = "car_id";
						else if (i == 3)
							name = "car_cc";
						else if (i == 4)
							name = "passengers";
						else if (i == 5)
							name = "car_category";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_Cars> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Cars> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("carChargesTableBtn") != null){
			String query = "select * from Car_Charges";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "rentOffName";
						else if (i == 2)
							name = "car_id";
						else if (i == 3)
							name = "days";
						else if (i == 4)
							name = "charge";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_CarCharges> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_CarCharges> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}		
		
		if(request.getParameter("motorsBtnTable") != null){
			String query = "select * from Motorcycles";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "rentOffName";
						else if (i == 2)
							name = "motor_id";
						else if (i == 3)
							name = "motor_cc";
						else if (i == 4)
							name = "motor_category";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_Motors> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Motors> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("motorChargesTableBtn") != null){
			String query = "select * from Motorcycle_Charges";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "rentOffName";
						else if (i == 2)
							name = "motor_id";
						else if (i == 3)
							name = "days";
						else if (i == 4)
							name = "charge";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_MotorCharges> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_MotorCharges> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("carBookingsBtn") != null){
			String query = "select * from Car_bookings";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						name = metaData.getColumnName(i);

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td> <input type=submit value=update name=updateCarBooking> </td>");
						pw.println("<td><input type=submit value=delete name=deleteCarBooking> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		
		if(request.getParameter("motorBookingsBtn") != null){
			String query = "select * from Motor_bookings";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						name = metaData.getColumnName(i);

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td> <input type=submit value=update name=updateMotorBooking> </td>");
						pw.println("<td><input type=submit value=delete name=deleteMotorBooking> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		
		//////////// FINISH ////////////////

		
		///////////  Museums //////////
		
		if(request.getParameter("museumButton") != null){
			String museumName = request.getParameter("museumName");
			String museumAddress = request.getParameter("museumAddress");
			String museumPhone = request.getParameter("museumPhone");
			String adultsTicket = request.getParameter("adultsTicket");
			String childrensTicket = request.getParameter("childrensTicket");
			String openingTime = request.getParameter("openingTime");
			String closingTime = request.getParameter("closingTime");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Museums values (?,?,?,?,?,?,?)");
				pst.setString(1, museumName);
				pst.setString(2, museumAddress);
				pst.setString(3, museumPhone);
				pst.setString(4, adultsTicket);
				pst.setString(5, childrensTicket);
				pst.setString(6, openingTime);
				pst.setString(7, closingTime);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Museums/museumsForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("museumsTableBtn") != null){
			String query = "select * from Museums";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "museumName";
						else if (i == 2)
							name = "museumAddress";
						else if (i == 3)
							name = "museumPhone";
						else if (i == 4)
							name = "adultsTicket";
						else if (i == 5)
							name = "childrensTicket";
						else if (i == 6)
							name = "openingTime";
						else if (i == 7)
							name = "closingTime";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_Museums> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Museums> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		/////////// landmarks //////////////////
		
		if(request.getParameter("landmarkButton") != null){
			String landmarkName = request.getParameter("landmarkName");
			String landmarkAddress = request.getParameter("landmarkAddress");
			String landmarkPhone = request.getParameter("landmarkPhone");
			String adultsTicket = request.getParameter("adultsTicket");
			String childrensTicket = request.getParameter("childrensTicket");
			String openingTime = request.getParameter("openingTime");
			String closingTime = request.getParameter("closingTime");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Landmarks values (?,?,?,?,?,?,?)");
				pst.setString(1, landmarkName);
				pst.setString(2, landmarkAddress);
				pst.setString(3, landmarkPhone);
				pst.setString(4, adultsTicket);
				pst.setString(5, childrensTicket);
				pst.setString(6, openingTime);
				pst.setString(7, closingTime);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Landmarks/landmarksForm.jsp");

				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();	
			}
		}
		
		if(request.getParameter("landmarksTableBtn") != null){
			String query = "select * from Landmarks";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "landmarkName";
						else if (i == 2)
							name = "landmarkAddress";
						else if (i == 3)
							name = "landmarkPhone";
						else if (i == 4)
							name = "adultsTicket";
						else if (i == 5)
							name = "childrensTicket";
						else if (i == 6)
							name = "openingTime";
						else if (i == 7)
							name = "closingTime";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_Landmarks> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Landmarks> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		/////////// finish  ////////////
		
		
		/////////// flights ////////////
		
		if(request.getParameter("companyButton") != null){
			String flightCompanyName = request.getParameter("companyName");
			String flightCompanyAddress = request.getParameter("companyAddress");
			String flightCompanyPhone = request.getParameter("companyPhone");
			String flightCompanyEmail = request.getParameter("companyEmail");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Flight_Companies values (?,?,?,?)");
				pst.setString(1, flightCompanyName);
				pst.setString(2, flightCompanyAddress);
				pst.setString(3, flightCompanyPhone);
				pst.setString(4, flightCompanyEmail);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Flights/companiesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();	
			}
		}
		
		if(request.getParameter("airplaneButton") != null){
			String flightCompanyName = request.getParameter("companyName");
			String airplaneID = request.getParameter("airplaneID");

			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				
				PreparedStatement pst = conn.prepareStatement("insert into Airplanes values (?,?)");
				pst.setString(1, flightCompanyName);
				pst.setString(2, airplaneID);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Flights/airplanesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch (Exception e) {
				e.printStackTrace();		
			}
		}
		
		if(request.getParameter("flightRouteButton") != null){
			String companyName = request.getParameter("companyName");
			String airplaneID = request.getParameter("airplaneID");
			String flightID = request.getParameter("flightID");
			String departure = request.getParameter("departure");
			String departureDate = request.getParameter("departureDate").toString();
			String departureTime = request.getParameter("departureTime").toString();
			String arrival = request.getParameter("arrival");
			String arrivalDate = request.getParameter("arrivalDate").toString();
			String arrivalTime = request.getParameter("arrivalTime").toString();
			String adultsTicket = request.getParameter("adultsTicket");
			String childrensTicket = request.getParameter("childrensTicket"); 
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				PreparedStatement pst = conn.prepareStatement("insert into Flight_Routes values (?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setString(1, companyName);
				pst.setString(2, airplaneID);
				pst.setString(3, flightID);
				pst.setString(4, departure);
				pst.setString(5, departureDate);
				pst.setString(6, departureTime);
				pst.setString(7, arrival);
				pst.setString(8, arrivalDate);
				pst.setString(9, arrivalTime);
				pst.setString(10, adultsTicket);
				pst.setString(11, childrensTicket);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Flights/routesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("companiesTableBtn") != null){
			String query = "select * from Flight_Companies";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "companyName";
						else if (i == 2)
							name = "companyAddress";
						else if (i == 3)
							name = "companyPhone";
						else if (i == 4)
							name = "companyEmail";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_FlightComp> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_FlightComp> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("airplanesTableBtn") != null){
			String query = "select * from Airplanes";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "companyName";
						else if (i == 2)
							name = "airplaneID";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_Plane> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Plane> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
									
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("flightRoutesTableBtn") != null){
			String query = "select * from Flight_Routes";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th style=font-size:11px>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "companyName";
						else if (i == 2)
							name = "airplaneID";
						else if (i == 3)
							name = "flightID";
						else if (i == 4)
							name = "departure";
						else if (i == 5)
							name = "departureDate";
						else if (i == 6)
							name = "departureTime";
						else if (i == 7)
							name = "arrival";
						else if (i == 8)
							name = "arrivalDate";
						else if (i == 9)
							name = "arrivalTime";
						else if (i == 10)
							name = "adultsTicket";
						else if (i == 11)
							name = "childrensTicket";

						pw.println("<td style=border:none> <input size=15 type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_FlightRoutes> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_FlightRoutes> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		
		if(request.getParameter("flightBookingsBtn") != null){
			String query = "select * from Flight_bookings";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						name = metaData.getColumnName(i);

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td> <input type=submit value=update name=updateFlightBooking> </td>");
						pw.println("<td><input type=submit value=delete name=deleteFlightBooking> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		
		//////////// finish ////////////
		
		//////////// Ships ////////////
		
		if(request.getParameter("shipCompanyBtn") != null){
			String shipCompany = request.getParameter("companyName");
			String shipCompanyAddress = request.getParameter("companyAddress");
			String shipCompanyPhone = request.getParameter("companyPhone");
			String shipCompanyEmail = request.getParameter("companyEmail");
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				PreparedStatement pst = conn.prepareStatement("insert into Ship_Companies values (?,?,?,?)");
				
				pst.setString(1, shipCompany);
				pst.setString(2, shipCompanyAddress);
				pst.setString(3, shipCompanyPhone);
				pst.setString(4, shipCompanyEmail);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Ships/companiesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch(Exception e){
				System.out.println("" + e);
			}
		}
		
		if(request.getParameter("shipBtn") != null){
			String shipCompany = request.getParameter("companyName");
			String shipID = request.getParameter("shipID");
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				PreparedStatement pst = conn.prepareStatement("insert into Ships values (?,?)");
				
				pst.setString(1, shipCompany);
				pst.setString(2, shipID);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Ships/shipsForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("shipRouteButton") != null){
			String companyName = request.getParameter("companyName");
			String shipID = request.getParameter("shipID");
			String routeID = request.getParameter("routeID");
			String departure = request.getParameter("departure");
			String departureDate = request.getParameter("departureDate");
			String departureTime = request.getParameter("departureTime").toString();
			String arrival = request.getParameter("arrival");
			String arrivalDate = request.getParameter("arrivalDate");
			String arrivalTime = request.getParameter("arrivalTime").toString();
			String adultsTicket = request.getParameter("adultsTicket");
			String childrensTicket = request.getParameter("childrensTicket"); 
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root"); 
				PreparedStatement pst = conn.prepareStatement("insert into Ship_Routes values (?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setString(1, companyName);
				pst.setString(2, shipID);
				pst.setString(3, routeID);
				pst.setString(4, departure);
				pst.setString(5, departureDate);
				pst.setString(6, departureTime);
				pst.setString(7, arrival);
				pst.setString(8, arrivalDate);
				pst.setString(9, arrivalTime);
				pst.setString(10, adultsTicket);
				pst.setString(11, childrensTicket);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Ships/routesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("shipCompaniesTableBtn") != null){
			String query = "select * from Ship_Companies";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				String compNameValue = null;
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1){
							name = "companyName";
							compNameValue = rs.getString(i);
						}
						else if (i == 2)
							name = "companyAddress";
						else if (i == 3)
							name = "companyPhone";
						else if (i == 4)
							name = "companyEmail";

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=hidden value=" + compNameValue + " name=compNameValue></td>");
						pw.println("<td><input type=submit value=update name=updateBtn_ShipComp> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_ShipComp> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("shipsTableBtn") != null){
			String query = "select * from Ships";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				String compNamevalue = null;
				String shipIdValue = null;
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1){
							name = "companyName";
							compNamevalue= rs.getString(i);
						}							
						else if (i == 2){
							name = "shipID";
							shipIdValue = rs.getString(i);
						}

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
						pw.println("<td><input type=hidden value=" + compNamevalue +  " name=compNamevalue> </td>");
						pw.println("<td><input type=hidden value=" + shipIdValue +  " name=shipIdValue> </td>");
						pw.println("<td><input type=submit value=update name=updateBtn_Ship> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_Ship> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("shipRoutesTableBtn") != null){
			String query = "select * from Ship_Routes";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th style=font-size:11px>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1)
							name = "companyName";
						else if (i == 2)
							name = "shipID";
						else if (i == 3)
							name = "shipRouteID";
						else if (i == 4)
							name = "departure";
						else if (i == 5)
							name = "departureDate";
						else if (i == 6)
							name = "departureTime";
						else if (i == 7)
							name = "arrival";
						else if (i == 8)
							name = "arrivalDate";
						else if (i == 9)
							name = "arrivalTime";
						else if (i == 10)
							name = "adultsTicket";
						else if (i == 11)
							name = "childrensTicket";

						pw.println("<td style=border:none> <input size=15 type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateBtn_ShipRoutes> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_ShipRoutes> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
				
					
					conn.close();
					st.close();
					rs.close();
					
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("shipBookingsBtn") != null){
			String query = "select * from Ship_bookings";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						name = metaData.getColumnName(i);

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateShipBooking> </td>");
						pw.println("<td><input type=submit value=delete name=deleteShipBooking> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			} catch (Exception e){
				e.printStackTrace();
			}

		}
		
		/////// finish //////////
		
		////// Excursions //////
		
		if(request.getParameter("excOfficeBtn") != null){
			String excOffice = request.getParameter("excOffice");
			String excAddress = request.getParameter("excOfficeAddress");
			String excPhone = request.getParameter("excOfficePhone");
			String excEmail = request.getParameter("excOfficeEmail");
			
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				PreparedStatement pst = conn.prepareStatement("insert into Excursion_Offices values (?,?,?,?)");
				
				pst.setString(1, excOffice);
				pst.setString(2, excAddress);
				pst.setString(3, excPhone);
				pst.setString(4, excEmail);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Excursions/excOfficesForm.jsp");
				
				conn.close();
				pst.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("excursionBtn") != null){
			String excOffice = request.getParameter("excOffice");
			String excursionID = request.getParameter("excursionID");
			String departure = request.getParameter("departure");
			String departureDate = request.getParameter("departureDate").toString();
			String departureTime = request.getParameter("departureTime").toString();
			String arrival = request.getParameter("arrival");
			String arrivalDate = request.getParameter("arrivalDate").toString();
			String arrivalTime = request.getParameter("arrivalTime").toString();
			String adultsTicket = request.getParameter("adultsTicket");
			String childrensTicket = request.getParameter("childrensTicket"); 
			
			
			try{
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				PreparedStatement pst = conn.prepareStatement("insert into Excursions values (?,?,?,?,?,?,?,?,?,?)");
				
				pst.setString(1, excOffice);
				pst.setString(2, excursionID);
				pst.setString(3, departure);
				pst.setString(4, departureDate);
				pst.setString(5, departureTime);
				pst.setString(6, arrival);
				pst.setString(7, arrivalDate);
				pst.setString(8, arrivalTime);
				pst.setString(9, adultsTicket);
				pst.setString(10, childrensTicket);
				result = pst.executeUpdate();
				
				response.sendRedirect("http://localhost:8080/ThesisAdmin/Excursions/excursionsForm.jsp");
				
				conn.close();
				pst.close();
				
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		if(request.getParameter("excOfficeTableBtn") != null){
			String query = "select * from Excursion_Offices";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				String excOfficeValue = null;
				String excAddress = null;
				String excPhone = null;
				String excEmail = null;
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1){
							name = "excOffice";
							excOfficeValue = rs.getString(i);
						}
						else if (i == 2){
							name = "excAddress";
							excAddress = rs.getString(i);
						}
						else if (i == 3){
							name = "excPhone";
							excPhone = rs.getString(i);
						}
						else if (i == 4){
							name = "excEmail";
							excEmail = rs.getString(i);
						}
						
						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=hidden value=" + excOfficeValue + " name=excOfficeValue> </td>");
						pw.println("<td><input type=submit value=update name=updateBtn_ExcOffice> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_ExcOffice> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					
					conn.close();
					st.close();
					rs.close();
				
			}catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		if(request.getParameter("excursionsTableBtn") != null){
			String query = "select * from Excursions";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				String excOfficeValue = null;
				String excursionIDvalue = null;
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}
					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						if(i == 1){
							name = "excOffice";
							excOfficeValue = rs.getString(i);
						}
						else if (i == 2){
							name = "excursionID";
							excursionIDvalue = rs.getString(i);
						}
						else if (i == 3)
							name = "departure";
						else if (i == 4)
							name = "departureDate";
						else if (i == 5)
							name = "departureTime";
						else if (i == 6)
							name = "arrival";
						else if (i == 7)
							name = "arrivalDate";
						else if (i == 8)
							name = "arrivalTime";
						else if (i == 9)
							name = "adultsTicket";
						else if (i == 10)
							name = "childrensTicket";
	
						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=hidden value=" + excOfficeValue + " name=excOfficeValue> </td>");
						pw.println("<td><input type=hidden value=" + excursionIDvalue + " name=excursionIDvalue> </td>");
						pw.println("<td><input type=submit value=update name=updateBtn_ExcursionBtn> </td>");
						pw.println("<td><input type=submit value=delete name=deleteBtn_ExcursionBtn> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
				
					
					conn.close();
					st.close();
					rs.close();
					
			} catch (Exception e){
				e.printStackTrace();
			}	
		}
		
		
		
		if(request.getParameter("excursionBookingsBtn") != null){
			String query = "select * from Excursion_bookings";
			response.setCharacterEncoding("utf-8");
			Connection conn;
			
			try{
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				pw.println("<table border=1 style=border-collapse:collapse>");	

				pw.println();
				String name = "";
				int cnt = 0;	
				
				while(rs.next()){						
					int i;
					
					pw.println("<tr>");
					pw.println("<table><tr><td>");
					pw.println("<tr>");
					
					if(cnt == 0){
						for(i = 1; i <= columnCount; i++){
							pw.println("<th>" + metaData.getColumnName(i));
						}
						cnt = 1;
					}

					
					pw.println("</tr>");
				 	pw.write("<form action=/ThesisAdmin/UpdateTables method=post target=_self>");
					 	
					for(i = 1; i <= columnCount; i++){
						
						name = metaData.getColumnName(i);

						pw.println("<td style=border:none> <input type=text value=" + rs.getString(i) + " name=" + name + "> </td>"); 							
					}
					
						pw.println("<td><input type=submit value=update name=updateExcursionBooking> </td>");
						pw.println("<td><input type=submit value=delete name=deleteExcursionBooking> </td>");
					
						pw.write("</form>");
						pw.write("</td></tr></table>");
						pw.println("</tr>");
						pw.println();
					}
					pw.println("</table>");
					
					conn.close();
					st.close();
					rs.close();
				
			} catch (Exception e){
				e.printStackTrace();
			}

		}
		
		if(result > 0)
			pw.write("<script> confirm(Success!); </script>");

	}

}
