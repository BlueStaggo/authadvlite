package io.bluestaggo.authadvlite.mixin.anvil;

import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.menu.AnvilMenu;
import net.minecraft.inventory.slot.InventorySlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.inventory.menu.AnvilMenu$2")
public abstract class AnvilMenu$2Mixin extends InventorySlot {
	public AnvilMenu$2Mixin(Inventory inventory, int slot, int x, int y) {
		super(inventory, slot, x, y);
	}

	@Redirect(
		method = "canPickUp",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/inventory/menu/AnvilMenu;repairCost:I",
			ordinal = 1
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
