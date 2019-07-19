package com.sharebooks.services.entityServices;

public class OTPService extends EntityService {
	private static OTPService instance;

	private OTPService() {
	}

	public static OTPService instance() {
		if (instance == null) {
			synchronized (OTPService.class) {
				if (instance == null) {
					instance = new OTPService();
				}
			}
		}
		return instance;
	}

	public int removeStaleOtps() {

		return 0;
	}

}
