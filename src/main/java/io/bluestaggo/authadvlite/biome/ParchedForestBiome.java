package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BirchTreeFeature;

import java.util.Random;

public class ParchedForestBiome extends Biome {
	private final BirchTreeFeature birchTree = new BirchTreeFeature(false, false);

	protected ParchedForestBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setGrassAttempts(2);
		decorator.setHugeMushroomAttempts(1);
	}

	@Override
	public AbstractTreeFeature getRandomTree(Random random) {
		return random.nextInt(5) == 0 ? this.birchTree
			: random.nextInt(10) == 0 ? this.largeTree
			: this.tree;
	}
}
