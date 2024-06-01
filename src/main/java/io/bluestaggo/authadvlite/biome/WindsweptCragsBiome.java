package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.feature.CragFeature;
import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.util.math.BlockPos;
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

	public void decorate(World world, Random random, BlockPos pos) {
		super.decorate(world, random, pos);

		BlockPos mpos = pos.add(random.nextInt(16) + 8, 0, random.nextInt(16) + 8);
		cragFeature.place(world, random, mpos);
	}
}
