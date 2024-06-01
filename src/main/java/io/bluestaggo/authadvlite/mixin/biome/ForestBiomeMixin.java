package io.bluestaggo.authadvlite.mixin.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ForestBiome;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BirchTreeFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ForestBiome.class)
public abstract class ForestBiomeMixin extends Biome {
	@Shadow @Final protected static BirchTreeFeature TALL_BIRCH_TREE;
	@Shadow @Final protected static BirchTreeFeature BIRCH_TREE;

	@Shadow private int variant;

	protected ForestBiomeMixin(int id) {
		super(id);
	}

	@Redirect(
		method = "getRandomTree",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/ForestBiome;BIRCH_TREE:Lnet/minecraft/world/gen/feature/BirchTreeFeature;"
		)
	)
	private BirchTreeFeature replaceBirch() {
		if (this.variant == 2) {
			return TALL_BIRCH_TREE;
		}
		return BIRCH_TREE;
	}

	@Inject(
		method = "getRandomTree",
		at = @At("RETURN"),
		cancellable = true
	)
	private void addLargeOaks(Random random, CallbackInfoReturnable<AbstractTreeFeature> cir) {
		if (cir.getReturnValue() == this.tree && random.nextInt(10) == 0) {
			cir.setReturnValue(this.largeTree);
		}
	}
}
