package jkmau5.alternativeenergy.power;

import jkmau5.alternativeenergy.util.interfaces.ISaveNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

/**
 * No description given
 *
 * @author jk-5
 */
public class LinkBoxWorldData extends WorldSavedData implements ISaveNBT {

    public NBTTagCompound data;

    public LinkBoxWorldData(String id) {
        super(id);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        LinkBoxNetwork.getInstance().readFromNBT(nbttagcompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        LinkBoxNetwork.getInstance().writeToNBT(nbttagcompound);
    }
}
