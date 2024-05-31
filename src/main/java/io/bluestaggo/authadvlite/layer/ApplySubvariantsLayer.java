package io.bluestaggo.authadvlite.layer;

import io.bluestaggo.authadvlite.biome.AABiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;

public class ApplySubvariantsLayer extends Layer {
    public ApplySubvariantsLayer(long seed, Layer parent) {
        super(seed);
        this.parent = parent;
    }

    public int[] nextValues(int x, int z, int width, int length) {
        int[] var5 = this.parent.nextValues(x, z, width, length);
        int[] var6 = IntArrays.get(width * length);

        for(int var7 = 0; var7 < length; ++var7) {
            for(int var8 = 0; var8 < width; ++var8) {
                this.setChunkSeed((long)(var8 + x), (long)(var7 + z));
                int var9 = var5[var8 + var7 * width];

	            AABiomes.Subvariant subvariant = AABiomes.SUBVARIANTS[var9];
				if (subvariant != null && this.nextInt(subvariant.chance) == 0) {
					var9 = subvariant.biome.id;
				}
                var6[var8 + var7 * width] = var9;
            }
        }

        return var6;
    }
}
