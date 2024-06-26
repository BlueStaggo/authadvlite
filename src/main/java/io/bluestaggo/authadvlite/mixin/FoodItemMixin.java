package io.bluestaggo.authadvlite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FoodItem.class)
public abstract class FoodItemMixin extends Item {
	protected FoodItemMixin(int id) {
		super(id);
	}

	@Redirect(
		method = "finishUsing",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/player/HungerManager;add(Lnet/minecraft/item/FoodItem;)V"
		)
	)
	private void foodAddHealth(HungerManager hungerManager, FoodItem foodItem, @Local(argsOnly = true) PlayerEntity player) {
		int pointsToAdd = foodItem.getHungerPoints();
		int missingHealth = player.getMaxHealth() - player.getHealth();
		int addedHealth = Math.min(pointsToAdd, missingHealth);
		pointsToAdd -= addedHealth;

		player.heal(addedHealth);
		hungerManager.add(pointsToAdd, 0);
	}

	@ModifyConstant(
		method = "getUseDuration",
		constant = @Constant(ordinal = 0)
	)
	private int longerEating(int constant) {
		return constant * 3 / 2;
	}
}
