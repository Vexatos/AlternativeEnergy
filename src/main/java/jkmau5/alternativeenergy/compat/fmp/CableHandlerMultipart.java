package jkmau5.alternativeenergy.compat.fmp;

import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.PartMetaAccess;
import jkmau5.alternativeenergy.util.CableHandlerBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import scala.collection.Iterator;

/**
 * No description given
 *
 * @author jk-5
 */
public class CableHandlerMultipart extends CableHandlerBase {

    @Override
    public boolean[] getConnections(IBlockAccess world, int x, int y, int z) {
        if(world instanceof PartMetaAccess) world = ((PartMetaAccess) world).part.getWorld();
        boolean[] ret = super.getConnections(world, x, y, z);
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
            TileEntity tile = world.getBlockTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
            if(tile instanceof TileMultipart){
                TileMultipart multipart = (TileMultipart) tile;
                Iterator parts = multipart.partList().toIterator();
                while(parts.hasNext()){
                    TMultiPart part = (TMultiPart) parts.next();
                    if(part instanceof CablePart){
                        ret[direction.ordinal()] = true;
                    }
                }
            }
        }
        return ret;
    }
}
