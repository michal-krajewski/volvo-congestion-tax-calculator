package pl.byteit.volvodemo.taxcalculator.rule;

import pl.byteit.volvodemo.taxcalculator.VehicleToll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class SingleChargeRule implements Function<List<VehicleToll>, List<VehicleToll>> {

	private final Duration singleChargeDuration;

	public SingleChargeRule(Duration singleChargeDuration) {
		this.singleChargeDuration = singleChargeDuration;
	}

	@Override
	public List<VehicleToll> apply(List<VehicleToll> vehicleTolls) {
		List<VehicleToll> sortedTolls = vehicleTolls.stream().sorted(comparing(VehicleToll::dateTime)).toList();
		SingleChargeGroups singleChargeGroups = new SingleChargeGroups();
		for (VehicleToll vehicleToll : sortedTolls) {
			singleChargeGroups.getLastGroup()
					.filter(group -> group.isInGroup(vehicleToll))
					.ifPresentOrElse(
							group -> group.updateMaxToll(vehicleToll),
							() -> singleChargeGroups.addGroup(SingleChargeGroup.of(vehicleToll, singleChargeDuration))
					);
		}
		return singleChargeGroups.toTolls();
	}

	private static class SingleChargeGroups {
		private final List<SingleChargeGroup> groups = new ArrayList<>();

		Optional<SingleChargeGroup> getLastGroup() {
			return groups.isEmpty() ?
					Optional.empty() :
					Optional.of(groups.get(groups.size() - 1));
		}

		void addGroup(SingleChargeGroup group) {
			groups.add(group);
		}

		List<VehicleToll> toTolls() {
			return groups.stream()
					.map(SingleChargeGroup::getCurrentHighestToll)
					.toList();
		}

	}

	private static class SingleChargeGroup {

		private final LocalDateTime groupMaxTime;
		private VehicleToll currentHighestToll;

		private SingleChargeGroup(LocalDateTime groupMaxTime, VehicleToll currentHighestToll) {
			this.groupMaxTime = groupMaxTime;
			this.currentHighestToll = currentHighestToll;
		}

		static SingleChargeGroup of(VehicleToll vehicleToll, Duration singleChageDuration) {
			return new SingleChargeGroup(vehicleToll.dateTime().plus(singleChageDuration), vehicleToll);
		}

		boolean isInGroup(VehicleToll vehicleToll) {
			return !vehicleToll.dateTime().isAfter(groupMaxTime);
		}

		public void updateMaxToll(VehicleToll vehicleToll) {
			if (currentHighestToll.congestionTax() < vehicleToll.congestionTax())
				currentHighestToll = vehicleToll;
		}

		VehicleToll getCurrentHighestToll() {
			return currentHighestToll;
		}

	}

}
