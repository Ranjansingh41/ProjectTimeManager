/*
 * Copyright by Lars Geyer-Blaumeiser <lars@lgblaumeiser.de>
 *
 * Licensed under MIT license
 * 
 * SPDX-License-Identifier: MIT
 */
package de.lgblaumeiser.ptm.cli.engine.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class ListBookingsTest extends AbstractHandlerTest {
	private static final String LIST_BOOKING_COMMAND = "list_bookings";
	private static final String DATE_FOR_BOOKINGS = "2018-04-05";

	@Test
	public void testToday() {
		commandline.runCommand(LIST_BOOKING_COMMAND);
		assertEquals("/bookings/day/" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
				restutils.apiNameGiven);
		assertTrue(logger.logMessages.toString().contains(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
		assertTrue(logger.logMessages.toString().contains("| Activity | Starttime | Endtime | Id | Comment |"));
	}

	@Test
	public void testCertainDate() {
		commandline.runCommand(LIST_BOOKING_COMMAND, "-d", DATE_FOR_BOOKINGS);
		assertEquals("/bookings/day/" + DATE_FOR_BOOKINGS, restutils.apiNameGiven);
		assertTrue(logger.logMessages.toString().contains(DATE_FOR_BOOKINGS));
		assertTrue(logger.logMessages.toString().contains("| Activity | Starttime | Endtime | Id | Comment |"));
	}
}
