/*
 * Copyright by Lars Geyer-Blaumeiser <lgblaumeiser@gmail.com>
 *
 * Licensed under MIT license
 */
package de.lgblaumeiser.ptm.cli.engine.handler;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.Collection;

import de.lgblaumeiser.ptm.cli.engine.AbstractCommandHandler;
import de.lgblaumeiser.ptm.datamanager.model.Booking;

/**
 * List the bookings of the day
 */
public class ListBookings extends AbstractCommandHandler {
	@Override
	public void handleCommand(final Collection<String> parameters) {
		LocalDate currentBookings = getServices().getStateStore().getCurrentDay();
		getLogger().log("Current Bookings for day " + currentBookings.format(ISO_LOCAL_DATE));
		getLogger().log("======================================");
		for (Booking booking : getServices().getBookingsStore().retrieveAll().stream()
				.filter(b -> currentBookings.equals(b.getBookingday()))
				.sorted((b1, b2) -> b1.getStarttime().compareTo(b2.getStarttime())).collect(toList())) {
			getLogger().log(booking.toString());
		}
		getLogger().log("======================================\n");
	}

	@Override
	public String toString() {
		return "List the bookings of the current day";
	}
}
