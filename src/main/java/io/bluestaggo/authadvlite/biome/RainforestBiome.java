package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TallPlantFeature;

import java.util.Random;

public class RainforestBiome extends Biome {
	protected RainforestBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setGrassAttempts(10);
		decorator.setFlowerAttempts(4);
	}

	@Override
	public Feature getRandomTree(Random random) {
		return random.nextInt(3) == 0 ? this.largeTree : this.tree;
	}

	@Override
	public Feature getRandomGrass(Random random) {
		return new TallPlantFeature(Block.TALLGRASS.id, random.nextInt(4) == 0 ? 2 : 1);
	}
}
