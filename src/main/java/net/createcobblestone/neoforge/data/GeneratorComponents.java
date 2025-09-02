package net.createcobblestone.neoforge.data;

import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public final class GeneratorComponents {
    public static final DeferredRegister.DataComponents REGISTRAR =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, CreateCobblestoneNeoForge.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GeneratorTypeComponent>> GENERATOR_TYPE =
            register("generator_type", builder -> builder
                    .persistent(GeneratorTypeComponent.CODEC)
                    .networkSynchronized(GeneratorTypeComponent.STREAM_CODEC)
            );

    private GeneratorComponents() {}

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator){
        return REGISTRAR.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }


    public static void register(IEventBus bus) {
        REGISTRAR.register(bus);
    }

}