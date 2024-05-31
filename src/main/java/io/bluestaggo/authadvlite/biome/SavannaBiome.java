package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;

public class SavannaBiome extends Biome {
	protected SavannaBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(0);
		decorator.setFlowerAttempts(0);
		decorator.setGrassAttempts(0);
	}
}
