package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.PalmTreeFeature;
import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class ArchipelagoBiome extends Biome {
	private final PalmTreeFeature palmTree = new PalmTreeFeature(false);

	protected ArchipelagoBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(1);
		decorator.setGrassAttempts(3);
	}

	@Override
	public AbstractTreeFeature getRandomTree(Random random) {
		return this.palmTree;
	}
}
