package jkmau5.alternativeenergy.compat.fmp;

import codechicken.lib.lighting.LazyLightMatrix;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.IHollowConnect;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.minecraft.IPartMeta;
import codechicken.multipart.minecraft.PartMetaAccess;
import jkmau5.alternativeenergy.world.blocks.AltEngBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * No description given
 *
 * @author jk-5
 */
public class CablePart extends AltEngPart implements IHollowConnect, JNormalOcclusion, IPartMeta {

    public static Cuboid6[] boundingBoxes = new Cuboid6[7];
    private static int expandBounds = -1;

    static {
        double w = 2/8D;
        boundingBoxes[6] = new Cuboid6(0.5-w, 0.5-w, 0.5-w, 0.5+w, 0.5+w, 0.5+w);
        for(int s = 0; s < 6; s++)
            boundingBoxes[s] = new Cuboid6(0.5-w, 0, 0.5-w, 0.5+w, 0.5-w, 0.5+w)
                    .apply(Rotation.sideRotations[s].at(Vector3.center));
    }

    @Override
    public Block getBlock() {
        return AltEngBlocks.blockPowerCable;
    }

    /**
     * The unique string identifier for this class of multipart.
     */
    @Override
    public String getType() {
        return "altEng_powerCable";
    }

    /**
     * @return The size (width and height) of the connection in pixels. Must be be less than 12 and more than 0
     */
    @Override
    public int getHollowSize() {
        return 6;
    }

    @Override
    public Iterable<Cuboid6> getOcclusionBoxes() {
        if(this.expandBounds >= 0){
            return Arrays.asList(boundingBoxes[this.expandBounds]);
        }else{
            return Arrays.asList(boundingBoxes[6]);
        }
    }

    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
        LinkedList<Cuboid6> list = new LinkedList<Cuboid6>();
        list.add(boundingBoxes[6]);
        //for(int s = 0; s < 6; s++)
            //if(maskConnects(s))
                //list.add(boundingBoxes[s]);
        return list;
    }

    @Override
    public boolean occlusionTest(TMultiPart npart){
        return NormalOcclusionTest.apply(this, npart);
    }

    @Override
    public void renderStatic(Vector3 pos, LazyLightMatrix olm, int pass) {
        if(pass == 0){
            new RenderBlocks(new PartMetaAccess(this)).renderBlockByRenderType(getBlock(), x(), y(), z());
        }
    }

    @Override
    public int getMetadata() {
        return 0;
    }

    @Override
    public World getWorld() {
        return world();
    }

    @Override
    public int getBlockId() {
        return AltEngBlocks.blockPowerCable.blockID;
    }

    @Override
    public BlockCoord getPos() {
        return new BlockCoord(tile());
    }

    @Override
    public boolean doesTick() {
        return false;
    }
}
