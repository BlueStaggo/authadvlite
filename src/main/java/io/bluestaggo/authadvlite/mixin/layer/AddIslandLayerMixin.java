package io.bluestaggo.authadvlite.mixin.layer;

import net.minecraft.world.biome.layer.AddIslandLayer;
import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AddIslandLayer.class)
public abstract class AddIslandLayerMixin extends Layer {
	public AddIslandLayerMixin(long seed) {
		super(seed);
	}

	@ModifyConstant(
		method = "nextValues",
		constant = @Constant(intValue = 4)
	)
	private int preventSnowLand(int constant) {
		return -1;
	}
}
