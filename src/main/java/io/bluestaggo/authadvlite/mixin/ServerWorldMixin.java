package io.bluestaggo.authadvlite.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ILogger;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.storage.WorldStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World {
	public ServerWorldMixin(WorldStorage storage, String name, Dimension dimension, WorldSettings settings, Profiler profiler, ILogger logger) {
		super(storage, name, dimension, settings, profiler, logger);
	}

	@ModifyConstant(
		method = "tick",
		constant = @Constant(longValue = 400L)
	)
	private long spawnAnimalsEveryTick(long constant) {
		return 1L;
	}
}
