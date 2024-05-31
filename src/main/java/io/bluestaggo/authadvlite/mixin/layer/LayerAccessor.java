package io.bluestaggo.authadvlite.mixin.layer;

import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Layer.class)
public interface LayerAccessor {
	@Accessor long getLocalWorldSeed();
	@Accessor Layer getParent();
}
