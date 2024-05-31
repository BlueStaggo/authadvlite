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
	private void init(World world, long seed, boolean structures, CallbackInfo ci) {
		Random specialRandom = new Random(new Random(seed).nextLong());
		this.biomeHeightNoise = new OctaveNoiseGenerator(specialRandom, 4);
		this.specialNoise = new OctaveNoiseGenerator(specialRandom, 4);
	}

	@Redirect(
		method = "populateHeightmap",
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
		method = "populateHeightmap",
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

	@Redirect(
		method = "generateBiomes",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;surfaceBlockId:B",
			opcode = Opcodes.GETFIELD
		)
	)
	private byte customSurfaceBlock(Biome biome,
	                                @Local(ordinal = 0, argsOnly = true) int chunkX,
	                                @Local(ordinal = 1, argsOnly = true) int chunkZ,
	                                @Local(ordinal = 4) int x,
	                                @Local(ordinal = 3) int z) {
		int wx = chunkX * 16 + x;
		int wz = chunkZ * 16 + z;

		if (biome instanceof SnowcappedHillsBiome) {
			if (this.getBoostValue((wx + random.nextInt(4)) / -8, (wz + random.nextInt(4)) / -8) < -0.35) {
				double specialValue = this.getSpecialValue(wx, wz);
				if (specialValue < -1.0 || specialValue > 2.0) {
					return (byte) Block.GRASS.id;
				}
			}

			if (biome != AABiomes.SNOWCAPPED_FOREST) {
				if (this.getSpecialValue(wx, wz) > 1.0) {
					return (byte) Block.STONE.id;
				}
			}
		}

		if (biome == AABiomes.MUSHROOM_VALLEY) {
			if (Math.abs(this.getSpecialValue(wx, wz)) > 3.0) {
				return (byte) Block.MYCELIUM.id;
			}
		}

		return biome.surfaceBlockId;
	}

	@Redirect(
		method = "generateBiomes",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;subsurfaceBlockId:B",
			opcode = Opcodes.GETFIELD
		)
	)
	private byte customSubsurfaceBlock(Biome biome,
	                                   @Local(ordinal = 0, argsOnly = true) int chunkX,
	                                   @Local(ordinal = 1, argsOnly = true) int chunkZ,
	                                   @Local(ordinal = 4) int x,
	                                   @Local(ordinal = 3) int z) {
		int wx = chunkX * 16 + x;
		int wz = chunkZ * 16 + z;

		if (biome instanceof SnowcappedHillsBiome) {
			if (this.getBoostValue((wx + random.nextInt(4)) / -8, (wz + random.nextInt(4)) / -8) < -0.35) {
				double specialValue = this.getSpecialValue(wx, wz);
				if (specialValue < -1.0 || specialValue > 2.0) {
					return (byte) Block.GRASS.id;
				}
			}

			if (biome != AABiomes.SNOWCAPPED_FOREST) {
				if (this.getSpecialValue(wx, wz) > 1.0) {
					return (byte) Block.STONE.id;
				}
			}
		}

		return biome.subsurfaceBlockId;
	}

	@Redirect(
		method = "populateChunk",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;canSnowFall(III)Z"
		)
	)
	private boolean customSnowConditions(World world, int x, int y, int z) {
		Biome biome = world.getBiome(x, z);
		if (biome instanceof SnowcappedHillsBiome) {
			biome.temperature = 0.21F - (getSnowValue(x, z) + y - 64.0F) * 0.05F / 30.0F;
			boolean result = world.canSnowFall(x, y, z);
			biome.temperature = 0.2F;
			return result;
		}
		return world.canSnowFall(x, y, z);
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
