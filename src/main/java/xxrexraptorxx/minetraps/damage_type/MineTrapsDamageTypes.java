package xxrexraptorxx.minetraps.damage_type;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MineTrapsDamageTypes {
    public static final RegistryKey<DamageType> SPIKES =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("minetraps", "spikes"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(
                world.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key));
    }

    public static void registerDamageTypes() {
        new MineTrapsDamageTypes();
    }
}
