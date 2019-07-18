package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.coreEntities.OneTimePassword;
import com.sharebooks.entities.entity.Entity;

public abstract class AbstractOTPDao implements OTPDao {

	// function for converting entity list into otp list
	protected List<OneTimePassword> convertIntoOTPList(List<Entity> list) throws Exception {
		try {
			List<OneTimePassword> otpList = new ArrayList<OneTimePassword>();
			for (Entity entity : list) {
				if (entity instanceof OneTimePassword) {
					otpList.add((OneTimePassword) entity);
				} else {
					throw new Exception("OTP list containing some other entity");
				}
			}

			return otpList;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
