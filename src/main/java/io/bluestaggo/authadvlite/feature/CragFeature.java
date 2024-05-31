package io.bluestaggo.authadvlite.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CragFeature extends Feature {
	public boolean place(World world, Random random, int x, int y, int z) {
		int height = random.nextInt(14) + 4;
		int radius = random.nextInt(6) + 3;
		float radiusSquare = radius * radius;

		for (int xx = -radius; xx <= radius; xx++) {
			for (int zz = -radius; zz <= radius; zz++) {
				float distSquare = xx * xx + zz * zz;
				float slope = 1 - distSquare / radiusSquare;

				if (slope < 0) continue;
				if (slope > 1) slope = 1;

				slope *= slope;
				int intSlope = (int) (slope * height);

				int ground = world.getSurfaceHeight(xx + x, zz + z);
				if (intSlope > 0 && world.getBlock(xx + x, ground - 1, zz + z) == Block.GRASS.id) {
					this.setBlockWithMetadata(world, xx + x, ground - 1, zz + z, Block.DIRT.id, 0);
				}

				for (int yy = 0; yy < intSlope; yy++) {
					this.setBlockWithMetadata(world, xx + x, yy + ground, zz + z, Block.STONE.id, 0);
				}
			}
		}

		return true;
	}
}
