package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.entity.living.mob.passive.animal.MooshroomEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeature;

import java.util.Random;

public class MushroomValleyBiome extends Biome {
	private final HugeMushroomFeature hugeMushroom = new HugeMushroomFeature();

	protected MushroomValleyBiome(int id) {
		super(id);
		this.passiveEntries.add(new SpawnEntry(MooshroomEntity.class, 4, 4, 4));

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setFlowerAttempts(10);
	}

	public Feature getRandomTree(Random random) {
		return random.nextInt(5) == 0 ? this.hugeMushroom
			: random.nextInt(5) == 0 ? this.largeTree
			: this.tree;
	}
}
