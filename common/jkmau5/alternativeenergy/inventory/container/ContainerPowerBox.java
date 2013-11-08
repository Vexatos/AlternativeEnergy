package jkmau5.alternativeenergy.inventory.container;

import jkmau5.alternativeenergy.AlternativeEnergy;
import jkmau5.alternativeenergy.inventory.slot.SlotAdvanced;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import jkmau5.alternativeenergy.world.tileentity.TileEntityPowerBox;

import java.util.ArrayList;

/**
 * Author: Lordmau5
 * Date: 23.08.13
 * Time: 14:40
 * You are allowed to change this code,
 * however, not to publish it without my permission.
 */
public class ContainerPowerBox extends Container {

    protected TileEntityPowerBox tileEntity;

    public static ArrayList<String> idList = new ArrayList<String>();

    public ContainerPowerBox (InventoryPlayer inventoryPlayer, TileEntityPowerBox te){
        tileEntity = te;


        addSlotToContainer(new SlotAdvanced(tileEntity.capacitySlot, 0, 131, 17, 16));
        addSlotToContainer(new SlotAdvanced(tileEntity.outputSpeedSlot, 0, 152, 17, 2));

        if(AlternativeEnergy.ICSupplied) {
            addSlotToContainer(new SlotAdvanced(tileEntity.chargeSlot, 0, 33, 23, 64));
            addSlotToContainer(new SlotAdvanced(tileEntity.dischargeSlot, 0, 33, 45, 64));
        }

        bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }


    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }
}