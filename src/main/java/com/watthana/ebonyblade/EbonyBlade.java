package com.watthana.ebonyblade;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EbonyBlade implements ModInitializer {
    public static final String MOD_ID = "ebonyblade";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModComponents.initialize();
        ModItems.initialize();
        EbonyBladeEvents.initialize();

        LOGGER.info("Ebony Blade has been initialized.");
    }
}