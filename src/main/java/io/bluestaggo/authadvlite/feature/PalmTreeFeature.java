package io.bluestaggo.authadvlite.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class PalmTreeFeature extends Feature {
	public boolean place(World world, Random random, int x, int y, int z) {
		int ground = world.getBlock(x, y - 1, z);
		if (ground != Block.GRASS.id && ground != Block.SAND.id) {
			return false;
		}

		int height = random.nextInt(3) + 3;
		int height2 = random.nextInt(2) + 1;
		int height3 = random.nextInt(2);
		int heightTotal = height + height2 + height3;

		{
			int dir = random.nextInt(4);
			int dirX = dir == 0 ? 1 : dir == 2 ? -1 : 0;
			int dirZ = dir == 1 ? 1 : dir == 3 ? -1 : 0;

			for (int yy = 0; yy < heightTotal; yy++) {
				if (yy == height || yy == height + height2) {
					x += dirX;
					z += dirZ;
				}
				this.setBlockWithMetadata(world, x, y + yy, z, Block.LOG.id, 3);
			}
		}

		y += heightTotal;
		if (world.isAir(x, y, z)) {
			this.setBlockWithMetadata(world, x, y, z, Block.LEAVES.id, 3);
		}

		for (int dir = 0; dir < 4; dir++) {
			int dirX = dir == 0 ? 1 : dir == 2 ? -1 : 0;
			int dirZ = dir == 1 ? 1 : dir == 3 ? -1 : 0;

			for (int i = 1; i < 4; i++) {
				if (i <= 2 && world.isAir(x + dirX * i, y, z + dirZ * i)) {
					this.setBlockWithMetadata(world, x + dirX * i, y, z + dirZ * i, Block.LEAVES.id, 3);
				}
				if (i >= 2 && world.isAir(x + dirX * i, y - 1, z + dirZ * i)) {
					this.setBlockWithMetadata(world, x + dirX * i, y - 1, z + dirZ * i, Block.LEAVES.id, 3);
				}
			}
		}

		return true;
	}
}
