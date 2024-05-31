package io.bluestaggo.authadvlite.mixin.biome;

import io.bluestaggo.authadvlite.biome.BiomeCustomInvoker;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DataFlowIssue")
@Mixin(Biome.class)
public abstract class BiomeMixin implements BiomeCustomInvoker {
	@Unique public float baseHeightBoost;
    @Unique public float heightVariationBoost;

	@Shadow protected abstract Biome setTemperatureAndDownfall(float temperature, float downfall);
	@Shadow protected abstract Biome setHeight(float baseHeight, float heightVariation);

	@Shadow private boolean snowy;

	@Unique
	@Override
	public Biome authadvlite$setHeightVariation(float baseHeightBoost, float heightVariationBoost) {
		this.baseHeightBoost = baseHeightBoost;
		this.heightVariationBoost = heightVariationBoost;
		return (Biome) (Object) this;
	}

	@Unique
	@Override
	public float authadvlite$getBaseHeightBoost() {
		return this.baseHeightBoost;
	}

	@Unique
	@Override
	public float authadvlite$getHeightVariationBoost() {
		return this.heightVariationBoost;
	}

	@Inject(
		method = "<clinit>",
		at = @At("TAIL")
	)
	private static void clinit(CallbackInfo ci) {
		((BiomeMixin) (Object) Biome.PLAINS).setTemperatureAndDownfall(0.8F, 0.5F);

		((BiomeMixin) (Object) Biome.DESERT).setHeight(0.2F, 0.2F);
		((BiomeMixin) (Object) Biome.DESERT).authadvlite$setHeightVariation(0.2F, 0.2F);

		((BiomeMixin) (Object) Biome.EXTREME_HILLS).setHeight(0.3F, 1.8F);

		((BiomeMixin) (Object) Biome.FOREST).setTemperatureAndDownfall(0.8F, 0.8F);
		((BiomeMixin) (Object) Biome.FOREST).setHeight(0.1F, 0.6F);
		((BiomeMixin) (Object) Biome.FOREST).authadvlite$setHeightVariation(0.2F, 0.6F);

		((BiomeMixin) (Object) Biome.TAIGA).setTemperatureAndDownfall(0.3F, 0.8F);
		((BiomeMixin) (Object) Biome.TAIGA).setHeight(0.1F, 0.6F);
		((BiomeMixin) (Object) Biome.TAIGA).authadvlite$setHeightVariation(0.2F, 0.6F);
		((BiomeMixin) (Object) Biome.TAIGA).snowy = false;

		((BiomeMixin) (Object) Biome.SWAMPLAND).setTemperatureAndDownfall(0.8F, 1.0F);
		((BiomeMixin) (Object) Biome.SWAMPLAND).authadvlite$setHeightVariation(0.0F, 0.4F);

		((BiomeMixin) (Object) Biome.ICE_PLAINS).setHeight(0.0F, 0.3F);
		((BiomeMixin) (Object) Biome.ICE_PLAINS).authadvlite$setHeightVariation(0.2F, 0.7F);

		((BiomeMixin) (Object) Biome.ICE_MOUNTAINS).setHeight(0.0F, 1.8F);

		((BiomeMixin) (Object) Biome.BEACH).setTemperatureAndDownfall(0.8F, 0.5F);

		((BiomeMixin) (Object) Biome.JUNGLE).setHeight(0.0F, 0.5F);
		((BiomeMixin) (Object) Biome.JUNGLE).authadvlite$setHeightVariation(0.2F, 1.0F);
	}
}
