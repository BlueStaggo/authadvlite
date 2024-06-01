package io.bluestaggo.authadvlite.mixin.anvil;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.inventory.menu.AnvilMenu;
import net.minecraft.inventory.menu.InventoryMenu;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends InventoryMenu {
	@Shadow private String itemName;

	@Shadow public int repairCost;

	@Redirect(
		method = "updateResult",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/inventory/menu/AnvilMenu;itemName:Ljava/lang/String;",
			ordinal = 0
		)
	)
	private String freeRename1(AnvilMenu instance, @Local(ordinal = 0) ItemStack var1, @Local(ordinal = 1) ItemStack var5) {
		if (this.itemName != null && !this.itemName.equalsIgnoreCase(var1.getHoverName()) && !this.itemName.isEmpty()) {
            var5.setName(this.itemName);
        }
		return null;
	}

	@ModifyConstant(
		method = "updateResult",
		constant = @Constant(intValue = 40)
	)
	private int removeCostLimit(int constant) {
		return Integer.MAX_VALUE;
	}

	@Redirect(
		method = "updateResult",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/ItemStack;hasCustomHoverName()Z"
		)
	)
	private boolean freeRename2(ItemStack instance) {
		return false;
	}

	@Inject(
		method = "updateResult",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/inventory/menu/AnvilMenu;repairCost:I",
			opcode = Opcodes.PUTFIELD,
			ordinal = 4,
			shift = At.Shift.AFTER
		)
	)
	private void freeRename3(CallbackInfo ci,
	                         @Local(ordinal = 0) LocalIntRef var2,
	                         @Local(ordinal = 1) LocalIntRef var3,
	                         @Local(ordinal = 0) ItemStack var1) {
		if (var2.get() <= 0 && (this.itemName != null && !this.itemName.equalsIgnoreCase(var1.getHoverName()) && this.itemName.length() > 0)) {
			var2.set(1);
			var3.set(-1);
			this.repairCost = 0;
		}
	}

	@ModifyArg(
		method = "updateResult",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/ItemStack;setRepairCost(I)V"
		),
		index = 0
	)
	private int freeRename4(int cost) {
		if (this.repairCost <= 0) {
			cost -= 2;
		}
		return cost;
	}
}
