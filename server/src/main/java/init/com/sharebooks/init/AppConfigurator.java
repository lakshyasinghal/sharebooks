package com.sharebooks.init;

import com.sharebooks.exception.IllegalAccessException;
import com.sharebooks.services.entityServices.BookCategoryService;
import com.sharebooks.services.entityServices.CityService;
import com.sharebooks.services.entityServices.StateService;
import com.sharebooks.sources.ServiceSource;
import com.sharebooks.sources.enums.ServiceType;

public class AppConfigurator {
	private static final AppConfigurator instance = new AppConfigurator();
	private static int accessedNum = 0;

	private AppConfigurator() {
	}

	public static AppConfigurator instance() throws IllegalAccessException {
		if (accessedNum > 0) {
			throw new IllegalAccessException(AppConfigurator.class.getName());
		}
		accessedNum++;
		return instance;
	}

	public void configureCache() throws Exception {
		CityService cityService = (CityService) ServiceSource.service(ServiceType.CITY.desc());
		cityService.putCitiesInCache();

		StateService stateService = (StateService) ServiceSource.service(ServiceType.STATE.desc());
		stateService.putStatesInCache();

		BookCategoryService bookCategoryService = (BookCategoryService) ServiceSource
				.service(ServiceType.BOOK_CATEGORY.desc());
		bookCategoryService.putBookCategoriesInCache();
	}
}