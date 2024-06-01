package io.bluestaggo.authadvlite.mixin.player;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {
	@Shadow private int foodLevel;
	@Shadow private int lastFoodLevel;
	@Shadow private float exhaustion;

	/**
	 * @author BlueStaggo
	 * @reason New hunger system
	 */
	@Overwrite
	public void tick(PlayerEntity player) {
		this.lastFoodLevel = this.foodLevel;
		if(this.exhaustion > 4.0F) {
			this.exhaustion -= 4.0F;
			if(player.world.difficulty != Difficulty.PEACEFUL) {
				this.foodLevel = Math.max(this.foodLevel - 1, 0);
			}
		}
	}
}
