package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.CragFeature;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class SeaCragsBiome extends Biome {
	private final CragFeature cragFeature = new CragFeature();

	protected SeaCragsBiome(int id) {
		super(id);
		this.passiveEntries.clear();
	}

	public void decorate(World world, Random random, BlockPos pos) {
		super.decorate(world, random, pos);

		int cragCount = 2 + random.nextInt(7);
		BlockPos.Mutable mpos = new BlockPos.Mutable();
		for (int i = 0; i < cragCount; i++) {
			mpos.set(pos.getX() + random.nextInt(16) + 8, 0, pos.getZ() + random.nextInt(16) + 8);
			cragFeature.place(world, random, mpos);
		}
	}
}
