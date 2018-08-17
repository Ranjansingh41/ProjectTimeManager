/*
 * Copyright by Lars Geyer-Blaumeiser <lars@lgblaumeiser.de>
 *
 * Licensed under MIT license
 */
package de.lgblaumeiser.ptm.cli.engine.handler;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.time.LocalDate;
import java.time.YearMonth;


/**
 * Run an hour analysis on the data
 */
@Parameters(commandDescription="Run an hour analysis for a month")
public class RunHourAnalysis extends AbstractRunAnalysis {
	private static final String ANALYSIS_HOURS_ID = "HOURS";

	@Parameter(names = { "-m", "--month" }, description="Optional day for booking", converter= YearMonthConverter.class)
	private YearMonth bookingMonth = YearMonth.now();

	@Parameter(names = { "-w", "--week" }, description="Day in week for project analysis", converter= LocalDateConverter.class)
	private LocalDate bookingDayInWeek = null;

	@Override
	public void handleCommand() {
		runAnalysis(ANALYSIS_HOURS_ID, bookingMonth, bookingDayInWeek, null);
	}
}
