package jkmau5.alternativeenergy.world.tileentity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import jkmau5.alternativeenergy.AltEngLog;
import jkmau5.alternativeenergy.network.synchronisation.ISynchronisationHandler;
import jkmau5.alternativeenergy.network.synchronisation.ISynchronized;
import jkmau5.alternativeenergy.network.synchronisation.SynchronisationMapTileEntity;
import jkmau5.alternativeenergy.network.synchronisation.SynchronsiationMap;
import jkmau5.alternativeenergy.util.interfaces.ISaveNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * No description given
 *
 * @author jk-5
 */
public abstract class SynchronizedTileEntity extends AltEngTileEntity implements ISynchronisationHandler, ISaveNBT {

    protected SynchronisationMapTileEntity<SynchronizedTileEntity> syncMap;
    private static final Map<Class<? extends SynchronizedTileEntity>, List<Field>> syncedFields = Maps.newIdentityHashMap();

    private static final Comparator<Field> FIELD_NAME_COMPARATOR = new Comparator<Field>() {
        @Override
        public int compare(Field o1, Field o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public SynchronizedTileEntity() {
        syncMap = new SynchronisationMapTileEntity<SynchronizedTileEntity>(this);
        this.createSynchronizedFields();
        this.registerFields();
    }

    protected abstract void createSynchronizedFields();

    private List<Field> getSyncedFields() {
        List<Field> result = syncedFields.get(getClass());

        if (result == null) {
            Set<Field> fields = Sets.newTreeSet(FIELD_NAME_COMPARATOR);
            for (Field field : getClass().getDeclaredFields()) {
                if (ISynchronized.class.isAssignableFrom(field.getType())) {
                    fields.add(field);
                    field.setAccessible(true);
                }
            }
            result = ImmutableList.copyOf(fields);
            syncedFields.put(getClass(), result);
        }

        return result;
    }

    private void registerFields() {
        for (Field field : getSyncedFields()) {
            try {
                addSyncedObject(field.getName(), (ISynchronized) field.get(this));
            } catch (Exception e) {
                AltEngLog.severe(e, "Exception while registering synced field '%s'", field);
            }
        }
    }

    public void addSyncedObject(String name, ISynchronized obj) {
        syncMap.put(name, obj);
    }

    public void sync() {
        if (syncMap.sync()) {
            onSync();
        }
    }

    public void onSync() {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!this.worldObj.isRemote) {
            this.sync();
        }
    }

    @Override
    public SynchronsiationMap<SynchronizedTileEntity> getSyncMap() {
        return syncMap;
    }

    @Override
    public Packet getDescriptionPacket() {
        try {
            return syncMap.createPacket(true, false);
        } catch (IOException e) {
            AltEngLog.severe(e, "Error during description packet creation");
            return null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        syncMap.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        syncMap.readFromNBT(tag);
    }
}
