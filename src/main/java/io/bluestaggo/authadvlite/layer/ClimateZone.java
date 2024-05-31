package io.bluestaggo.authadvlite.layer;

import io.bluestaggo.authadvlite.biome.AABiomes;
import net.minecraft.world.biome.Biome;

import java.util.*;

public enum ClimateZone {
	TEMPERATE(
		0,
		Biome.PLAINS,
		Biome.FOREST,
		AABiomes.BIRCH_FOREST,
		Biome.EXTREME_HILLS,
		AABiomes.HIGHLANDS,
		Biome.TAIGA
	),
	HOT(
		1,
		Biome.JUNGLE,
		Biome.JUNGLE,
		AABiomes.RAINFOREST,
		AABiomes.RAINFOREST,
		Biome.DESERT,
		Biome.DESERT,
		AABiomes.SAVANNA,
		AABiomes.MUSHROOM_VALLEY,
		AABiomes.PARCHED_FOREST,
		Biome.SWAMPLAND
	),
	COOL(
		0,
		Biome.PLAINS,
		Biome.TAIGA,
		AABiomes.BIRCH_FOREST,
		AABiomes.MEGA_TAIGA,
		Biome.EXTREME_HILLS,
		AABiomes.HIGHLANDS,
		AABiomes.WINDSWEPT_CRAGS,
		AABiomes.SNOWCAPPED_HILLS,
		AABiomes.SNOWCAPPED_HILLS // Snowcapped peaks are a biome I regret adding
	),
	WARM(
		0,
		Biome.FOREST,
		Biome.FOREST,
		AABiomes.PARCHED_FOREST,
		AABiomes.PARCHED_FOREST,
		Biome.SWAMPLAND,
		Biome.SWAMPLAND,
		Biome.PLAINS,
		AABiomes.SAVANNA
	),
	SNOWY(
		-1,
		AABiomes.COLD_TAIGA,
		AABiomes.COLD_TAIGA,
		AABiomes.FROSTED_BIRCH_FOREST,
		Biome.ICE_PLAINS,
		Biome.ICE_PLAINS,
		Biome.ICE_MOUNTAINS,
		AABiomes.FROSTY_HIGHLANDS
	);

	public static final ClimateZone[] allZones = values();
	private static final Map<Integer, List<ClimateZone>> zonesPerTemperature;
	public static final List<ClimateZone> zeroZones;
	private static final int minTemperature;
	private static final int maxTemperature;

	static {
		Map<Integer, List<ClimateZone>> zones = new HashMap<>();
		int minTemp = 0;
		int maxTemp = 0;

		for (ClimateZone zone : allZones) {
			if (zone.temperature < minTemp) {
				minTemp = zone.temperature;
			}
			if (zone.temperature > maxTemp) {
				maxTemp = zone.temperature;
			}
			zones.computeIfAbsent(zone.temperature, k -> new ArrayList<>()).add(zone);
		}

		for (int k : zones.keySet()) {
			zones.computeIfPresent(k, (y, v) -> Collections.unmodifiableList(v));
		}

		zonesPerTemperature = Collections.unmodifiableMap(zones);
		zeroZones = zonesPerTemperature.get(0);

		minTemperature = minTemp;
		maxTemperature = maxTemp;
	}

	public final int temperature;
	public final Biome[] biomes;

	ClimateZone(int temperature, Biome... biomes) {
		this.temperature = temperature;
		this.biomes = biomes;
	}

	public int id() {
		return this.ordinal() + 1;
	}

	public static ClimateZone getZoneFromId(int id) {
		if (id < 1 || id > allZones.length + 1) return null;
		return allZones[id - 1];
	}

	public static List<ClimateZone> getZonesFromTemperature(int temperature) {
		if (temperature < minTemperature) {
			temperature = minTemperature;
		} else if (temperature > maxTemperature) {
			temperature = maxTemperature;
		}

		return zonesPerTemperature.getOrDefault(temperature, zeroZones);
	}
}

