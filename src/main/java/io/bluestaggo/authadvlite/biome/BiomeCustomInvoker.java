package io.bluestaggo.authadvlite.biome;

import net.minecraft.world.biome.Biome;

public interface BiomeCustomInvoker {
	Biome authadvlite$setHeightVariation(float baseHeightBoost, float heightVariationBoost);
	float authadvlite$getBaseHeightBoost();
	float authadvlite$getHeightVariationBoost();
}
