package com.github.peeftube.spiromodneo.core.init.registry.data.subdata;

import net.minecraft.world.level.block.*;

public record PlanksCollection(GenericConstructionCollection base, ButtonBlock button, FenceBlock fence,
                               FenceGateBlock gate, TrapDoorBlock trapdoor, DoorBlock door, SignBlock sign,
                               CeilingHangingSignBlock ceilingSign, WallHangingSignBlock wallSign,
                               GenericConstructionCollection tile, GenericConstructionCollection tile2,
                               GenericConstructionCollection herringbone, RotatedPillarBlock wallPanel)
{
}
