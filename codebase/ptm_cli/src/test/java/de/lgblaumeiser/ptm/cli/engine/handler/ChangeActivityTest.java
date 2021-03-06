/*
 * Copyright by Lars Geyer-Blaumeiser <lars@lgblaumeiser.de>
 *
 * Licensed under MIT license
 * 
 * SPDX-License-Identifier: MIT
 */
package de.lgblaumeiser.ptm.cli.engine.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.beust.jcommander.ParameterException;

public class ChangeActivityTest extends AbstractHandlerTest {
	private static final String CHANGE_ACTIVITY_COMMAND = "change_activity";

	@Test
	public void testChangeAndHideActivityClean() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-a", "1", "-n", ACTIVITY1NAME, "-i", ACTIVITY1NUMBER,
				"--hidden");
		assertEquals("/activities/1", restutils.apiNameGiven);
		assertEquals(ACTIVITY1NAME, restutils.bodyDataGiven.get("activityName"));
		assertEquals(ACTIVITY1NUMBER, restutils.bodyDataGiven.get("bookingNumber"));
		assertEquals("true", restutils.bodyDataGiven.get("hidden"));
		assertEquals(3, restutils.bodyDataGiven.size());
	}

	@Test
	public void testChangeAndEnableActivityClean() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-a", "2", "-n", ACTIVITY1NAME, "-i", ACTIVITY1NUMBER,
				"--visible");
		assertEquals("/activities/2", restutils.apiNameGiven);
		assertEquals(ACTIVITY1NAME, restutils.bodyDataGiven.get("activityName"));
		assertEquals(ACTIVITY1NUMBER, restutils.bodyDataGiven.get("bookingNumber"));
		assertEquals("false", restutils.bodyDataGiven.get("hidden"));
		assertEquals(3, restutils.bodyDataGiven.size());
	}

	@Test(expected = ParameterException.class)
	public void testChangeActivityTwoParamFirstNull() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-a", "1", "-n", "-i", ACTIVITY1NUMBER);
	}

	@Test(expected = ParameterException.class)
	public void testChangeActivityTwoParamSecondNull() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-a", "1", "-n", ACTIVITY1NAME, "-i");
	}

	@Test
	public void testChangeActivityOneParam() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-a", "1", "-n", ACTIVITY1NAME);
		assertEquals("/activities/1", restutils.apiNameGiven);
		assertEquals(ACTIVITY1NAME, restutils.bodyDataGiven.get("activityName"));
		assertEquals(ACTIVITY1NUMBER, restutils.bodyDataGiven.get("bookingNumber"));
		assertEquals("false", restutils.bodyDataGiven.get("hidden"));
		assertEquals(3, restutils.bodyDataGiven.size());
	}

	@Test
	public void testChangeActivityNoParam() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-a", "1", "--hidden");
		assertEquals("/activities/1", restutils.apiNameGiven);
		assertEquals(ACTIVITY1NAME, restutils.bodyDataGiven.get("activityName"));
		assertEquals(ACTIVITY1NUMBER, restutils.bodyDataGiven.get("bookingNumber"));
		assertEquals("true", restutils.bodyDataGiven.get("hidden"));
		assertEquals(3, restutils.bodyDataGiven.size());
	}

	@Test(expected = ParameterException.class)
	public void testChangeActivityNameNoActivity() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-n", ACTIVITY1NAME);
	}

	@Test(expected = ParameterException.class)
	public void testChangeActivityNoActivity() {
		commandline.runCommand(CHANGE_ACTIVITY_COMMAND, "-hidden");
	}
}
