package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListeleController {
	public static ArrayList<StokModel> listele(){
		ArrayList<StokModel> liste = new ArrayList<>();
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from stokkart");
			while (rs.next()) {
				liste.add(new StokModel(rs.getString("stokKodu"),rs.getString("stokAdı"),
						rs.getInt("stokTipi"),rs.getString("birimi"),rs.getString("barkodu"),
						rs.getDouble("kdvTipi"),rs.getString("acıklama"),rs.getString("olusturmaTarihi")));
				
			}
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return liste;
		
	}

}
