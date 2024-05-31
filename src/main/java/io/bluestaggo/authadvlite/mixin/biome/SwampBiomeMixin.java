package io.bluestaggo.authadvlite.mixin.biome;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SwampBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwampBiome.class)
public abstract class SwampBiomeMixin extends Biome {
	protected SwampBiomeMixin(int id) {
		super(id);
	}

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void init(int par1, CallbackInfo ci) {
		this.waterColor = 0xFFFFFF;
	}

	/**
	 * @author BlueStaggo
	 * @reason De-mank the swamps
	 */
	@Overwrite
	@Environment(EnvType.CLIENT)
	public int getGrassColor() {
		return super.getGrassColor();
	}

	/**
	 * @author BlueStaggo
	 * @reason De-mank the swamps
	 */
	@Overwrite
	@Environment(EnvType.CLIENT)
	public int getFoliageColor() {
		return super.getFoliageColor();
	}
}
