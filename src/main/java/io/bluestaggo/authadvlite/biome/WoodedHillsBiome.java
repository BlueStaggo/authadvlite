package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ExtremeHillsBiome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GiantSpruceTreeFeature;
import net.minecraft.world.gen.feature.SpruceTreeFeature;

import java.util.Random;

public class WoodedHillsBiome extends ExtremeHillsBiome {
	protected WoodedHillsBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(5);
		decorator.setGrassAttempts(3);
	}

	@Override
	public Feature getRandomTree(Random random) {
		return random.nextInt(3) == 0
			? (random.nextInt(3) == 0 ? new GiantSpruceTreeFeature() : new SpruceTreeFeature(false))
			: (random.nextInt(5) == 0 ? this.largeTree : this.tree);
	}
}
