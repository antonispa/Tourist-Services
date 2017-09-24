package thesisadmin;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTables")
public class UpdateTables extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateTables() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Writer writer = response.getWriter();
		int result = 0;
		
		PreparedStatement pst = null;
		Connection conn = null;
		Statement stm = null;

		String value1 = null;
		String value2 = null;
		String value3 = null;
		String value4 = null;
		String value5 = null;
		String value6 = null;
		String value7 = null;
		String value8 = null;
		String value9 = null;
		String value10 = null;
		String value11 = null;
		String value12 = null;

		String[] values = null;
		int k = 0;

		Enumeration<String> paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			
			String param = paramNames.nextElement();
			values = request.getParameterValues(param);
			k++;

			for (int i = 0; i < values.length; i++) {

				String value = values[i];

				if (k == 1)
					value1 = value;
				else if (k == 2)
					value2 = value;
				else if (k == 3)
					value3 = value;
				else if (k == 4)
					value4 = value;
				else if (k == 5)
					value5 = value;
				else if (k == 6)
					value6 = value;
				else if (k == 7)
					value7 = value;
				else if (k == 8)
					value8 = value;
				else if (k == 9)
					value9 = value;
				else if (k == 10)
					value10 = value;
				else if (k == 11)
					value11 = value;
				else if (k == 12)
					value12 = value;
			}
		}

		k = 0;

		String charge1 = request.getParameter("charge");

		int charge = 0;

		if (charge1 != null)
			charge = Integer.parseInt(charge1);

		/*
		 * String hotelName = request.getParameter("hotelName"); String
		 * hotelCategory = request.getParameter("hotelCategory"); String
		 * hotelAddress = request.getParameter("hotelAddress"); String
		 * hotelPhone = request.getParameter("hotelPhone"); String hotelEmail =
		 * request.getParameter("hotelEmail");
		 * 
		 * String roomNumber = request.getParameter("roomNumber"); String
		 * roomType = request.getParameter("roomType"); String roomPrice =
		 * request.getParameter("roomPrice");
		 * 
		 * String roomService = request.getParameter("roomService");
		 * 
		 * String rentOffName = request.getParameter("rentOffName"); String
		 * rentOffAddress = request.getParameter("rentOffAddress"); String
		 * rentOffPhone = request.getParameter("rentOffPhone");
		 * 
		 * String carId = request.getParameter("car_id"); String carCc =
		 * request.getParameter("car_cc"); String passengers =
		 * request.getParameter("passengers"); String carCategory =
		 * request.getParameter("car_category");
		 * 
		 * String motorId = request.getParameter("motor_id"); String motorCc =
		 * request.getParameter("motor_cc"); String motorCategory =
		 * request.getParameter("motor_category");
		 * 
		 * String days = request.getParameter("days");
		 * 
		 * 
		 * String museumName = request.getParameter("museumName"); String
		 * museumAddress = request.getParameter("museumAddress"); String
		 * museumPhone = request.getParameter("museumPhone");
		 * 
		 * String landmarkName = request.getParameter("landmarkName"); String
		 * landmarkAddress = request.getParameter("landmarkAddress"); String
		 * landmarkPhone = request.getParameter("landmarkPhone");
		 * 
		 * String adultsTicket = request.getParameter("adultsTicket"); String
		 * childrensTicket = request.getParameter("childrensTicket"); String
		 * openingTime = request.getParameter("openingTime"); String closingTime
		 * = request.getParameter("closingTime");
		 * 
		 * String companyName = request.getParameter("companyName"); String
		 * companyAddress = request.getParameter("companyAddress"); String
		 * companyPhone = request.getParameter("companyPhone"); String
		 * companyEmail = request.getParameter("companyEmail");
		 * 
		 * String airplaneID = request.getParameter("airplaneID"); String
		 * flightID = request.getParameter("flightID");
		 * 
		 * String shipID = request.getParameter("shipID"); String shipRouteID =
		 * request.getParameter("shipRouteID");
		 * 
		 * String departure = request.getParameter("departure"); String
		 * departureDate = request.getParameter("departureDate"); String
		 * departureTime = request.getParameter("departureTime"); String arrival
		 * = request.getParameter("arrival"); String arrivalDate =
		 * request.getParameter("arrivalDate"); String arrivalTime =
		 * request.getParameter("arrivalTime");
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");

			if (request.getParameter("updateBtn_Hotel") != null) {

				String update = "update Hotels set Category=?,Address=?,Phone=?,Email=? where Name=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setString(3, value4);
				pst.setString(4, value5);
				pst.setString(5, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Hotel") != null) {

				stm = conn.createStatement();

				stm.executeUpdate("delete from Hotel_Bookings where Hotel_Name= '" + value1 + "' ");
				stm.executeUpdate("delete from RoomServices where Hotel_Name= '" + value1 + "' ");
				stm.executeUpdate("delete from Rooms where Hotel_Name= '" + value1 + "' ");
				result = stm.executeUpdate("delete from Hotels where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_Room") != null) {

				String update = "update Rooms set Room_Number=?,Type=?,Price=? where Hotel_Name=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setString(3, value4);
				pst.setString(4, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Room") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Rooms where Hotel_Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_Serv") != null) {

				String update = "update RoomServices set Services=? where Hotel_Name=? and room_number=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value3);
				pst.setString(2, value1);
				pst.setString(3, value2);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Serv") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from RoomServices where Hotel_Name= '" + value1
						+ "' and room_number= '" + value2 + "' ");

			}

			else if (request.getParameter("updateHotelBooking") != null) {

				String update = "update Hotel_Bookings set Hotel_Name=?,Room_Number=?,Check_In=?,Check_Out=?,Adults=?,Kids=?,Charge=?,Name=?,Surname=?,Phone=?,Email=?"
						+ " where Booking_ID=?";

				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setDate(3, java.sql.Date.valueOf(value4));
				pst.setDate(4, java.sql.Date.valueOf(value5));
				pst.setString(5, value6);
				pst.setString(6, value7);
				pst.setString(7, value8);
				pst.setString(8, value9);
				pst.setString(9, value10);
				pst.setString(10, value11);
				pst.setString(11, value12);
				pst.setString(12, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteHotelBooking") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from hotel_bookings where booking_id= '" + value1 + "' ");

			}

			
			
			////////////////  Rentals //////////////

			
			
			else if (request.getParameter("updateBtn_RentOff") != null) {

				String update = "update Rental_Offices set Name=?,Address=?,Phone=? where Name=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_RentOff") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Rental_Offices where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_Cars") != null) {

				String update = "update Cars set Rental_Office=?,Car_id=?,Car_cc=?,Passengers=?,Category=? where Rental_Office= '"
						+ value1 + "' " + "and Car_id= '" + value2 + "' ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value5);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Cars") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate(
						"delete from Cars where Rental_Office= '" + value1 + "' and Car_id= '" + value2 + "' ");

			}

			else if (request.getParameter("updateBtn_CarCharges") != null) {

				String update = "update Car_Charges set Rental_Office=?,Car_id=?,Days=?,Charge=? where Rental_Office= '"
						+ value1 + "' " + "and Car_id= '" + value2 + "' and Days= '" + value3 + "' ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setInt(4, charge);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_CarCharges") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Car_Charges where Rental_Office= '" + value1 + "' and Car_id= '"
						+ value2 + "' and Days= '" + value3 + "' ");

			}

			else if (request.getParameter("updateBtn_Motors") != null) {

				String update = "update Motorcycles set Rental_Office=?,Motorcycle_id=?,Motorcycle_cc=?,Category=? where Rental_Office=? "
						+ "and Motorcycle_id=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value1);
				pst.setString(6, value2);
				pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Motors") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Motorcycles where Rental_Office= '" + value1
						+ "' and Motorcycle_id= '" + value2 + "' ");

			}

			else if (request.getParameter("updateBtn_MotorCharges") != null) {

				String update = "update Motorcycle_Charges set Rental_Office=?,Motorcycle_id=?,Days=?,Charge=? where Rental_Office= '"
						+ value1 + "' " + "and Motorcycle_id= '" + value2 + "' and Days= '" + value3 + "' ";
				
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setInt(4, charge);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_MotorCharges") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Motorcycle_Charges where Rental_Office= '" + value1
						+ "' and Motorcycle_id= '" + value2 + "' and Days= '" + value3 + "' ");

			}

			else if (request.getParameter("updateCarBooking") != null) {

				String update = "update Car_Bookings set Rental_Office=?,Car_ID=?,Pick_Up=?,Drop_Off=?,Charge=?,Name=?,Surname=?,Phone=?,Email=?"
						+ " where Booking_ID=?";

				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setDate(3, java.sql.Date.valueOf(value4));
				pst.setDate(4, java.sql.Date.valueOf(value5));
				pst.setLong(5, Integer.parseInt(value6));
				pst.setString(6, value7);
				pst.setString(7, value8);
				pst.setString(8, value9);
				pst.setString(9, value10);
				pst.setString(10, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteCarBooking") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from car_bookings where booking_id= '" + value1 + "' ");

			}

			else if (request.getParameter("updateMotorBooking") != null) {

				String update = "update Motor_Bookings set Rental_Office=?,Motorcycle_ID=?,Pick_Up=?,Drop_Off=?,Charge=?,Name=?,Surname=?,Phone=?,Email=?"
						+ " where Booking_ID=?";

				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setDate(3, java.sql.Date.valueOf(value4));
				pst.setDate(4, java.sql.Date.valueOf(value5));
				pst.setLong(5, Integer.parseInt(value6));
				pst.setString(6, value7);
				pst.setString(7, value8);
				pst.setString(8, value9);
				pst.setString(9, value10);
				pst.setString(10, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteMotorBooking") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from motor_bookings where booking_id= '" + value1 + "' ");

			}
			
			

			/////////////// Flights //////////
			

			else if (request.getParameter("updateBtn_Museums") != null) {

				String update = "update Museums set Address=?,Phone=?,Adults_Ticket=?,Childrens_Ticket=?,Opening_time=?,Closing_Time=? where Name=?  ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setString(3, value4);
				pst.setString(4, value5);
				pst.setString(5, value6);
				pst.setString(6, value7);
				pst.setString(7, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Museums") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Museums where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_Landmarks") != null) {

				String update = "update Landmarks set Name=?,Address=?,Phone=?,Adults_Ticket=?,Childrens_Ticket=?,Opening_time=?,Closing_Time=? where Name=?  ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value5);
				pst.setString(6, value6);
				pst.setString(7, value7);
				pst.setString(8, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Landmarks") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Landmarks where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_FlightComp") != null) {

				String update = "update Flight_Companies set Name=?,Address=?,Phone=?,Email=? where Name=?  ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_FlightComp") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Flight_Companies where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_Plane") != null) {

				String update = "update Airplanes set Company_Name=?,Airplane_ID=? where Company_Name=? and Airplane_ID=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value1);
				pst.setString(4, value2);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Plane") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Airplanes where Company_Name= '" + value1
						+ "' and Airplane_ID= '" + value2 + "' ");

			}

			else if (request.getParameter("updateBtn_FlightRoutes") != null) {

				String update = "update Flight_Routes set Company_Name=?,Airplane_ID=?,Flight_ID=?,Departure=?,Departure_Date=?,Departure_Time=?,"
						+ "Arrival=?,Arrival_Date=?,Arrival_Time=?,Adults_Ticket=?,Childrens_Ticket=? where Company_Name= '"
						+ value1 + "' and Airplane_ID= '" + value2 + "' and Flight_ID= '" + value3 + "' ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value5);
				pst.setString(6, value6);
				pst.setString(7, value7);
				pst.setString(8, value8);
				pst.setString(9, value9);
				pst.setString(10, value10);
				pst.setString(11, value11);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_FlightRoutes") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Flight_Routes where Company_Name= '" + value1
						+ "' and Airplane_ID= '" + value2 + "'  and Flight_ID= '" + value3 + "' ");

			}

			else if (request.getParameter("updateFlightBooking") != null) {

				String update = "update Flight_Bookings set Company_Name=?,Airplane_ID=?,Flight_ID=?,Adults_Ticket=?,Childrens_Ticket=?,Name=?,Surname=?,Phone=?,Email=?"
						+ " where Booking_ID=?";

				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setString(3, value4);
				pst.setInt(4, Integer.parseInt(value5));
				pst.setInt(5, Integer.parseInt(value6));
				pst.setString(6, value7);
				pst.setString(7, value8);
				pst.setString(8, value9);
				pst.setString(9, value10);
				pst.setString(10, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteFlightBooking") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from flight_bookings where booking_id= '" + value1 + "' ");

			}
			
			

			///////////////// Ships  //////////////////

			
			else if (request.getParameter("updateBtn_ShipComp") != null) {

				String update = "update Ship_Companies set Name=?,Address=?,Phone=?,Email=? where Name=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_ShipComp") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Ship_Companies where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_Ship") != null) {

				String update = "update Ships set Company_Name=?,Ship_ID=? where Company_Name=? and Ship_ID=? ";
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, request.getParameter("compNameValue"));
				pst.setString(4, request.getParameter("shipIdValue"));
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_Ship") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate(
						"delete from Ships where Company_Name= '" + value1 + "' and Ship_ID= '" + value2 + "' ");

			}

			else if (request.getParameter("updateBtn_ShipRoutes") != null) {

				String update = "update Ship_Routes set Company_Name=?,Ship_ID=?,Route_ID=?,Departure=?,Departure_Date=?,Departure_Time=?,"
						+ "Arrival=?,Arrival_Date=?,Arrival_Time=?,Adults_Ticket=?,Childrens_Ticket=? where Company_Name= '"
						+ value1 + "' and Ship_ID= '" + value2 + "' and Route_ID= '" + value3 + "' ";
				
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value5);
				pst.setString(6, value6);
				pst.setString(7, value7);
				pst.setString(8, value8);
				pst.setString(9, value9);
				pst.setString(10, value10);
				pst.setString(11, value11);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_ShipRoutes") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Ship_Routes where Company_Name= '" + value1 + "' and Ship_ID= '"
						+ value2 + "' and Route_ID= '" + value3 + "' ");

			}

			else if (request.getParameter("updateShipBooking") != null) {

				String update = "update Ship_Bookings set Company_Name=?,Ship_ID=?,Route_ID=?,Adults_Ticket=?,Childrens_Ticket=?,Name=?,Surname=?,Phone=?,Email=?"
						+ " where Booking_ID=?";

				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setString(3, value4);
				pst.setInt(4, Integer.parseInt(value5));
				pst.setInt(5, Integer.parseInt(value6));
				pst.setString(6, value7);
				pst.setString(7, value8);
				pst.setString(8, value9);
				pst.setString(9, value10);
				pst.setString(10, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteShipBooking") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from ship_bookings where booking_id= '" + value1 + "' ");

			}
			
			

			//////////// Excursions  /////////////////
			
			

			else if (request.getParameter("updateBtn_ExcOffice") != null) {

				String update = "update Excursion_Offices set Name=?,Address=?,Phone=?,Email=? where Name=? ";
				
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, request.getParameter("excOfficeValue"));
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_ExcOffice") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Excursion_Offices where Name= '" + value1 + "' ");

			}

			else if (request.getParameter("updateBtn_ExcursionBtn") != null) {

				String update = "update Excursions set Excursion_Office=?,Excursion_ID=?,Departure=?,Departure_Date=?,Departure_Time=?,"
						+ "Arrival=?,Arrival_Date=?,Arrival_Time=?,Adults_Ticket=?,Childrens_Ticket=? where Excursion_Office=? and Excursion_ID=? ";
				
				pst = conn.prepareStatement(update);

				pst.setString(1, value1);
				pst.setString(2, value2);
				pst.setString(3, value3);
				pst.setString(4, value4);
				pst.setString(5, value5);
				pst.setString(6, value6);
				pst.setString(7, value7);
				pst.setString(8, value8);
				pst.setString(9, value9);
				pst.setString(10, value10);
				pst.setString(11, request.getParameter("excOfficeValue"));
				pst.setString(12, request.getParameter("excursionIDvalue"));
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteBtn_ExcursionBtn") != null) {

				stm = conn.createStatement();

				stm.executeUpdate("delete from Excursions where Excursion_Office= '" + value1 + "' and Excursion_ID= '"
						+ value2 + "' ");

			}

			else if (request.getParameter("updateExcursionBooking") != null) {

				String update = "update Excursion_Bookings set Excursion_Office=?,Excursion_ID=?,Name=?,Surname=?,Phone=?,Email=?"
						+ " where Booking_ID=?";

				pst = conn.prepareStatement(update);

				pst.setString(1, value2);
				pst.setString(2, value3);
				pst.setString(3, value4);
				pst.setString(4, value5);
				pst.setString(5, value6);
				pst.setString(6, value7);
				pst.setString(7, value1);
				result = pst.executeUpdate();

			}

			else if (request.getParameter("deleteExcursionBooking") != null) {

				stm = conn.createStatement();

				result = stm.executeUpdate("delete from Excursion_Bookings where booking_id= '" + value1 + "' ");

			}
			
			//pst.close();
			//stm.close();
			conn.close();	

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			if(conn != null){
				try{
					conn.close();
				} catch (Exception e){
					
				}
			}
			
			if(pst != null){
				try{
					pst.close();
				} catch (Exception e){
					
				}
			}
			
			if(stm != null){
				try{
					stm.close();
				} catch (Exception e){
					
				}
			}
		}
		

		if (result > 0)
			writer.write("<script> confirm('Success!'); </script>");

	}

}
