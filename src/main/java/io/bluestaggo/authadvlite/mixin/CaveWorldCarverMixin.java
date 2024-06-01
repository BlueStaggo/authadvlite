package io.bluestaggo.authadvlite.mixin;

import net.minecraft.world.gen.Generator;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CaveWorldCarver.class)
public abstract class CaveWorldCarverMixin extends Generator {
	@ModifyConstant(
		method = "place(Lnet/minecraft/world/World;IIII[Lnet/minecraft/block/Block;)V",
		constant = @Constant(intValue = 15)
	)
	private int increaseCaveDensity(int constant) {
		return 40;
	}

	@ModifyConstant(
		method = "place(Lnet/minecraft/world/World;IIII[Lnet/minecraft/block/Block;)V",
		constant = @Constant(intValue = 7)
	)
	private int increaseCaveSpread(int constant) {
		return 15;
	}
}
