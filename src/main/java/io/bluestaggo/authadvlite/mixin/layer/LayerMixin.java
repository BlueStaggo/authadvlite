package io.bluestaggo.authadvlite.mixin.layer;

import io.bluestaggo.authadvlite.layer.*;
import net.minecraft.world.biome.layer.*;
import net.minecraft.world.gen.WorldGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Function;

@Mixin(Layer.class)
public abstract class LayerMixin {
	/**
	 * @author BlueStaggo
	 * @reason Authentic Adventure changes a lot of things with biome distribution
	 */
	@Overwrite
	public static Layer[] init(long seed, WorldGeneratorType generatorType) {
        Layer layer = new RareIslandLayer(1L);
		layer = new AddSmoothIslandLayer(1L, layer);
		layer = new AddSmoothIslandLayer(2L, layer);
		layer = new MoreIslandsLayer(1000L, layer);
		layer = new AddIslandLayer(3L, layer);
		layer = new AddIslandLayer(4L, layer);
		layer = new AddSmoothIslandLayer(5L, layer);
		layer = new RiverLayer(2000L, layer);
		layer = new MoreIslandsLayer(1001L, layer);
		layer = new AddSmoothIslandLayer(7L, layer);
		layer = new ClimateLayer(1001L, layer);
		layer = new ZoomLayer(2001L, layer);
		layer = new AddSmoothIslandLayer(8L, layer);
		layer = new AddIslandLayer(9L, layer);
		layer = new TemperateBorderLayer(1002L, layer);
        AddMushroomIslandLayer var15 = new AddMushroomIslandLayer(1002, layer);
        byte var4 = (byte)(generatorType == WorldGeneratorType.LARGE_BIOMES ? 6 : 4);

        Layer var5 = ZoomLayer.zoom(1000L, var15, 0);
        RiverInitLayer var13 = new RiverInitLayer(100L, var5);
        var5 = ZoomLayer.zoom(1000L, var13, var4 + 1);
        AddSunflowerPlainsLayer var14 = new AddSunflowerPlainsLayer(1L, var5);
		AddSnowLayer riverLayer = new AddSnowLayer(1000L, var14);

        Layer var6 = ZoomLayer.zoom(1000L, var15, 0);
        BiomeInitLayer var17 = new BiomeInitLayer(200L, var6, generatorType);
        var6 = ZoomLayer.zoom(1000L, var17, 2);
        final Layer var18 = new ApplySubvariantsLayer(1000L, var6);

		Function<Boolean, Layer> biomeZoomer = (addRivers) -> {
			Layer l = var18;
	        for(int i = 0; i < var4; ++i) {
				if (i == var4 - 1 && addRivers) {
					l = new RiverMixLayer(100L, l, riverLayer);
				}

	            l = new ZoomLayer((long)(1000 + i), l);

	            if (i == 0) {
	                l = new SmoothLayer(1000L, l);
	            }
	        }

			l = new AddSnowLayer(1000L, l);
			return l;
		};

        Layer var20 = biomeZoomer.apply(true);
        VoronoiZoomLayer var8 = new VoronoiZoomLayer(10L, biomeZoomer.apply(false));
        var20.setLocalWorldSeed(seed);
        var8.setLocalWorldSeed(seed);
        return new Layer[]{var20, var8, var20};
    }
}
