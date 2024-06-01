package io.bluestaggo.authadvlite.mixin.biome;

import io.bluestaggo.authadvlite.mixin.FeatureDecoratorAccessor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IceBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBiome.class)
public abstract class IceBiomeMixin extends Biome {
	protected IceBiomeMixin(int id) {
		super(id);
	}

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void removeTallGrass(int id, CallbackInfo ci) {
		FeatureDecoratorAccessor decorator = (FeatureDecoratorAccessor) this.decorator;
		decorator.setGrassAttempts(0);
		decorator.setFlowerAttempts(0);
		decorator.setMushroomAttempts(0);
	}
}
