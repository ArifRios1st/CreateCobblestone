package net.createcobblestone.neoforge.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public record GeneratorTypeComponent(ResourceLocation typeId) {
    public static final Codec<GeneratorTypeComponent> CODEC = RecordCodecBuilder.create(i ->
            i.group(
                    ResourceLocation.CODEC.fieldOf("type").forGetter(GeneratorTypeComponent::typeId)
            ).apply(i, GeneratorTypeComponent::new)
    );

    public static final StreamCodec<ByteBuf, GeneratorTypeComponent> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, GeneratorTypeComponent::typeId,
            GeneratorTypeComponent::new
    );

    public GeneratorTypeComponent {
        Objects.requireNonNull(typeId, "typeId");
    }
}