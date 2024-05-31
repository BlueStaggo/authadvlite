package io.bluestaggo.authadvlite.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BeachBiome;

public class GravelBeachBiome extends BeachBiome {
	public GravelBeachBiome(int i) {
		super(i);
		this.surfaceBlockId = 0;
		this.subsurfaceBlockId = (byte) Block.GRAVEL.id;
	}
}
