package jkmau5.alternativeenergy.compat.fmp;

import codechicken.lib.vec.Cuboid6;
import codechicken.microblock.IHollowConnect;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.minecraft.McMetaPart;
import com.google.common.collect.Lists;
import jkmau5.alternativeenergy.world.blocks.AltEngBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

import java.util.Arrays;
import java.util.List;

/**
 * No description given
 *
 * @author jk-5
 */
public class CablePart extends McMetaPart implements IHollowConnect {

    public CablePart() {

    }

    public CablePart(int meta) {
        super(meta);
    }

    @Override
    public Iterable<ItemStack> getDrops() {
        return Arrays.asList(new ItemStack(this.getBlock(), 1));
    }

    @Override
    public ItemStack pickItem(MovingObjectPosition hit) {
        return new ItemStack(this.getBlock(), 1);
    }

    @Override
    public boolean occlusionTest(TMultiPart npart) {
        return NormalOcclusionTest.apply(this, npart);
    }

    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {
        return Arrays.asList(new Cuboid6(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D));
    }

    @Override
    public Cuboid6 getBounds() {
        return new Cuboid6(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
    }

    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
        List l1 = Lists.newArrayList();
        List l2 = Lists.newArrayList();

        //((BlockPowerCable) AltEngBlocks.blockPowerCable).addCollisionBoxesToList(this.getWorld(), this.x(), this.y(), this.z());
        //TODO fix this
        return Arrays.asList(new Cuboid6(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D));
    }

    @Override
    public Block getBlock() {
        return AltEngBlocks.blockPowerCable;
    }

    @Override
    public int getHollowSize() {
        return 3;
    }

    @Override
    public String getType() {
        return "altEng:powerCable";
    }
}
