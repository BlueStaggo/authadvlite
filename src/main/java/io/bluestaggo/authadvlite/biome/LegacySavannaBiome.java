package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.SavannaBiome;

public class LegacySavannaBiome extends SavannaBiome {
	public LegacySavannaBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(0);
		decorator.setFlowerAttempts(0);
		decorator.setGrassAttempts(0);
	}
}
