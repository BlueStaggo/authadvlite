package io.bluestaggo.authadvlite.mixin.layer;

import io.bluestaggo.authadvlite.biome.AABiomes;
import io.bluestaggo.authadvlite.layer.ClimateZone;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.BiomeInitLayer;
import net.minecraft.world.biome.layer.Layer;
import net.minecraft.world.gen.chunk.OverworldGeneratorOptions;
import org.spongepowered.asm.mixin.*;

@Mixin(BiomeInitLayer.class)
public abstract class BiomeInitLayerMixin extends Layer {
	@Shadow @Final private OverworldGeneratorOptions options;

	@Unique private Biome[] oceanBiomes = new Biome[] {
		AABiomes.SEA_CRAGS,
		AABiomes.ARCHIPELAGO
	};

	public BiomeInitLayerMixin(long seed) {
		super(seed);
	}

	/**
	 * @author BlueStaggo
	 * @reason Big world gen changes
	 */
	@Overwrite
	public int[] nextValues(int x, int z, int width, int length) {
        int[] var5 = this.parent.nextValues(x, z, width, length);
        int[] var6 = IntArrays.get(width * length);

        for(int var7 = 0; var7 < length; ++var7) {
            for(int var8 = 0; var8 < width; ++var8) {
                this.setChunkSeed((long)(var8 + x), (long)(var7 + z));
                int var9 = var5[var8 + var7 * width];
				if (this.options != null && this.options.fixedBiome >= 0) {
                    var6[var8 + var7 * width] = this.options.fixedBiome;
				} else if (var9 == Biome.MUSHROOM_ISLAND.id || var9 == Biome.MUSHROOM_ISLAND_SHORE.id || var9 == Biome.FROZEN_OCEAN.id) {
                    var6[var8 + var7 * width] = var9;
                } else if (var9 > 0) {
					ClimateZone zone = ClimateZone.getZoneFromId(var9);
					Biome[] biomeArray = zone != null ? zone.biomes : new Biome[] { Biome.PLAINS };
					Biome biome = biomeArray[this.nextInt(biomeArray.length)];
					var6[var8 + var7 * width] = biome.id;
                } else if (this.nextInt(3) == 0) {
                    var6[var8 + var7 * width] = this.oceanBiomes[this.nextInt(this.oceanBiomes.length)].id;
                } else if (this.nextInt(3) == 0) {
                    var6[var8 + var7 * width] = Biome.DEEP_OCEAN.id;
                } else {
                    var6[var8 + var7 * width] = Biome.OCEAN.id;
                }
            }
        }

        return var6;
    }
}
