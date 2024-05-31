package io.bluestaggo.authadvlite.mixin.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.AddSunflowerPlainsLayer;
import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AddSunflowerPlainsLayer.class)
public abstract class AddSunflowerPlainsLayerMixin extends Layer {
	public AddSunflowerPlainsLayerMixin(long seed) {
		super(seed);
	}

	/**
	 * @author BlueStaggo
	 * @reason Big changes
	 */
	@Overwrite
	public int[] nextValues(int x, int z, int width, int length) {
        int var5 = x - 1;
        int var6 = z - 1;
        int var7 = width + 2;
        int var8 = length + 2;
        int[] var9 = this.parent.nextValues(var5, var6, var7, var8);
        int[] var10 = IntArrays.get(width * length);

        for(int var11 = 0; var11 < length; ++var11) {
            for(int var12 = 0; var12 < width; ++var12) {
                int var13 = var9[var12 + 0 + (var11 + 1) * var7];
                int var14 = var9[var12 + 2 + (var11 + 1) * var7];
                int var15 = var9[var12 + 1 + (var11 + 0) * var7];
                int var16 = var9[var12 + 1 + (var11 + 2) * var7];
                int var17 = var9[var12 + 1 + (var11 + 1) * var7];
                if(var17 != 0 && var13 != 0 && var14 != 0 && var15 != 0 && var16 != 0) {
					if(var17 == var13 && var17 == var15 && var17 == var14 && var17 == var16) {
						var10[var12 + var11 * width] = -1;
					} else {
						var10[var12 + var11 * width] = Biome.RIVER.id;
					}
				} else if (var17 == 0) {
					var10[var12 + var11 * width] = 0;
				} else {
					var10[var12 + var11 * width] = Biome.RIVER.id;
				}
            }
        }

        return var10;
    }
}
