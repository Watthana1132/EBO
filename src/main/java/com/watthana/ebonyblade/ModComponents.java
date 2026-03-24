package com.watthana.ebonyblade;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModComponents {
    public static final DataComponentType<Integer> KILL_COUNT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(EbonyBlade.MOD_ID, "kill_count"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static void initialize() {
        EbonyBlade.LOGGER.info("Registering components for {}", EbonyBlade.MOD_ID);
    }
}