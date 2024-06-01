package io.bluestaggo.authadvlite.mixin.biome;

import io.bluestaggo.authadvlite.biome.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ExtremeHillsBiome;
import net.minecraft.world.biome.SavannaBiome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@SuppressWarnings("DataFlowIssue")
@Mixin(Biome.class)
public abstract class BiomeMixin implements BiomeCustomInvoker {
	@Unique public float baseHeightBoost;
    @Unique public float heightVariationBoost;

	@Shadow protected abstract Biome setTemperatureAndDownfall(float temperature, float downfall);
	@Shadow protected abstract Biome setHeight(Biome.Height height);

	@Shadow protected boolean snowy;

	@SuppressWarnings("rawtypes") @Shadow @Final public static Set EXPLORABLE;

	@Unique
	@Override
	public Biome authadvlite$setHeightVariation(Biome.Height height) {
		this.baseHeightBoost = height.baseHeight;
		this.heightVariationBoost = height.heightModifier;
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

	@SuppressWarnings("unchecked")
	@Inject(
		method = "<clinit>",
		at = @At("TAIL")
	)
	private static void clinit(CallbackInfo ci) {
		((BiomeMixin) (Object) Biome.OCEAN).setHeight(new Biome.Height(-1.0F, 0.2F));

		((BiomeMixin) (Object) Biome.PLAINS).setTemperatureAndDownfall(0.8F, 0.5F);
		((BiomeMixin) (Object) Biome.PLAINS).setHeight(new Biome.Height(0.1F, 0.15F));

		((BiomeMixin) (Object) Biome.DESERT).setHeight(new Biome.Height(0.2F, 0.1F));
		((BiomeMixin) (Object) Biome.DESERT).authadvlite$setHeightVariation(new Biome.Height(0.0F, 0.4F));

		((BiomeMixin) (Object) Biome.EXTREME_HILLS).setHeight(new Biome.Height(0.3F, 0.9F));

		((BiomeMixin) (Object) Biome.FOREST).setTemperatureAndDownfall(0.8F, 0.8F);
		((BiomeMixin) (Object) Biome.FOREST).setHeight(AABiomes.FOREST_HEIGHT);
		((BiomeMixin) (Object) Biome.FOREST).authadvlite$setHeightVariation(AABiomes.FOREST_HEIGHT_BOOST);

		((BiomeMixin) (Object) Biome.TAIGA).setTemperatureAndDownfall(0.3F, 0.8F);
		((BiomeMixin) (Object) Biome.TAIGA).setHeight(AABiomes.FOREST_HEIGHT);
		((BiomeMixin) (Object) Biome.TAIGA).authadvlite$setHeightVariation(AABiomes.FOREST_HEIGHT_BOOST);
		((BiomeMixin) (Object) Biome.TAIGA).snowy = false;

		((BiomeMixin) (Object) Biome.SWAMPLAND).setTemperatureAndDownfall(0.8F, 1.0F);
		((BiomeMixin) (Object) Biome.SWAMPLAND).setHeight(new Biome.Height(-0.2F, 0.1F));
		((BiomeMixin) (Object) Biome.SWAMPLAND).authadvlite$setHeightVariation(new Biome.Height(0.0F, 0.2F));

		((BiomeMixin) (Object) Biome.FROZEN_OCEAN).setHeight(new Biome.Height(-1.0F, 0.2F));

		((BiomeMixin) (Object) Biome.ICE_PLAINS).setHeight(new Biome.Height(0.0F, 0.15F));
		((BiomeMixin) (Object) Biome.ICE_PLAINS).authadvlite$setHeightVariation(new Biome.Height(0.2F, 0.35F));

		((BiomeMixin) (Object) Biome.ICE_MOUNTAINS).setHeight(new Biome.Height(0.0F, 0.9F));

		((BiomeMixin) (Object) Biome.MUSHROOM_ISLAND).setHeight(new Biome.Height(0.2F, 0.5F));

		((BiomeMixin) (Object) Biome.MUSHROOM_ISLAND_SHORE).setHeight(new Biome.Height(-1.0F, 0.05F));

		((BiomeMixin) (Object) Biome.BEACH).setTemperatureAndDownfall(0.8F, 0.5F);
		((BiomeMixin) (Object) Biome.BEACH).setHeight(new Biome.Height(0.0F, 0.05F));

		((BiomeMixin) (Object) Biome.JUNGLE).setHeight(new Biome.Height(0.0F, 0.25F));
		((BiomeMixin) (Object) Biome.JUNGLE).authadvlite$setHeightVariation(new Biome.Height(0.2F, 0.5F));

		((BiomeMixin) (Object) Biome.DEEP_OCEAN).setHeight(new Biome.Height(-1.8F, 0.4F));

		EXPLORABLE.clear();
		EXPLORABLE.add(Biome.OCEAN);
		EXPLORABLE.add(Biome.PLAINS);
		EXPLORABLE.add(Biome.DESERT);
		EXPLORABLE.add(Biome.EXTREME_HILLS);
		EXPLORABLE.add(Biome.FOREST);
		EXPLORABLE.add(Biome.TAIGA);
		EXPLORABLE.add(Biome.SWAMPLAND);
		EXPLORABLE.add(Biome.ICE_PLAINS);
		EXPLORABLE.add(Biome.ICE_MOUNTAINS);
		EXPLORABLE.add(Biome.MUSHROOM_ISLAND);
		EXPLORABLE.add(Biome.BEACH);
		EXPLORABLE.add(Biome.JUNGLE);
		EXPLORABLE.add(Biome.DEEP_OCEAN);
		EXPLORABLE.add(AABiomes.COLD_TAIGA);
		EXPLORABLE.add(AABiomes.PARCHED_FOREST);
		EXPLORABLE.add(AABiomes.HIGHLANDS);
		EXPLORABLE.add(AABiomes.SEA_CRAGS);
		EXPLORABLE.add(AABiomes.ARCHIPELAGO);
		EXPLORABLE.add(AABiomes.RAINFOREST);
		EXPLORABLE.add(AABiomes.COLD_BEACH);
		EXPLORABLE.add(AABiomes.FROSTY_HIGHLANDS);
		EXPLORABLE.add(AABiomes.GRAVEL_BEACH);
		EXPLORABLE.add(AABiomes.WINDSWEPT_CRAGS);
		EXPLORABLE.add(AABiomes.SAVANNA);
		EXPLORABLE.add(AABiomes.WOODED_HILLS);
		EXPLORABLE.add(AABiomes.SNOWCAPPED_HILLS);
		EXPLORABLE.add(AABiomes.BIRCH_FOREST);
		EXPLORABLE.add(AABiomes.MUSHROOM_VALLEY);
		EXPLORABLE.add(AABiomes.MEGA_TAIGA);
		EXPLORABLE.add(AABiomes.FROSTED_BIRCH_FOREST);
		EXPLORABLE.add(AABiomes.SNOWCAPPED_FOREST);
	}

	@Redirect(
		method = "<clinit>",
		at = @At(
			value = "NEW",
			target = "(IZ)Lnet/minecraft/world/biome/ExtremeHillsBiome;"
		)
	)
	private static ExtremeHillsBiome overrideExtremeHills(int id, boolean moreTrees) {
		return new LegacyExtremeHillsBiome(id, moreTrees);
	}

	@Redirect(
		method = "<clinit>",
		at = @At(
			value = "NEW",
			target = "(I)Lnet/minecraft/world/biome/SavannaBiome;"
		)
	)
	private static SavannaBiome overrideSavanna(int id) {
		return new LegacySavannaBiome(id);
	}

	@SuppressWarnings("ConstantValue")
	@ModifyVariable(
		method = "getTemperature",
		at = @At("HEAD"),
		ordinal = 1,
		argsOnly = true
	)
	private int restrictTemperature(int y) {
		if (y > 64 && ((Object)this instanceof LegacyExtremeHillsBiome) || ((Object)this instanceof WindsweptCragsBiome)) {
			y = Math.max(y - 64, 64);
		}
		return y;
	}

	@ModifyConstant(
		method = "populate",
		constant = @Constant(intValue = 56)
	)
	private int removeOceanGravel(int constant) {
		return -100;
	}
}
