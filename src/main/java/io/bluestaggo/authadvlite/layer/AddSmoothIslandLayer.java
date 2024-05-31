package io.bluestaggo.authadvlite.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;

public class AddSmoothIslandLayer extends Layer {
    public AddSmoothIslandLayer(long seed, Layer parent) {
        super(seed);
        this.parent = parent;
    }

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
                this.setChunkSeed((long)(var12 + x), (long)(var11 + z));
                if (var17 == 0 && (var13 != 0 || var14 != 0 || var15 != 0 || var16 != 0)) {
                    int var18 = 1;
                    int var19 = 1;
                    if (var13 != 0 && this.nextInt(var18++) == 0) {
                        var19 = var13;
                    }

                    if (var14 != 0 && this.nextInt(var18++) == 0) {
                        var19 = var14;
                    }

                    if (var15 != 0 && this.nextInt(var18++) == 0) {
                        var19 = var15;
                    }

                    if (var16 != 0 && this.nextInt(var18++) == 0) {
                        var19 = var16;
                    }

                    if (this.nextInt(3) == 0) {
                        var10[var12 + var11 * width] = var19;
                    } else if (var19 == Biome.ICE_PLAINS.id) {
                        var10[var12 + var11 * width] = Biome.FROZEN_OCEAN.id;
                    } else {
                        var10[var12 + var11 * width] = 0;
                    }
                } else {
                    var10[var12 + var11 * width] = var17;
                }
            }
        }

        return var10;
    }
}
