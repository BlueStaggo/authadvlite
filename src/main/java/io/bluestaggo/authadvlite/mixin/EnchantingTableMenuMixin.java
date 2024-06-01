package io.bluestaggo.authadvlite.mixin;

import net.minecraft.inventory.menu.EnchantingTableMenu;
import net.minecraft.inventory.menu.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnchantingTableMenu.class)
public abstract class EnchantingTableMenuMixin extends InventoryMenu {
	@ModifyArg(
		method = "onButtonClick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/living/player/PlayerEntity;addXp(I)V"
		),
		index = 0
	)
	private int reduceXPCosts(int levels) {
		return Math.min(Math.max(levels / 4, 5), levels);
	}
}
