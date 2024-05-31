package io.bluestaggo.authadvlite.mixin.layer;

import io.bluestaggo.authadvlite.biome.AABiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;
import net.minecraft.world.biome.layer.RiverMixLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RiverMixLayer.class)
public abstract class RiverMixLayerMixin extends Layer {
	@Shadow private Layer biomeLayer;
	@Shadow private Layer riverLayer;

	public RiverMixLayerMixin(long seed) {
		super(seed);
	}

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void init(long seed, Layer biomeLayer, Layer riverLayer, CallbackInfo ci) {
		this.parent = biomeLayer;
	}

	/**
	 * @author BlueStaggo
	 * @reason Big changes
	 */
	@Overwrite
	public int[] nextValues(int x, int z, int width, int length) {
		int[] var5 = this.biomeLayer.nextValues(x, z, width, length);
        int[] var6 = this.riverLayer.nextValues(x, z, width, length);
        int[] var7 = IntArrays.get(width * length);

        for(int var8 = 0; var8 < width * length; ++var8) {
            if (AABiomes.IS_OCEAN[var5[var8]]) {
                var7[var8] = var5[var8];
            } else if (var6[var8] >= 0) {
                if (var5[var8] == Biome.MUSHROOM_ISLAND.id || var5[var8] == Biome.MUSHROOM_ISLAND_SHORE.id) {
                    var7[var8] = Biome.MUSHROOM_ISLAND_SHORE.id;
                } else if (Biome.BY_ID[var5[var8]].getTemperature() < 0.15F) {
                    var7[var8] = Biome.FROZEN_RIVER.id;
                } else {
                    var7[var8] = var6[var8];
                }
            } else {
                var7[var8] = var5[var8];
            }
        }

        return var7;
	}
}
