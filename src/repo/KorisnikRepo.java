package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import utils.DBUtils;

public class KorisnikRepo {

	public List<Korisnik> findAll() {

		List<Korisnik> list = new ArrayList<Korisnik>();

		String sql = "SELECT * FROM korisnik";

		try(Connection conn = DBUtils.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
				Korisnik temp = new Korisnik(rs.getLong(1), rs.getString(2), rs.getString(3));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Korisnik findById(Long id) {

		String sql = "SELECT * FROM korisnik WHERE id = ?";

		try(Connection conn = DBUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setLong(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return new Korisnik(rs.getLong(1), rs.getString(2), rs.getString(3)); 
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Korisnik create(Korisnik k) {

		String sql = "INSERT INTO korisnik (ime, prezime) VALUES (?, ?)";

		try(Connection conn = DBUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, k.getIme());
			pst.setString(2, k.getPrezime());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				try (ResultSet genKey = pst.getGeneratedKeys()) {
					if (genKey.next()) {
						k.setId(genKey.getLong(1));
						return k;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Korisnik update(Korisnik k) {
		
		String sql = "UPDATE korisnik SET ime = ?, prezime = ? WHERE id = ?";
		
		try(Connection conn = DBUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			
			pst.setString(1, k.getIme());
			pst.setString(2, k.getPrezime());
			pst.setLong(3, k.getId());
			
			var res = pst.executeUpdate();
			
			if (res > 0) 
				return k;
			else
				return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean delete(Long id) {
		
		String sql = "DELETE FROM korisnik WHERE id = ?";
		
		try(Connection conn = DBUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id);
			
			var res = pst.executeUpdate();
			
			if (res > 0) 
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
