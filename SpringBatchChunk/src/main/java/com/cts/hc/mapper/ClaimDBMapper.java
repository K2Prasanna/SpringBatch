package com.cts.hc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cts.hc.models.Claim;

public class ClaimDBMapper implements RowMapper<Claim> {

	public Claim mapRow(ResultSet arg0, int arg1) throws SQLException {
		Claim claim = new Claim();

		claim.setClaimId(arg0.getInt(1));
		claim.setDoc(arg0.getString(2));
		claim.setAmount(arg0.getInt(3));
		claim.setPolicyHolderName(arg0.getString(4));
		System.out.println("DB Claim: " + claim.toString());
		return claim;
		
	}

}
