package io.bluestaggo.authadvlite.layer;

import io.bluestaggo.authadvlite.mixin.layer.LayerAccessor;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;
import net.minecraft.world.gen.noise.OctaveNoiseGenerator;

import java.util.List;
import java.util.Random;

public class ClimateLayer extends Layer {
	private OctaveNoiseGenerator temperatureNoise;

	public ClimateLayer(long var1, Layer var3) {
		super(var1);
		this.parent = var3;
	}

	public void setLocalWorldSeed(long worldSeed) {
		super.setLocalWorldSeed(worldSeed);
		this.temperatureNoise = new OctaveNoiseGenerator(new Random(((LayerAccessor)this).getLocalWorldSeed()), 1);
	}

	public int[] nextValues(int var1, int var2, int var3, int var4) {
		int[] var9 = this.parent.nextValues(var1, var2, var3, var4);
		int[] var10 = IntArrays.get(var3 * var4);
		double[] temperatures = this.temperatureNoise.getNoise(new double[var3 * var4], var2, var1, var4, var3, 0.4D, 0.4D, 0.5D);

		for(int var11 = 0; var11 < var4; ++var11) {
			for(int var12 = 0; var12 < var3; ++var12) {
				int i = var12 + var11 * var3;
				int b = var9[i];
				if(b == 0) {
					var10[i] = 0;
				} else {
					this.setChunkSeed(var12 + var1, var11 + var2);
					double t = temperatures[i] * 2.2D;
					int ti = (int)Math.round(t);

					List<ClimateZone> zones = ClimateZone.getZonesFromTemperature(ti);
					int zi = this.nextInt(zones.size());
					var10[i] = zones.get(zi).id();
				}
			}
		}

		return var10;
	}
}
