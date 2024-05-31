package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.TallBirchTreeFeature;
import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.entity.living.mob.passive.animal.tamable.WolfEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GiantSpruceTreeFeature;
import net.minecraft.world.gen.feature.SpruceTreeFeature;

import java.util.Random;

public class BirchForestBiome extends Biome {
	private final TallBirchTreeFeature tallBirchTree = new TallBirchTreeFeature(false);

	protected BirchForestBiome(int id) {
		super(id);
		this.passiveEntries.add(new SpawnEntry(WolfEntity.class, 5, 4, 4));

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setGrassAttempts(2);
		decorator.setFlowerAttempts(4);
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
	public Feature getRandomTree(Random random) {
		return this.tallBirchTree;
	}
}
