package io.bluestaggo.authadvlite.biome;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.ExtremeHillsBiome;
import net.minecraft.world.chunk.BlockStateStorage;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.PineTreeFeature;
import net.minecraft.world.gen.feature.SpruceTreeFeature;

import java.util.Random;

public class LegacyExtremeHillsBiome extends ExtremeHillsBiome {
	private final boolean moreTrees;

	public LegacyExtremeHillsBiome(int id, boolean moreTrees) {
		super(id, moreTrees);
		this.moreTrees = moreTrees;
	}

	@Override
	public AbstractTreeFeature getRandomTree(Random random) {
		if (!this.moreTrees) {
			return random.nextInt(10) == 0 ? this.largeTree : this.tree;
		}

		return random.nextInt(3) == 0
			? (random.nextInt(3) == 0 ? new PineTreeFeature() : new SpruceTreeFeature(false))
			: (random.nextInt(5) == 0 ? this.largeTree : this.tree);
	}

	@Override
	public void populateChunk(World world, Random random, BlockStateStorage blocks, int x, int z, double noise) {
		this.populate(world, random, blocks, x, z, noise);
	}
}
