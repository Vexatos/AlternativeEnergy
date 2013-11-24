package jkmau5.alternativeenergy.network;

import jkmau5.alternativeenergy.compat.fmp.FMPEventHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * No description given
 *
 * @author jk-5
 */
public class PacketPlaceMultipart extends AbstractPacket {

    public PacketPlaceMultipart(){}

    @Override
    public void writePacket(DataOutput data) throws IOException {

    }

    @Override
    public void readPacket(DataInput data) throws IOException {
        if(FMPEventHandler.needsEvents){
            FMPEventHandler.place(this.getSender(), this.getSender().worldObj);
        }
    }
}
