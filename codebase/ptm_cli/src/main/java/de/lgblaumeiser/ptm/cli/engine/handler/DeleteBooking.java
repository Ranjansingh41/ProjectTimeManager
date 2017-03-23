/*
 * Copyright 2016, 2017 Lars Geyer-Blaumeiser <lgblaumeiser@gmail.com>
 */
package de.lgblaumeiser.ptm.cli.engine.handler;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Iterables.get;
import static java.time.LocalTime.parse;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

import de.lgblaumeiser.ptm.cli.engine.AbstractCommandHandler;
import de.lgblaumeiser.ptm.datamanager.model.Booking;
import de.lgblaumeiser.ptm.datamanager.model.DayBookings;

/**
 * Delete a booking
 */
public class DeleteBooking extends AbstractCommandHandler {
	@Override
	public void handleCommand(final Collection<String> parameters) {
		DayBookings currentBookings = getServices().getStateStore().getCurrentDay();
		checkState(parameters.size() > 0);
		getLogger().log("Delete booking now ...");
		LocalTime starttime = parse(get(parameters, 0));
		Optional<Booking> bookingToDelete = currentBookings.getBookings().stream()
				.filter((booking) -> booking.getStarttime().equals(starttime)).findAny();
		if (bookingToDelete.isPresent()) {
			getServices().getBookingService().removeBooking(currentBookings, bookingToDelete.get());
		}
		getLogger().log("... booking deleted");
		getServices().getBookingsStore().store(currentBookings);
		getLogger().log("... bookings stored");
	}

	@Override
	public String toString() {
		return "Delete a booking, Params: <1> Starttime of booking";
	}

}