/*
 * Copyright by Lars Geyer-Blaumeiser <lgblaumeiser@gmail.com>
 *
 * Licensed under MIT license
 */
package de.lgblaumeiser.ptm.analysis.analyzer;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.lgblaumeiser.ptm.analysis.Analysis;
import de.lgblaumeiser.ptm.datamanager.model.Activity;
import de.lgblaumeiser.ptm.datamanager.model.Booking;
import de.lgblaumeiser.ptm.store.ObjectStore;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * An analysis to compute the amount of hours per a activity. The computer
 * calculates the percentage of an activity on the overall hours and maps these
 * percentages to the amount of 8 hours per booking day. This way, this fulfills
 * the requirements of the author concerning his time keeping.
 */
public class ProjectComputer implements Analysis {
	private ObjectStore<Booking> store;

	@Override
	public Collection<Collection<Object>> analyze(final Collection<String> parameter) {
		Collection<Collection<Object>> result = Lists.newArrayList();
		result.add(Arrays.asList("Activity", "Booking number", "Hours", "%"));
		Duration totalMinutes = Duration.ZERO;
		Map<Activity, Duration> activityToMinutesMap = Maps.newHashMap();
		for (Booking current : getRelevantBookings(parameter)) {
			if (current.hasEndtime()) {
				Activity currentActivity = current.getActivity();
				Duration accumulatedMinutes = activityToMinutesMap.get(currentActivity);
				if (accumulatedMinutes == null) {
					accumulatedMinutes = Duration.ZERO;
				}
				Duration activityLength = current.calculateTimeSpan().getLengthInMinutes();
				totalMinutes = totalMinutes.plus(activityLength);
				accumulatedMinutes = accumulatedMinutes.plus(activityLength);
				activityToMinutesMap.put(currentActivity, accumulatedMinutes);
			}
		}
		for (Entry<Activity, Duration> currentActivity : activityToMinutesMap.entrySet()) {
			Activity activity = currentActivity.getKey();
			Duration totalMinutesId = currentActivity.getValue();
			double percentage = (double) totalMinutesId.toMinutes() / (double) totalMinutes.toMinutes();
			String percentageString = String.format("%2.1f", percentage * 100.0) + "%";
			result.add(Arrays.asList(activity.getActivityName(), activity.getBookingNumber(),
					formatDuration(totalMinutesId), percentageString));
		}
		result.add(Arrays.asList("Total","",formatDuration(totalMinutes),"100%"));
		return result;
	}

	private Collection<Booking> getRelevantBookings(final Collection<String> dayOrMonthParameter) {
		if (dayOrMonthParameter.size() > 0) {
			String dayOrMonthString = Iterables.get(dayOrMonthParameter, 0);
			if (dayOrMonthString.length() == 10) {
				return getBookingsForDay(LocalDate.parse(dayOrMonthString));
			}
			if (dayOrMonthString.length() == 7) {
				return getBookingsForMonth(YearMonth.parse(dayOrMonthString));
			}
		}
		return getBookingsForMonth(YearMonth.now());
	}

	private Collection<Booking> getBookingsForMonth(final YearMonth month) {
		return store.retrieveAll().stream().filter(b -> month.equals(YearMonth.from(b.getBookingday()))).collect(Collectors.toList());
	}

	private Collection<Booking> getBookingsForDay(final LocalDate day) {
		return store.retrieveAll().stream().filter(b -> day.equals(b.getBookingday())).collect(Collectors.toList());
	}

	private String formatDuration(final Duration duration) {
		long minutes = duration.toMinutes();
		char pre = minutes < 0 ? '-' : ' ';
		minutes = Math.abs(minutes);
		return String.format("%c%02d:%02d", pre, minutes / 60, minutes % 60);
	}

	public void setStore(final ObjectStore<Booking> store) {
		this.store = store;
	}
}
