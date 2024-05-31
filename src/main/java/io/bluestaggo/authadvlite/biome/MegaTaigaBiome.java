package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.TallSpruceTreeFeature;
import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GiantSpruceTreeFeature;
import net.minecraft.world.gen.feature.SpruceTreeFeature;

import java.util.Random;

public class MegaTaigaBiome extends Biome {
	private final TallSpruceTreeFeature tallSpruceA = new TallSpruceTreeFeature(false);
	private final TallSpruceTreeFeature tallSpruceB = new TallSpruceTreeFeature(true);

	protected MegaTaigaBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setGrassAttempts(7);
		decorator.setDeadBushAttempts(1);
		decorator.setMushroomAttempts(3);
	}

	@Override
	public Feature getRandomTree(Random random) {
		return random.nextInt(3) == 0
			? (random.nextInt(13) != 0 ? this.tallSpruceA : this.tallSpruceB)
			: (random.nextInt(3) == 0 ? new GiantSpruceTreeFeature() : new SpruceTreeFeature(false));
	}
}
