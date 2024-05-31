package io.bluestaggo.authadvlite.mixin;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LargeOakTreeFeature;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LargeOakTreeFeature.class)
public abstract class LargeOakTreeFeatureMixin extends Feature {
	@Shadow int foliageClusterHeight;

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void init(boolean par1, CallbackInfo ci) {
		this.foliageClusterHeight = 5;
	}

	@Redirect(
		method = "place",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/gen/feature/LargeOakTreeFeature;height:I",
			opcode = Opcodes.GETFIELD,
			ordinal = 0
		)
	)
	private int alwaysRandomiseHeight(LargeOakTreeFeature instance) {
		return 0;
	}

	@ModifyArg(
		method = "placeBranches",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/feature/LargeOakTreeFeature;placeBranch([I[II)V"
		),
		index = 1
	)
	private int[] placeBranches_higherBranches(int[] pos) {
		pos[1] += (this.foliageClusterHeight - 1) / 2;
		return pos;
	}
}
