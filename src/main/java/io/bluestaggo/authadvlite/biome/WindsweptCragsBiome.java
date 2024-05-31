package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.CragFeature;
import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class WindsweptCragsBiome extends Biome {
	private final CragFeature cragFeature = new CragFeature();

	protected WindsweptCragsBiome(int id) {
		super(id);

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(1);
	}

	public void decorate(World world, Random random, int x, int z) {
		super.decorate(world, random, x, z);

		int xx = x + random.nextInt(16) + 8;
		int zz = z + random.nextInt(16) + 8;
		cragFeature.place(world, random, xx, 0, zz);
	}
}
