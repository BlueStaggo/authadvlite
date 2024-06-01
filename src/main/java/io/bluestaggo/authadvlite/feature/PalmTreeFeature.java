package io.bluestaggo.authadvlite.feature;

import net.minecraft.block.*;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import java.util.Random;

public class PalmTreeFeature extends AbstractTreeFeature {
	private static final BlockState LOG_STATE = Blocks.LOG.defaultState().set(LogBlock.VARIANT, PlanksBlock.Variant.JUNGLE);
	private static final BlockState LEAVES_STATE = Blocks.LEAVES.defaultState().set(LeavesBlock.VARIANT, PlanksBlock.Variant.JUNGLE);

	public PalmTreeFeature(boolean bl) {
		super(bl);
	}

	public boolean place(World world, Random random, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		BlockState ground = world.getBlockState(pos.down());
		if (ground != Blocks.GRASS && ground != Blocks.SAND) {
			return false;
		}

		int height = random.nextInt(3) + 3;
		int height2 = random.nextInt(2) + 1;
		int height3 = random.nextInt(2);
		int heightTotal = height + height2 + height3;

		BlockPos.Mutable mpos = new BlockPos.Mutable(x, y, z);

		{
			int dir = random.nextInt(4);
			int dirX = dir == 0 ? 1 : dir == 2 ? -1 : 0;
			int dirZ = dir == 1 ? 1 : dir == 3 ? -1 : 0;

			for (int yy = 0; yy < heightTotal; yy++) {
				if (yy == height || yy == height + height2) {
					x += dirX;
					z += dirZ;
				}

				mpos.set(x, y + yy, z);
				this.setBlockState(world, mpos, LOG_STATE);
			}
		}

		y += heightTotal;
		mpos.set(x, y, z);
		if (world.isAir(mpos)) {
			this.setBlockState(world, mpos, LEAVES_STATE);
		}

		for (int dir = 0; dir < 4; dir++) {
			int dirX = dir == 0 ? 1 : dir == 2 ? -1 : 0;
			int dirZ = dir == 1 ? 1 : dir == 3 ? -1 : 0;

			for (int i = 1; i < 4; i++) {
				mpos.set(x + dirX * i, y, z + dirZ * i);
				if (i <= 2 && world.isAir(mpos)) {
					this.setBlockState(world, mpos, LEAVES_STATE);
				}

				mpos.set(x + dirX * i, y - 1, z + dirZ * i);
				if (i >= 2 && world.isAir(mpos)) {
					this.setBlockState(world, mpos, LEAVES_STATE);
				}
			}
		}

		return true;
	}
}
