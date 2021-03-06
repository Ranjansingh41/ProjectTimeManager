/*
 * Copyright by Lars Geyer-Blaumeiser <lars@lgblaumeiser.de>
 *
 * Licensed under MIT license
 * 
 * SPDX-License-Identifier: MIT
 */
package de.lgblaumeiser.ptm.cli.engine;

import de.lgblaumeiser.ptm.analysis.DataAnalysisService;
import de.lgblaumeiser.ptm.cli.rest.RestBookingStore;
import de.lgblaumeiser.ptm.cli.rest.RestInfrastructureServices;
import de.lgblaumeiser.ptm.datamanager.model.Activity;
import de.lgblaumeiser.ptm.store.ObjectStore;

/**
 * Small class that allows to access the services needed by the command handler
 * implementations.
 */
public class ServiceManager {
	private DataAnalysisService analysisService;
	private ObjectStore<Activity> activityStore;
	private RestBookingStore bookingsStore;
	private RestInfrastructureServices infrastructureServices;

	public DataAnalysisService getAnalysisService() {
		return analysisService;
	}

	public void setAnalysisService(final DataAnalysisService analysisService) {
		this.analysisService = analysisService;
	}

	public ObjectStore<Activity> getActivityStore() {
		return activityStore;
	}

	public void setActivityStore(final ObjectStore<Activity> activityStore) {
		this.activityStore = activityStore;
	}

	public RestBookingStore getBookingsStore() {
		return bookingsStore;
	}

	public void setBookingsStore(final RestBookingStore bookingsStore) {
		this.bookingsStore = bookingsStore;
	}

	public RestInfrastructureServices getInfrastructureServices() {
		return infrastructureServices;
	}

	public void setInfrastructureServices(final RestInfrastructureServices infrastructureServices) {
		this.infrastructureServices = infrastructureServices;
	}
}
