package io.bluestaggo.authadvlite.mixin;

import net.minecraft.world.gen.feature.decorator.FeatureDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FeatureDecorator.class)
public interface FeatureDecoratorAccessor {
	@Accessor void setLilyPadAttempts(int value);
	@Accessor void setTreeAttempts(int value);
	@Accessor void setFlowerAttempts(int value);
    @Accessor void setGrassAttempts(int value);
    @Accessor void setDeadBushAttempts(int value);
    @Accessor void setMushroomAttempts(int value);
    @Accessor void setSugarcaneAttempts(int value);
    @Accessor void setCactusAttempts(int value);
    @Accessor void setGravelPatchAttempts(int value);
    @Accessor void setSandPatchAttempts(int value);
    @Accessor void setClayPatchAttempts(int value);
    @Accessor void setHugeMushroomAttempts(int value);
}
