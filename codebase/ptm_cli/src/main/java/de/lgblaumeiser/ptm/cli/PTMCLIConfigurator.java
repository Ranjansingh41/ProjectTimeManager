/*
 * Copyright by Lars Geyer-Blaumeiser <lars@lgblaumeiser.de>
 *
 * Licensed under MIT license
 * 
 * SPDX-License-Identifier: MIT
 */
package de.lgblaumeiser.ptm.cli;

import com.beust.jcommander.JCommander;

import de.lgblaumeiser.ptm.analysis.DataAnalysisService;
import de.lgblaumeiser.ptm.cli.engine.AbstractCommandHandler;
import de.lgblaumeiser.ptm.cli.engine.PrettyPrinter;
import de.lgblaumeiser.ptm.cli.engine.ServiceManager;
import de.lgblaumeiser.ptm.cli.engine.handler.AddActivity;
import de.lgblaumeiser.ptm.cli.engine.handler.AddBooking;
import de.lgblaumeiser.ptm.cli.engine.handler.AddBreakToBooking;
import de.lgblaumeiser.ptm.cli.engine.handler.Backup;
import de.lgblaumeiser.ptm.cli.engine.handler.ChangeActivity;
import de.lgblaumeiser.ptm.cli.engine.handler.ChangeBooking;
import de.lgblaumeiser.ptm.cli.engine.handler.ControlBackend;
import de.lgblaumeiser.ptm.cli.engine.handler.DeleteBooking;
import de.lgblaumeiser.ptm.cli.engine.handler.License;
import de.lgblaumeiser.ptm.cli.engine.handler.ListActivity;
import de.lgblaumeiser.ptm.cli.engine.handler.ListBookings;
import de.lgblaumeiser.ptm.cli.engine.handler.Restore;
import de.lgblaumeiser.ptm.cli.engine.handler.RunHourAnalysis;
import de.lgblaumeiser.ptm.cli.engine.handler.RunProjectAnalysis;
import de.lgblaumeiser.ptm.cli.rest.RestActivityStore;
import de.lgblaumeiser.ptm.cli.rest.RestAnalysisService;
import de.lgblaumeiser.ptm.cli.rest.RestBaseService;
import de.lgblaumeiser.ptm.cli.rest.RestBookingStore;
import de.lgblaumeiser.ptm.cli.rest.RestInfrastructureServices;
import de.lgblaumeiser.ptm.cli.rest.RestUtils;
import de.lgblaumeiser.ptm.datamanager.model.Activity;
import de.lgblaumeiser.ptm.store.ObjectStore;

/**
 * The configuration object that creates the application structure
 */
public class PTMCLIConfigurator {
	private static final String ADD_ACTIVITY_COMMAND = "add_activity";
	private static final String ADD_ACTIVITY_COMMAND_ABBRV = "aa";
	private static final String CHANGE_ACTIVITY_COMMAND = "change_activity";
	private static final String CHANGE_ACTIVITY_COMMAND_ABBRV = "ca";
	private static final String LIST_ACTIVITY_COMMAND = "list_activities";
	private static final String LIST_ACTIVITY_COMMAND_ABBRV = "la";
	private static final String ADD_BOOKING_COMMAND = "add_booking";
	private static final String ADD_BOOKING_COMMAND_ABBRV = "ab";
	private static final String DELETE_BOOKING_COMMAND = "delete_booking";
	private static final String DELETE_BOOKING_COMMAND_ABBRV = "db";
	private static final String CHANGE_BOOKING_COMMAND = "change_booking";
	private static final String CHANGE_BOOKING_COMMAND_ABBRV = "cb";
	private static final String ADD_BREAK_COMMAND = "add_break";
	private static final String ADD_BREAK_COMMAND_ABBRV = "br";
	private static final String LIST_BOOKING_COMMAND = "list_bookings";
	private static final String LIST_BOOKING_COMMAND_ABBRV = "lb";
	private static final String HOURS_ANALYSIS_COMMAND = "hour_analysis";
	private static final String HOURS_ANALYSIS_COMMAND_ABBRV = "ha";
	private static final String PROJECTS_ANALYSIS_COMMAND = "project_analysis";
	private static final String PROJECTS_ANALYSIS_COMMAND_ABBRV = "pa";
	private static final String BACKEND_COMMAND = "backend";
	private static final String BACKUP_COMMAND = "backup";
	private static final String RESTORE_COMMAND = "restore";
	private static final String LICENSE_COMMAND = "licenses";

	public CLI configure() {
		RestBookingStore bookingStore = new RestBookingStore();
		ObjectStore<Activity> activityStore = new RestActivityStore();
		DataAnalysisService analysisService = new RestAnalysisService();
		RestInfrastructureServices infrastructureServices = new RestInfrastructureServices();
		ServiceManager manager = createServiceManager(bookingStore, activityStore, analysisService,
				infrastructureServices);
		RestBaseService.setRestUtils(new RestUtils().configure());
		RestBaseService.setServices(manager);
		return createCLI(createCommandInterpreter(manager));
	}

	private ServiceManager createServiceManager(final RestBookingStore bookingStore,
			final ObjectStore<Activity> activityStore, final DataAnalysisService analysisService,
			RestInfrastructureServices infrastructureServices) {
		ServiceManager serviceManager = new ServiceManager();
		serviceManager.setActivityStore(activityStore);
		serviceManager.setBookingsStore(bookingStore);
		serviceManager.setAnalysisService(analysisService);
		serviceManager.setInfrastructureServices(infrastructureServices);
		return serviceManager;
	}

	private JCommander createCommandInterpreter(final ServiceManager serviceManager) {
		StdoutLogger logger = new StdoutLogger();
		AbstractCommandHandler.setLogger(logger);
		AbstractCommandHandler.setServices(serviceManager);
		AbstractCommandHandler.setPrinter(new PrettyPrinter().setLogger(logger));
		JCommander jc = JCommander.newBuilder().addObject(new MainParameters())
				.addCommand(ADD_ACTIVITY_COMMAND, new AddActivity(), ADD_ACTIVITY_COMMAND_ABBRV)
				.addCommand(CHANGE_ACTIVITY_COMMAND, new ChangeActivity(), CHANGE_ACTIVITY_COMMAND_ABBRV)
				.addCommand(LIST_ACTIVITY_COMMAND, new ListActivity(), LIST_ACTIVITY_COMMAND_ABBRV)
				.addCommand(ADD_BOOKING_COMMAND, new AddBooking(), ADD_BOOKING_COMMAND_ABBRV)
				.addCommand(DELETE_BOOKING_COMMAND, new DeleteBooking(), DELETE_BOOKING_COMMAND_ABBRV)
				.addCommand(CHANGE_BOOKING_COMMAND, new ChangeBooking(), CHANGE_BOOKING_COMMAND_ABBRV)
				.addCommand(ADD_BREAK_COMMAND, new AddBreakToBooking(), ADD_BREAK_COMMAND_ABBRV)
				.addCommand(LIST_BOOKING_COMMAND, new ListBookings(), LIST_BOOKING_COMMAND_ABBRV)
				.addCommand(HOURS_ANALYSIS_COMMAND, new RunHourAnalysis(), HOURS_ANALYSIS_COMMAND_ABBRV)
				.addCommand(PROJECTS_ANALYSIS_COMMAND, new RunProjectAnalysis(), PROJECTS_ANALYSIS_COMMAND_ABBRV)
				.addCommand(BACKEND_COMMAND, new ControlBackend()).addCommand(BACKUP_COMMAND, new Backup())
				.addCommand(RESTORE_COMMAND, new Restore()).addCommand(LICENSE_COMMAND, new License()).build();
		return jc;
	}

	private CLI createCLI(final JCommander interpreter) {
		CLI cli = new CLI();
		cli.setInterpreter(interpreter);
		return cli;
	}
}
