package io.bluestaggo.authadvlite.biome;

import net.minecraft.world.biome.BeachBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.TaigaBiome;

import java.util.Arrays;

public class AABiomes {
	private static int id = 254;

	public static final boolean[] IS_OCEAN = new boolean[256];
	public static final boolean[] HAS_BEACH = new boolean[256];
	public static final Subvariant[] SUBVARIANTS = new Subvariant[256];

	public static final Biome COLD_TAIGA;
	public static final Biome PARCHED_FOREST;
	public static final Biome HIGHLANDS;
	public static final Biome HIGHLANDS_EDGE;
	public static final Biome SEA_CRAGS;
	public static final Biome ARCHIPELAGO;
	public static final Biome RAINFOREST;
	public static final Biome COLD_BEACH;
	public static final Biome FROSTY_HIGHLANDS;
	public static final Biome FROSTY_HIGHLANDS_EDGE;
	public static final Biome GRAVEL_BEACH;
	public static final Biome WINDSWEPT_CRAGS;
	public static final Biome SAVANNA;
	public static final Biome WOODED_HILLS;
	public static final Biome SNOWCAPPED_HILLS;
	public static final Biome BIRCH_FOREST;
	public static final Biome MUSHROOM_VALLEY;
	public static final Biome MEGA_TAIGA;
	public static final Biome FROSTED_BIRCH_FOREST;
	public static final Biome SNOWCAPPED_FOREST;

	static {
		BiomeBuilder biomeBuilder = new BiomeBuilder();

		biomeBuilder.biome = COLD_TAIGA = new TaigaBiome(getID(30)); // Fall back to 1.7's Cold Taiga
		biomeBuilder.name("Cold Taiga").color(0x7fffff).climate(0.0F, 0.8F).snowy().height(0.1F, 0.6F).heightVariation(0.2F, 0.6F);

		biomeBuilder.biome = PARCHED_FOREST = new ParchedForestBiome(getID());
		biomeBuilder.name("Parched Forest").color(0x8d7e2f).climate(0.8F, 0.3F).height(0.3F, 1.8F);

		biomeBuilder.biome = HIGHLANDS = new HighlandsBiome(getID());
		biomeBuilder.name("Highlands").color(0x6090a0).climate(0.6F, 0.7F).height(1.8F, 0.8F);

		biomeBuilder.biome = HIGHLANDS_EDGE = new HighlandsBiome(getID());
		biomeBuilder.name("Highlands Edge").color(0x65d4b7).climate(0.6F, 0.7F).height(1.0F, 0.6F);

		biomeBuilder.biome = SEA_CRAGS = new SeaCragsBiome(getID());
		biomeBuilder.name("Sea Crags").color(0x000040).climate(0.5F, 0.5F).height(-1.0F, 0.5F);

		biomeBuilder.biome = ARCHIPELAGO = new ArchipelagoBiome(getID());
		biomeBuilder.name("Archipelago").color(0x0000a0).climate(0.8F, 0.9F).height(-1.0F, 1.0F);

		biomeBuilder.biome = RAINFOREST = new RainforestBiome(getID());
		biomeBuilder.name("Rainforest").color(0x1a9e1e).climate(1.2F, 0.9F).height(0.0F, 0.5F).heightVariation(0.2F, 1.0F);

		biomeBuilder.biome = COLD_BEACH = new BeachBiome(getID(26)); // Fall back to 1.7's Cold Beach
		biomeBuilder.name("Cold Beach").color(0xfaf0c0).climate(0.0F, 0.5F).snowy().height(0.0F, 0.1F);

		biomeBuilder.biome = FROSTY_HIGHLANDS = new HighlandsBiome(getID());
		biomeBuilder.name("Frosty Highlands").color(0x65a7a1).climate(0.0F, 0.1F).snowy().height(1.8F, 0.8F);

		biomeBuilder.biome = FROSTY_HIGHLANDS_EDGE = new HighlandsBiome(getID());
		biomeBuilder.name("Frosty Highlands Edge").color(0x65eaea).climate(0.0F, 0.1F).snowy().height(1.0F, 0.6F);

		biomeBuilder.biome = GRAVEL_BEACH = new GravelBeachBiome(getID());
		biomeBuilder.name("Gravel Beach").color(0xa0a0a0).climate(0.8F, 0.5F).height(0.0F, 0.1F);

		biomeBuilder.biome = WINDSWEPT_CRAGS = new WindsweptCragsBiome(getID());
		biomeBuilder.name("Windswept Crags").color(0x416f1f).climate(0.2F, 0.3F).height(0.5F, 2.5F);

		biomeBuilder.biome = SAVANNA = new SavannaBiome(getID(35)); // Fall back to 1.7's Savanna
		biomeBuilder.name("Savanna").color(0xc6ce4c).climate(0.9F, 0.1F).height(0.1F, 0.3F).heightVariation(0.1F, 0.3F);

		biomeBuilder.biome = WOODED_HILLS = new WoodedHillsBiome(getID(34)); // Fall back to 1.7's Extreme Hills+
		biomeBuilder.name("Wooded Hills").color(0x316a46).climate(0.2F, 0.3F).height(0.3F, 1.8F);

		biomeBuilder.biome = SNOWCAPPED_HILLS = new SnowcappedHillsBiome(getID());
		biomeBuilder.name("Snowcapped Hills").color(0x606090).climate(0.2F, 0.3F).height(1.2F, 1.2F);

		biomeBuilder.biome = BIRCH_FOREST = new BirchForestBiome(getID(27)); // Fall back to 1.7's Birch Forest
		biomeBuilder.name("Birch Forest").color(0x307444).climate(0.6F, 0.6F).height(0.1F, 0.6F).heightVariation(0.2F, 0.6F);

		biomeBuilder.biome = MUSHROOM_VALLEY = new MushroomValleyBiome(getID());
		biomeBuilder.name("Mushroom Valley").color(0xff007f).climate(0.9F, 1.0F).height(1.0F, 1.0F);

		biomeBuilder.biome = MEGA_TAIGA = new MegaTaigaBiome(getID(32)); // Fall back to 1.7's Mega Taiga
		biomeBuilder.name("Mega Taiga").color(0x596651).climate(0.3F, 0.8F).height(0.1F, 0.6F).heightVariation(0.2F, 0.6F);

		biomeBuilder.biome = FROSTED_BIRCH_FOREST = new BirchForestBiome(getID());
		biomeBuilder.name("Frosted Birch Forest").color(0x7af8c7).climate(0.1F, 0.4F).snowy().height(0.1F, 0.6F).heightVariation(0.2F, 0.6F);

		biomeBuilder.biome = SNOWCAPPED_FOREST = new SnowcappedHillsBiome(getID()).addTrees();
		biomeBuilder.name("Snowcapped Forest").color(0x708090).climate(0.2F, 0.3F).height(1.5F, 1.5F);

		IS_OCEAN[Biome.OCEAN.id] = true;
		IS_OCEAN[Biome.FROZEN_OCEAN.id] = true;
		IS_OCEAN[SEA_CRAGS.id] = true;
		IS_OCEAN[ARCHIPELAGO.id] = true;

		Arrays.fill(HAS_BEACH, true);
		HAS_BEACH[Biome.SWAMPLAND.id] = false;
		HAS_BEACH[Biome.RIVER.id] = false;
		HAS_BEACH[Biome.FROZEN_RIVER.id] = false;
		HAS_BEACH[AABiomes.FROSTY_HIGHLANDS.id] = false;
		HAS_BEACH[AABiomes.FROSTY_HIGHLANDS_EDGE.id] = false;
		HAS_BEACH[AABiomes.SNOWCAPPED_HILLS.id] = false;
		HAS_BEACH[AABiomes.SNOWCAPPED_FOREST.id] = false;
		HAS_BEACH[AABiomes.MUSHROOM_VALLEY.id] = false;
		HAS_BEACH[AABiomes.HIGHLANDS.id] = false;
		HAS_BEACH[AABiomes.HIGHLANDS_EDGE.id] = false;
		HAS_BEACH[AABiomes.RAINFOREST.id] = false;
		HAS_BEACH[AABiomes.WINDSWEPT_CRAGS.id] = false;
		HAS_BEACH[Biome.EXTREME_HILLS.id] = false;
		HAS_BEACH[AABiomes.PARCHED_FOREST.id] = false;

		SUBVARIANTS[Biome.PLAINS.id] = new Subvariant(Biome.FOREST, 3);
		SUBVARIANTS[Biome.ICE_PLAINS.id] = new Subvariant(Biome.ICE_MOUNTAINS, 5);
		SUBVARIANTS[Biome.EXTREME_HILLS.id] = new Subvariant(AABiomes.WOODED_HILLS, 3);
		SUBVARIANTS[AABiomes.SAVANNA.id] = new Subvariant(Biome.DESERT, 5);
		SUBVARIANTS[AABiomes.SNOWCAPPED_HILLS.id] = new Subvariant(AABiomes.SNOWCAPPED_FOREST, 5);
	}

	private static int getID() {
		while (id >= 0 && Biome.BY_ID[id] != null) {
			id--;
		}

		if (id < 0) {
			throw new RuntimeException("Ran out of IDs for Authentic Adventure biomes!");
		}

		return id--;
	}

	private static int getID(int tryID) {
		if (Biome.BY_ID[tryID] != null) return getID();
		return tryID;
	}

	public static class Subvariant {
		public final Biome biome;
		public final int chance;

		public Subvariant(Biome biome, int chance) {
			this.biome = biome;
			this.chance = chance;
		}
	}
}
