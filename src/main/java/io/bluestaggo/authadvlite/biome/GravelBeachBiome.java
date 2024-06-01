package io.bluestaggo.authadvlite.biome;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.BeachBiome;

public class GravelBeachBiome extends BeachBiome {
	public GravelBeachBiome(int i) {
		super(i);
		this.surfaceBlock = Blocks.AIR;
		this.subsurfaceBlock = Blocks.GRAVEL;
	}
}
