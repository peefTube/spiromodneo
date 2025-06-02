package com.github.peeftube.spiromodneo.mixin;

import com.github.peeftube.spiromodneo.util.worldgen.HugeVeins;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NoiseChunk.class)
public abstract class NoiseChunkMixin implements DensityFunction.ContextProvider, DensityFunction.FunctionContext
{
    // @Unique
    // private static ThreadLocal<ChunkAccess> spiromodneo$thisChunk = new ThreadLocal<>();
    //
    // @WrapOperation(method = "forChunk", at = @At(value = "NEW",
    //         target = "(ILnet/minecraft/world/level/levelgen/RandomState;IILnet/minecraft/world/level/levelgen/NoiseSettings;Lnet/minecraft/world/level/levelgen/DensityFunctions$BeardifierOrMarker;Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;Lnet/minecraft/world/level/levelgen/Aquifer$FluidPicker;Lnet/minecraft/world/level/levelgen/blending/Blender;)Lnet/minecraft/world/level/levelgen/NoiseChunk;"))
    // private static NoiseChunk doForChunk(int j1, RandomState blender$blendingoutput, int l, int j, NoiseSettings k, DensityFunctions.BeardifierOrMarker i, NoiseGeneratorSettings k1, Aquifer.FluidPicker l1, Blender blender, Operation<NoiseChunk> original, ChunkAccess chunk)
    // {
    //     spiromodneo$thisChunk.set(chunk);
    //     try { return original.call(j1, blender$blendingoutput, l, j, k, i, k1, l1, blender); }
    //     finally { assert spiromodneo$thisChunk != null; spiromodneo$thisChunk.remove(); }
    // }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/levelgen/OreVeinifier;create(Lnet/minecraft/world/level/levelgen/DensityFunction;Lnet/minecraft/world/level/levelgen/DensityFunction;Lnet/minecraft/world/level/levelgen/DensityFunction;Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;)Lnet/minecraft/world/level/levelgen/NoiseChunk$BlockStateFiller;"))
    NoiseChunk.BlockStateFiller onVeinify(DensityFunction veinToggle, DensityFunction veinRidged,
            DensityFunction veinGap, PositionalRandomFactory random, Operation<NoiseChunk.BlockStateFiller> original)
    { return HugeVeins.create(veinToggle, veinRidged, veinGap, random/*, spiromodneo$thisChunk.get()*/); }
}
