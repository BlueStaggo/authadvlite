package io.bluestaggo.authadvlite.layer;

import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.Layer;

public class TemperateBorderLayer extends Layer {
	public TemperateBorderLayer(long var1, Layer var3) {
		super(var1);
		super.parent = var3;
	}

	public int[] nextValues(int var1, int var2, int var3, int var4) {
		int var5 = var1 - 1;
		int var6 = var2 - 1;
		int var7 = var3 + 2;
		int var8 = var4 + 2;
		int[] var9 = this.parent.nextValues(var5, var6, var7, var8);
		int[] var10 = IntArrays.get(var3 * var4);

		for(int var11 = 0; var11 < var4; ++var11) {
			for(int var12 = 0; var12 < var3; ++var12) {
				int i = var9[var12 + 1 + (var11 + 1) * var7];

				ClimateZone c = ClimateZone.getZoneFromId(i);
				ClimateZone[] n = {
						ClimateZone.getZoneFromId(var9[var12 + 0 + (var11 + 1) * var7]),
						ClimateZone.getZoneFromId(var9[var12 + 2 + (var11 + 1) * var7]),
						ClimateZone.getZoneFromId(var9[var12 + 1 + (var11 + 0) * var7]),
						ClimateZone.getZoneFromId(var9[var12 + 1 + (var11 + 2) * var7])
				};
				if (c != null) {
					if (c == ClimateZone.WARM && arrContains(n, ClimateZone.SNOWY)) {
						i = ClimateZone.COOL.id();
					} else if (c == ClimateZone.COOL && arrContains(n, ClimateZone.HOT)) {
						i = ClimateZone.WARM.id();
					} else if (c == ClimateZone.SNOWY && arrContains(n, ClimateZone.HOT)
							|| c == ClimateZone.HOT && arrContains(n, ClimateZone.SNOWY)) {
						i = ClimateZone.TEMPERATE.id();
					}
				}

				var10[var12 + var11 * var3] = i;
			}
		}

		return var10;
	}

	private static boolean arrContains(ClimateZone[] n, ClimateZone z) {
		for (ClimateZone nz : n) {
			if (nz == z) {
				return true;
			}
		}
		return false;
	}
}
