package io.bluestaggo.authadvlite.biome;

import io.bluestaggo.authadvlite.mixin.biome.BiomeInvoker;
import net.minecraft.world.biome.Biome;

class BiomeBuilder {
	public Biome biome;

	public BiomeBuilder name(String name) {
		((BiomeInvoker) this.biome).invokeSetName(name);
		return this;
	}

	public BiomeBuilder color(int color) {
		((BiomeInvoker) this.biome).invokeSetBaseColor(color);
		return this;
	}

	public BiomeBuilder climate(float t, float d) {
		((BiomeInvoker) this.biome).invokeSetTemperatureAndDownfall(t, d);
		return this;
	}

	public BiomeBuilder snowy() {
		((BiomeInvoker) this.biome).invokeSetSnowy();
		return this;
	}

	public BiomeBuilder height(float b, float v) {
		((BiomeInvoker) this.biome).invokeSetHeight(b, v);
		return this;
	}

	public BiomeBuilder heightVariation(float b, float v) {
		((BiomeCustomInvoker) this.biome).authadvlite$setHeightVariation(b, v);
		return this;
	}
}
