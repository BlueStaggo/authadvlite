package io.bluestaggo.authadvlite.mixin.player;

import net.minecraft.client.entity.living.player.InputClientPlayerEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InputClientPlayerEntity.class)
public abstract class InputClientPlayerEntityMixin extends PlayerEntity {
	public InputClientPlayerEntityMixin(World world, String name) {
		super(world, name);
	}

	@ModifyConstant(
		method = "tickAi",
		constant = @Constant(floatValue = 6.0F)
	)
	private float sprintUntilNoHunger(float constant) {
		return 0.0F;
	}
}
