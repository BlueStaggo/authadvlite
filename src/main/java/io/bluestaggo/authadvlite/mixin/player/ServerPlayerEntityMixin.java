package io.bluestaggo.authadvlite.mixin.player;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.inventory.menu.InventoryMenuListener;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements InventoryMenuListener {
	public ServerPlayerEntityMixin(World world, String name) {
		super(world, name);
	}


}
