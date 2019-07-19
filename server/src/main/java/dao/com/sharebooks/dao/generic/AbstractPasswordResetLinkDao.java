package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.coreEntities.PasswordResetLink;
import com.sharebooks.entities.entity.Entity;

public abstract class AbstractPasswordResetLinkDao implements PasswordResetLinkDao {

	// function for converting entity list into otp list
	protected List<PasswordResetLink> convertIntoLinksList(List<Entity> list) throws Exception {
		try {
			List<PasswordResetLink> linkList = new ArrayList<PasswordResetLink>();
			for (Entity entity : list) {
				if (entity instanceof PasswordResetLink) {
					linkList.add((PasswordResetLink) entity);
				} else {
					throw new Exception("PasswordResetLink list containing some other entity");
				}
			}

			return linkList;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
