package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.ExtremeHillsBiome;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class SnowcappedHillsBiome extends ExtremeHillsBiome {
	protected SnowcappedHillsBiome(int id, boolean moreTrees) {
		super(id, moreTrees);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(moreTrees ? 3 : 0);
		decorator.setGrassAttempts(3);
	}

	@Override
	public AbstractTreeFeature getRandomTree(Random random) {
		return random.nextInt(3) != 0
			? (random.nextInt(3) == 0 ? new PineTreeFeature() : new SpruceTreeFeature(false))
			: (random.nextInt(5) == 0 ? this.largeTree : this.tree);
	}
}
