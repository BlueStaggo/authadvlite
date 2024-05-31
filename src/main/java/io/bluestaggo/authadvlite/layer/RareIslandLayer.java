package io.bluestaggo.authadvlite.layer;

import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;

public class RareIslandLayer extends Layer {
    public RareIslandLayer(long l) {
        super(l);
    }

    public int[] nextValues(int x, int z, int width, int length) {
        int[] var5 = IntArrays.get(width * length);

        for(int var6 = 0; var6 < length; ++var6) {
            for(int var7 = 0; var7 < width; ++var7) {
                this.setChunkSeed((long)(x + var7), (long)(z + var6));
                var5[var7 + var6 * width] = this.nextInt(100) == 0 ? 1 : 0;
            }
        }

        if (x > -width && x <= 0 && z > -length && z <= 0) {
            var5[-x + -z * width] = 1;
        }

        return var5;
    }
}
