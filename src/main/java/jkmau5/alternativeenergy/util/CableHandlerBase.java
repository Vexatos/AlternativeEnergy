package jkmau5.alternativeenergy.util;

import jkmau5.alternativeenergy.world.tileentity.TileEntityPowerCable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

/**
 * No description given
 *
 * @author jk-5
 */
public class CableHandlerBase {

    public boolean[] getConnections(IBlockAccess world, int x, int y, int z){
        boolean[] ret = new boolean[]{false, false, false, false, false, false, false};
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if(tile != null && tile instanceof TileEntityPowerCable){
            TileEntityPowerCable cable = (TileEntityPowerCable) tile;
            for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
                ret[direction.ordinal()] = cable.getConnectionMatrix().isConnected(direction);
            }
        }
        return ret;
    }
}
