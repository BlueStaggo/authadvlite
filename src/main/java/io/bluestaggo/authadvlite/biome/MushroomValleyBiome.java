package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.living.mob.passive.animal.MooshroomEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DarkOakTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeature;

import java.util.Random;

public class MushroomValleyBiome extends Biome {
    protected static final DarkOakTreeFeature DARK_OAK_TREE = new DarkOakTreeFeature(false);

	private final AbstractTreeFeature hugeMushroom = new AbstractTreeFeature(false) {
		private final HugeMushroomFeature baseFeature = new HugeMushroomFeature();

		@Override
		public boolean place(World world, Random random, int x, int y, int z) {
			return baseFeature.place(world, random, x, y, z);
		}
	};

	protected MushroomValleyBiome(int id) {
		super(id);
		this.passiveEntries.add(new SpawnEntry(MooshroomEntity.class, 4, 4, 4));

		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setTreeAttempts(10);
		decorator.setFlowerAttempts(10);
	}

	public AbstractTreeFeature getRandomTree(Random random) {
		return random.nextInt(5) == 0 ? this.hugeMushroom
			: random.nextInt(2) == 0 ? DARK_OAK_TREE
			: random.nextInt(5) == 0 ? this.largeTree
			: this.tree;
	}

	public void populateChunk(World world, Random random, Block[] blocks, byte[] blockMetadata, int x, int y, double noise) {
		this.surfaceBlock = Math.abs(noise) > 3.0D ? Blocks.MYCELIUM : Blocks.GRASS;

		super.populateChunk(world, random, blocks, blockMetadata, x, y, noise);
	}
}
