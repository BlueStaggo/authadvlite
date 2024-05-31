package io.bluestaggo.authadvlite.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class TallSpruceTreeFeature extends Feature {
	private boolean heightMod;
	private int minHeight;
	private int heightVariation;

	public TallSpruceTreeFeature(boolean var2) {
        this(var2, 15, 15);
    }

    public TallSpruceTreeFeature(boolean var2, int var3, int var4) {
        this(false, var2, var3, var4);
    }

    public TallSpruceTreeFeature(boolean var1, boolean var2, int var3, int var4) {
        super(var1);
        this.heightMod = var2;
		this.minHeight = var3;
        this.heightVariation = var4;
    }

	@Override
	public boolean place(World world, Random random, int x, int y, int z) {
		int var6 = random.nextInt(this.heightVariation) + this.minHeight;

        if (!this.func_150537_a(world, random, x, y, z, var6)) {
            return false;
        } else {
            this.func_150541_c(world, x, z, y + var6, 0, random);

            for (int var7 = 0; var7 < var6; ++var7) {
                Material var8 = world.getMaterial(x, y + var7, z);

                if (var8 == Material.AIR || var8 == Material.LEAVES) {
                    this.setBlockWithMetadata(world, x, y + var7, z, Block.LOG.id, 1);
                }

                if (var7 < var6 - 1) {
                    var8 = world.getMaterial(x + 1, y + var7, z);

                    if (var8 == Material.AIR || var8 == Material.LEAVES) {
                        this.setBlockWithMetadata(world, x + 1, y + var7, z, Block.LOG.id, 1);
                    }

                    var8 = world.getMaterial(x + 1, y + var7, z + 1);

                    if (var8 == Material.AIR || var8 == Material.LEAVES) {
                        this.setBlockWithMetadata(world, x + 1, y + var7, z + 1, Block.LOG.id, 1);
                    }

                    var8 = world.getMaterial(x, y + var7, z + 1);

                    if (var8 == Material.AIR || var8 == Material.LEAVES) {
                        this.setBlockWithMetadata(world, x, y + var7, z + 1, Block.LOG.id, 1);
                    }
                }
            }

            return true;
        }
	}

	private void func_150541_c(World var1, int var2, int var3, int var4, int var5, Random var6) {
        int var7 = var6.nextInt(5);

        if (this.heightMod) {
            var7 += this.heightVariation;
        } else {
            var7 += 3;
        }

        int var8 = 0;

        for (int var9 = var4 - var7; var9 <= var4; ++var9) {
            int var10 = var4 - var9;
            int var11 = var5 + MathHelper.floor((float) var10 / (float) var7 * 3.5F);
            this.func_150535_a(var1, var2, var9, var3,
                    var11 + (var10 > 0 && var11 == var8 && (var9 & 1) == 0 ? 1 : 0), var6);
            var8 = var11;
        }
    }

    public void func_150524_b(World var1, Random var2, int var3, int var4, int var5) {
        this.func_150539_c(var1, var2, var3 - 1, var4, var5 - 1);
        this.func_150539_c(var1, var2, var3 + 2, var4, var5 - 1);
        this.func_150539_c(var1, var2, var3 - 1, var4, var5 + 2);
        this.func_150539_c(var1, var2, var3 + 2, var4, var5 + 2);

        for (int var6 = 0; var6 < 5; ++var6) {
            int var7 = var2.nextInt(64);
            int var8 = var7 % 8;
            int var9 = var7 / 8;

            if (var8 == 0 || var8 == 7 || var9 == 0 || var9 == 7) {
                this.func_150539_c(var1, var2, var3 - 3 + var8, var4, var5 - 3 + var9);
            }
        }
    }

    private void func_150539_c(World var1, Random var2, int var3, int var4, int var5) {
        for (int var6 = -2; var6 <= 2; ++var6) {
            for (int var7 = -2; var7 <= 2; ++var7) {
                if (Math.abs(var6) != 2 || Math.abs(var7) != 2) {
                    this.func_150540_a(var1, var3 + var6, var4, var5 + var7);
                }
            }
        }
    }

    private void func_150540_a(World var1, int var2, int var3, int var4) {
        for (int var5 = var3 + 2; var5 >= var3 - 3; --var5) {
            int var6 = var1.getBlock(var2, var5, var4);

            if (var6 == Block.GRASS.id || var6 == Block.DIRT.id) {
                this.setBlockWithMetadata(var1, var2, var5, var4, Block.DIRT.id, 0);
				break;
            }

            if (var6 != 0 && var5 < var3) {
                break;
            }
        }
    }

	protected boolean func_150523_a(Block var1) {
        return var1 == null || var1.material == Material.LEAVES
                || var1 == Block.GRASS || var1 == Block.DIRT || var1 == Block.LOG
                || var1 == Block.SAPLING || var1 == Block.VINE;
    }

	private boolean func_150536_b(World var1, Random var2, int var3, int var4, int var5, int var6) {
        boolean var7 = true;

        if (var4 >= 1) {
            for (int var8 = var4; var8 <= var4 + 1 + var6; ++var8) {
                byte var9 = 2;

                if (var8 == var4) {
                    var9 = 1;
                }

                if (var8 >= var4 + 1 + var6 - 2) {
                    var9 = 2;
                }

                for (int var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
                    for (int var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                        if (var8 >= 0) {
                            int var12 = var1.getBlock(var10, var8, var11);

                            if (!this.func_150523_a(Block.BY_ID[var12])) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            return var7;
        } else {
            return false;
        }
    }

	private boolean func_150532_c(World var1, Random var2, int var3, int var4, int var5) {
        int var6 = var1.getBlock(var3, var4 - 1, var5);

        if ((var6 == Block.GRASS.id || var6 == Block.DIRT.id) && var4 >= 2) {
            this.setBlockWithMetadata(var1, var3, var4 - 1, var5, Block.DIRT.id, 0);
            this.setBlockWithMetadata(var1, var3 + 1, var4 - 1, var5, Block.DIRT.id, 0);
            this.setBlockWithMetadata(var1, var3, var4 - 1, var5 + 1, Block.DIRT.id, 0);
            this.setBlockWithMetadata(var1, var3 + 1, var4 - 1, var5 + 1, Block.DIRT.id, 0);
            return true;
        } else {
            return false;
        }
    }

	protected boolean func_150537_a(World var1, Random var2, int var3, int var4, int var5, int var6) {
        return this.func_150536_b(var1, var2, var3, var4, var5, var6)
                && this.func_150532_c(var1, var2, var3, var4, var5);
    }

	protected void func_150535_a(World var1, int var2, int var3, int var4, int var5, Random var6) {
        int var7 = var5 * var5;

        for (int var8 = var2 - var5; var8 <= var2 + var5 + 1; ++var8) {
            int var9 = var8 - var2;

            for (int var10 = var4 - var5; var10 <= var4 + var5 + 1; ++var10) {
                int var11 = var10 - var4;
                int var12 = var9 - 1;
                int var13 = var11 - 1;

                if (var9 * var9 + var11 * var11 <= var7 || var12 * var12 + var13 * var13 <= var7
                        || var9 * var9 + var13 * var13 <= var7
                        || var12 * var12 + var11 * var11 <= var7) {
                    Material var14 = var1.getMaterial(var8, var3, var10);

                    if (var14 == Material.AIR || var14 == Material.LEAVES) {
                        this.setBlockWithMetadata(var1, var8, var3, var10, Block.LEAVES.id, 1);
                    }
                }
            }
        }
    }
}
