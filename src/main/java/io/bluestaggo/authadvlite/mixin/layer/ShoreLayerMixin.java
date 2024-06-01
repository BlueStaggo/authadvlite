package io.bluestaggo.authadvlite.mixin.layer;

import io.bluestaggo.authadvlite.biome.AABiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;
import net.minecraft.world.biome.layer.ShoreLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ShoreLayer.class)
public abstract class ShoreLayerMixin extends Layer {
	public ShoreLayerMixin(long seed) {
		super(seed);
	}

	/**
	 * @author BlueStaggo
	 * @reason Many changes
	 */
	@Overwrite
	public int[] nextValues(int x, int z, int width, int length) {
        int[] var5 = this.parent.nextValues(x - 1, z - 1, width + 2, length + 2);
        int[] var6 = IntArrays.get(width * length);

        for(int var7 = 0; var7 < length; ++var7) {
            for(int var8 = 0; var8 < width; ++var8) {
                this.setChunkSeed((long)(var8 + x) >> 1, (long)(var7 + z) >> 1);
                int var9 = var5[var8 + 1 + (var7 + 1) * (width + 2)];
                int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (width + 2)];
                int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (width + 2)];
                int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (width + 2)];
                int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (width + 2)];
	            boolean landlocked = !AABiomes.IS_OCEAN[var10] && !AABiomes.IS_OCEAN[var11] && !AABiomes.IS_OCEAN[var12] && !AABiomes.IS_OCEAN[var13];
	            if (var9 == Biome.MUSHROOM_ISLAND.id) {
                    if (landlocked) {
                        var6[var8 + var7 * width] = var9;
                    } else {
                        var6[var8 + var7 * width] = Biome.MUSHROOM_ISLAND_SHORE.id;
                    }
				} else if (var9 == AABiomes.HIGHLANDS.id) {
                    if (var10 == AABiomes.HIGHLANDS.id && var11 == AABiomes.HIGHLANDS.id && var12 == AABiomes.HIGHLANDS.id && var13 == AABiomes.HIGHLANDS.id) {
                        var6[var8 + var7 * width] = var9;
                    } else {
                        var6[var8 + var7 * width] = AABiomes.HIGHLANDS_EDGE.id;
                    }
				} else if (var9 == AABiomes.FROSTY_HIGHLANDS.id) {
                    if (var10 == AABiomes.FROSTY_HIGHLANDS.id && var11 == AABiomes.FROSTY_HIGHLANDS.id && var12 == AABiomes.FROSTY_HIGHLANDS.id && var13 == AABiomes.FROSTY_HIGHLANDS.id) {
                        var6[var8 + var7 * width] = var9;
                    } else {
                        var6[var8 + var7 * width] = AABiomes.FROSTY_HIGHLANDS_EDGE.id;
                    }
                } else if (AABiomes.HAS_BEACH[var9] && !AABiomes.IS_OCEAN[var9]) {
                    if (landlocked) {
                        var6[var8 + var7 * width] = var9;
                    } else {
                        var6[var8 + var7 * width] =
		                        Biome.byId(var10).temperature < 0.15F
				                        || Biome.byId(var11).temperature < 0.15F
				                        || Biome.byId(var12).temperature < 0.15F
				                        || Biome.byId(var13).temperature < 0.15F ? AABiomes.COLD_BEACH.id
		                        : this.nextInt(4) == 3 ? AABiomes.GRAVEL_BEACH.id
		                        : Biome.BEACH.id;
                    }
                } else {
                    var6[var8 + var7 * width] = var9;
                }
            }
        }

        return var6;
    }
}
