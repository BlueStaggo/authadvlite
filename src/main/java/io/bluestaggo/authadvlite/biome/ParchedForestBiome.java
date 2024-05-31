package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class ParchedForestBiome extends Biome {
	protected ParchedForestBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setGrassAttempts(2);
		decorator.setHugeMushroomAttempts(1);
	}

	@Override
	public Feature getRandomTree(Random random) {
		return random.nextInt(5) == 0 ? this.spruceTree
			: random.nextInt(10) == 0 ? this.largeTree
			: this.tree;
	}
}
