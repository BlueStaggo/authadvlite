package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.CragFeature;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class SeaCragsBiome extends Biome {
	private final CragFeature cragFeature = new CragFeature();

	protected SeaCragsBiome(int id) {
		super(id);
		this.passiveEntries.clear();
	}

	public void decorate(World world, Random random, int x, int z) {
		super.decorate(world, random, x, z);

		int cragCount = 2 + random.nextInt(7);
		for (int i = 0; i < cragCount; i++) {
			int xx = x + random.nextInt(16) + 8;
			int zz = z + random.nextInt(16) + 8;
			cragFeature.place(world, random, xx, 0, zz);
		}
	}
}
