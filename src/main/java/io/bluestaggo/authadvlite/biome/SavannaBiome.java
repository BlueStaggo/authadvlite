package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.entity.living.mob.passive.animal.HorseBaseEntity;
import net.minecraft.world.biome.Biome;

public class SavannaBiome extends Biome {
	protected SavannaBiome(int id) {
		super(id);
        this.passiveEntries.add(new Biome.SpawnEntry(HorseBaseEntity.class, 5, 2, 6));

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(0);
		decorator.setFlowerAttempts(0);
		decorator.setGrassAttempts(0);
	}
}
