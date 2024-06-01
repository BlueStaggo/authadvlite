package io.bluestaggo.authadvlite.mixin.anvil;

import net.minecraft.client.gui.screen.inventory.menu.AnvilScreen;
import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.inventory.menu.AnvilMenu;
import net.minecraft.inventory.menu.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends InventoryMenuScreen {
	public AnvilScreenMixin(InventoryMenu menu) {
		super(menu);
	}

	@Redirect(
		method = "drawForeground",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/inventory/menu/AnvilMenu;repairCost:I",
			ordinal = 0
		)
	)
	private int freeRename(AnvilMenu instance) {
		int repairCost = instance.repairCost;
		if (repairCost <= 0
				&& instance.getSlot(2).getStack() != null
				&& instance.getSlot(2).getStack().hasCustomHoverName()) {
			repairCost = 1;
		}
		return repairCost;
	}
}
