package com.cts.hc.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.cts.hc.models.Claim;

public class ClaimMapper implements FieldSetMapper<Claim> {

	public Claim mapFieldSet(FieldSet arg0) throws BindException {
		Claim claim = new Claim();
		claim.setClaimId(arg0.readInt(0));
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		//LocalDate date = LocalDate.parse(arg0.readString(1).trim(), formatter);
		claim.setDoc(arg0.readString(1));

		claim.setAmount(arg0.readInt(2));
		claim.setPolicyHolderName(arg0.readString(3));
		
		return claim;
	}

}
