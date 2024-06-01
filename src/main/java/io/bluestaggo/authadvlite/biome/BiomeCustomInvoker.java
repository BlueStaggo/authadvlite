package io.bluestaggo.authadvlite.biome;

import net.minecraft.world.biome.Biome;

public interface BiomeCustomInvoker {
	Biome authadvlite$setHeightVariation(Biome.Height heightBoost);
	float authadvlite$getBaseHeightBoost();
	float authadvlite$getHeightVariationBoost();
}
