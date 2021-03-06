/*
 * Copyright by Lars Geyer-Blaumeiser <lars@lgblaumeiser.de>
 *
 * Licensed under MIT license
 * 
 * SPDX-License-Identifier: MIT
 */
package de.lgblaumeiser.ptm.datamanager.model;

import static de.lgblaumeiser.ptm.datamanager.model.Activity.newActivity;
import static de.lgblaumeiser.ptm.util.Utils.emptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the activity class
 */
public class ActivityTest {
	private final static String LINE_ACTIVITY_1_1 = "LineActivityId11";
	private final static String LINE_ACTIVITY_1_2 = "LineActivityId12";
	private final static String LINE_BOOKING_1 = "L1";
	private final static String PROJECT_ACTIVITY_1_1 = "ProjectActivityId11";
	private final static String PROJECT_BOOKING_1 = "P1";

	/**
	 * Positive test method for newLineActivity with activity id
	 */
	@Test
	public final void testNewActivityPositive() {
		Activity newActivity = newActivity().setActivityName(LINE_ACTIVITY_1_1).setBookingNumber(LINE_BOOKING_1)
				.build();
		assertEquals(LINE_ACTIVITY_1_1, newActivity.getActivityName());
		assertEquals(LINE_BOOKING_1, newActivity.getBookingNumber());
	}

	@Test(expected = IllegalStateException.class)
	public final void testWithBlankName() {
		newActivity().setActivityName(emptyString()).setBookingNumber(LINE_BOOKING_1).build();
	}

	@Test(expected = IllegalStateException.class)
	public final void testWithBlankNumber() {
		newActivity().setActivityName(LINE_ACTIVITY_1_1).setBookingNumber(emptyString()).build();
	}

	/**
	 * Test for equals and hashcode
	 */
	@Test
	public final void testEquals() {
		Activity newActivity1 = newActivity().setActivityName(LINE_ACTIVITY_1_1).setBookingNumber(LINE_BOOKING_1)
				.build();
		Activity newActivity2 = newActivity().setActivityName(LINE_ACTIVITY_1_2).setBookingNumber(LINE_BOOKING_1)
				.build();
		Activity newActivity3 = newActivity().setActivityName(PROJECT_ACTIVITY_1_1).setBookingNumber(PROJECT_BOOKING_1)
				.build();
		Activity newActivity4 = newActivity().setActivityName(LINE_ACTIVITY_1_1).setBookingNumber(LINE_BOOKING_1)
				.build();

		assertTrue(newActivity1.equals(newActivity4));
		assertTrue(newActivity1.hashCode() == newActivity4.hashCode());
		assertFalse(newActivity1.equals(newActivity2));
		assertFalse(newActivity1.equals(newActivity3));
	}
}
