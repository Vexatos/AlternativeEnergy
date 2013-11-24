package jkmau5.alternativeenergy.compat.fmp;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;
import jkmau5.alternativeenergy.world.blocks.AltEngBlocks;
import net.minecraft.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public class PartFactory implements MultiPartRegistry.IPartFactory, MultiPartRegistry.IPartConverter {

    @Override
    public boolean canConvert(int blockID) {
        return blockID == AltEngBlocks.blockPowerCable.blockID;
    }

    @Override
    public TMultiPart convert(World world, BlockCoord pos) {
        int id = world.getBlockId(pos.x, pos.y, pos.z);
        int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
        if(id == AltEngBlocks.blockPowerCable.blockID){
            return new CablePart();
        }
        return null;
    }

    @Override
    public TMultiPart createPart(String name, boolean client) {
        if(name.equals("altEng_powerCable")) return new CablePart();
        return null;
    }
}
