package io.bluestaggo.authadvlite.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CragFeature extends Feature {
	public boolean place(World world, Random random, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		BlockPos.Mutable mpos = new BlockPos.Mutable(x, 0, z);

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

				mpos.set(xx + x, 0, zz + z);
				int ground = world.getSurfaceHeight(mpos).getY();
				mpos.set(mpos.getX(), ground, mpos.getZ());
				if (intSlope > 0 && world.getBlockState(mpos.down()).getBlock() == Blocks.GRASS) {
					this.setBlockState(world, mpos.down(), Blocks.DIRT.defaultState());
				}

				for (int yy = 0; yy < intSlope; yy++) {
					mpos.set(xx + x, yy + ground, zz + z);
					this.setBlockState(world, mpos, Blocks.STONE.defaultState());
				}
			}
		}

		return true;
	}
}
