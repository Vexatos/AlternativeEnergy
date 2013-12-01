package jkmau5.alternativeenergy.compat.fmp;

import codechicken.multipart.MultiPartRegistry;
import jkmau5.alternativeenergy.compat.ICompatPlugin;

/**
 * No description given
 *
 * @author jk-5
 */
public class PluginForgeMultipart implements ICompatPlugin {

    @Override
    public String getModID() {
        return "ForgeMultipart";
    }

    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        PartFactory factory = new PartFactory();
        MultiPartRegistry.registerConverter(factory);
        MultiPartRegistry.registerParts(factory, new String[]{"altEng:powerCable"});
    }

    @Override
    public void postInit() {

    }
}
