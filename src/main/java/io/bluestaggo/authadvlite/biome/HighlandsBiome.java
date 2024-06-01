package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class HighlandsBiome extends Biome {
	protected HighlandsBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(6);
		decorator.setGrassAttempts(2);
	}

	@Override
	protected Biome setSnowy() {
		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setGrassAttempts(0);
		decorator.setFlowerAttempts(0);
		decorator.setMushroomAttempts(0);
		return super.setSnowy();
	}

	@Override
	public AbstractTreeFeature getRandomTree(Random random) {
		return random.nextInt(2) == 0
			? (random.nextInt(3) == 0 ? new PineTreeFeature() : new SpruceTreeFeature(false))
			: (random.nextInt(10) == 0 ? this.largeTree : this.tree);
	}
}
