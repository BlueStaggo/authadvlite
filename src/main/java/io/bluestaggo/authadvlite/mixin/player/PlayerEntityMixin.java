package io.bluestaggo.authadvlite.mixin.player;

import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.server.command.source.CommandSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements CommandSource {
	@Shadow public PlayerAbilities abilities;
	@Shadow protected HungerManager hungerManager;

	@Shadow
	public abstract boolean needsHealing();

	public PlayerEntityMixin(World world) {
		super(world);
	}

	@Unique
	private void addCustomFatigue(float amount) {
		if (this.abilities.invulnerable || this.world.isMultiplayer) return;

		if (this.world.difficulty < 2) {
			amount /= 2.0F;
		} else if (this.world.difficulty > 2) {
			amount *= 2.0F;
		}

		this.hungerManager.addExhaustion(amount);
	}

	@Inject(
		method = "addFatigue",
		at = @At("HEAD"),
		cancellable = true
	)
	private void addNoFatigue(float amount, CallbackInfo ci) {
		ci.cancel();
	}

	@Redirect(
		method = "tickNonRidingMovementRelatedStats",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/living/player/PlayerEntity;addFatigue(F)V",
			ordinal = 2
		)
	)
	private void exhaustionOnSprinting(PlayerEntity player, float amount) {
		this.addCustomFatigue(amount * 2.0F);
	}

	@Redirect(
		method = "jump",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/living/player/PlayerEntity;addFatigue(F)V",
			ordinal = 0
		)
	)
	private void exhaustionOnSprintJumping(PlayerEntity player, float amount) {
		this.addCustomFatigue(1.0F);
	}

	@ModifyVariable(
		method = "canEat",
		at = @At("HEAD"),
		ordinal = 0,
		argsOnly = true
	)
	private boolean eatToHeal(boolean value) {
		return value || this.needsHealing();
	}
}
