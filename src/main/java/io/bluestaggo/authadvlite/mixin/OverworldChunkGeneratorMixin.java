package io.bluestaggo.authadvlite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.authadvlite.biome.AABiomes;
import io.bluestaggo.authadvlite.biome.BiomeCustomInvoker;
import io.bluestaggo.authadvlite.biome.SnowcappedHillsBiome;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.minecraft.world.gen.noise.OctaveNoiseGenerator;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(OverworldChunkGenerator.class)
public abstract class OverworldChunkGeneratorMixin implements ChunkSource {
	@Unique private static final OctaveNoiseGenerator SNOW_NOISE = new OctaveNoiseGenerator(new Random(1234L), 1);
	@Unique private static double[] snowNoiseArray;
	@Unique private static int lsnowX;
	@Unique private static int lsnowZ;

	@Shadow private Random random;
	@Unique private double[] noiseArray = new double[1];
	@Unique private double[] specialNoiseArray = new double[1];

	@Unique private int lnX;
	@Unique private int lnZ;
	@Unique private int lsnX;
	@Unique private int lsnZ;

	@Unique private OctaveNoiseGenerator biomeHeightNoise;
	@Unique private OctaveNoiseGenerator specialNoise;

	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void init(World world, long seed, boolean structures, String generatorOptions, CallbackInfo ci) {
		Random specialRandom = new Random(new Random(seed).nextLong());
		this.biomeHeightNoise = new OctaveNoiseGenerator(specialRandom, 4);
		this.specialNoise = new OctaveNoiseGenerator(specialRandom, 4);
	}

	@Redirect(
		method = "populateHeightMap",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;baseHeight:F",
			opcode = Opcodes.GETFIELD
		)
	)
	private float boostBaseHeight(Biome biome,
	                              @Local(ordinal = 0, argsOnly = true) int hmx,
	                              @Local(ordinal = 2, argsOnly = true) int hmz,
	                              @Local(ordinal = 8) int nx,
	                              @Local(ordinal = 9) int nz) {
		BiomeCustomInvoker biomeci = (BiomeCustomInvoker) biome;
		float baseHeightBoost = biomeci.authadvlite$getBaseHeightBoost();

		if (baseHeightBoost != 0.0F) {
			float boostValue = this.getBoostValue(hmx + nx, hmz + nz);
			return biome.baseHeight + baseHeightBoost * boostValue;
		}

		return biome.baseHeight;
	}

	@Redirect(
		method = "populateHeightMap",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;heightVariation:F",
			opcode = Opcodes.GETFIELD
		)
	)
	private float boostHeightVariation(Biome biome,
	                                   @Local(ordinal = 0, argsOnly = true) int hmx,
	                                   @Local(ordinal = 2, argsOnly = true) int hmz,
	                                   @Local(ordinal = 8) int nx,
	                                   @Local(ordinal = 9) int nz) {
		BiomeCustomInvoker biomeci = (BiomeCustomInvoker) biome;
		float heightVariationBoost = biomeci.authadvlite$getHeightVariationBoost();

		if (heightVariationBoost != 0.0F) {
			float boostValue = this.getBoostValue(hmx + nx, hmz + nz);
			return biome.heightVariation + heightVariationBoost * boostValue;
		}

		return biome.heightVariation;
	}

	@Unique
	private float getBoostValue(int x, int z) {
		if (this.lnX != x || this.lnZ != z || this.noiseArray == null) {
			if (this.noiseArray == null) {
				this.noiseArray = new double[1];
			}
			this.biomeHeightNoise.getNoise(this.noiseArray, x, z, 1, 1, 0.1, 0.1, 0.5);
			this.lnX = x;
			this.lnZ = z;
		}
		return (float)this.noiseArray[0] / 30.0F + 0.5F;
	}

	@Unique
	private double getSpecialValue(int x, int z) {
		if (this.lsnX != x || this.lsnZ != z || this.specialNoiseArray == null) {
			if (this.specialNoiseArray == null) {
				this.specialNoiseArray = new double[1];
			}
			this.specialNoise.getNoise(this.specialNoiseArray, x, z, 1, 1, 1.0 / 16.0, 1.0 / 16.0, 0.5);
			this.lsnX = x;
			this.lsnZ = z;
		}
		return this.specialNoiseArray[0];
	}

	@Unique
	private static float getSnowValue(int x, int z) {
		if (lsnowX != x || lsnowZ != z || snowNoiseArray == null) {
			if (snowNoiseArray == null) {
				snowNoiseArray = new double[1];
			}
			SNOW_NOISE.getNoise(snowNoiseArray, x, z, 1, 1, 1.0 / 8.0, 1.0 / 8.0, 0.5);
			lsnowX = x;
			lsnowZ = z;
		}
		return (float)snowNoiseArray[0];
	}
}
