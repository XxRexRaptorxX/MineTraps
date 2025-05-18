package xxrexraptorxx.minetraps.utils;

import net.neoforged.neoforge.common.ModConfigSpec;
import xxrexraptorxx.magmacore.config.ConfigHelper;

public class Config {

    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec SERVER_CONFIG;

    private static ModConfigSpec.BooleanValue BARBED_WIRE_DESTROY_ITEMS;
    private static ModConfigSpec.IntValue BARBED_WIRE_DAMAGE;
    private static ModConfigSpec.IntValue BARBED_WIRE_FENCE_DAMAGE;
    private static ModConfigSpec.IntValue RAZOR_WIRE_DAMAGE;
    private static ModConfigSpec.IntValue BEAR_TRAP_DAMAGE;
    private static ModConfigSpec.IntValue CHEST_BOMB_EXPLOSION_RADIUS;
    private static ModConfigSpec.IntValue MINE_EXPLOSION_RADIUS;
    private static ModConfigSpec.IntValue MINE_DAMAGE;
    private static ModConfigSpec.IntValue NAIL_TRAP_DAMAGE;
    private static ModConfigSpec.IntValue POISON_MINE_CLOUD_DURATION;
    private static ModConfigSpec.IntValue POISON_MINE_EFFECT_DURATION;
    private static ModConfigSpec.IntValue POISON_MINE_EFFECT_AMPLIFIER;
    private static ModConfigSpec.IntValue TOXIC_NAIL_TRAP_EFFECT_DURATION;
    private static ModConfigSpec.IntValue TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER;
    private static ModConfigSpec.IntValue TOXIN_POISON_EFFECT_DURATION;
    private static ModConfigSpec.IntValue TOXIN_POISON_EFFECT_AMPLIFIER;
    private static ModConfigSpec.IntValue TOXIN_CONFUSION_EFFECT_DURATION;
    private static ModConfigSpec.IntValue TOXIN_CONFUSION_EFFECT_AMPLIFIER;
    private static ModConfigSpec.IntValue SPIKES_DAMAGE;
    private static ModConfigSpec.IntValue TOXIC_SPIKES_EFFECT_DURATION;
    private static ModConfigSpec.IntValue TOXIC_SPIKES_EFFECT_AMPLIFIER;
    private static ModConfigSpec.IntValue QUICKSAND_DAMAGE;
    private static ModConfigSpec.IntValue EXPLOSIVE_BLOCK_RADIUS;


    static {
        ConfigHelper.setCategory(SERVER_BUILDER, "barbed_wires");
        BARBED_WIRE_DESTROY_ITEMS        = SERVER_BUILDER.comment("If enabled, barbed wire and razor wire destroys items")
                .define("barbed_wire_destroy_items", false);
        BARBED_WIRE_DAMAGE               = SERVER_BUILDER.comment("Defines how much damage the Barbed Wire do")
                .defineInRange("barbed_wire_damage", 1, 1, 50);
        BARBED_WIRE_FENCE_DAMAGE         = SERVER_BUILDER.comment("Defines how much damage the Barbed Wire Fence do")
                .defineInRange("barbed_wire_fence_damage", 1, 1, 50);
        RAZOR_WIRE_DAMAGE                = SERVER_BUILDER.comment("Defines how much damage the Razor Wire do")
                .defineInRange("razor_wire_damage", 3, 1, 50);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "explosives");
        CHEST_BOMB_EXPLOSION_RADIUS      = SERVER_BUILDER.comment("Defines how big the explosion radius from a Chest Bomb is")
                .defineInRange("chest_bomb_explosion_radius", 3, 1, 50);
        MINE_EXPLOSION_RADIUS            = SERVER_BUILDER.comment("Defines how big the explosion radius from the Mines is")
                .defineInRange("mine_explosion_radius", 5, 1, 100);
        MINE_DAMAGE                      = SERVER_BUILDER.comment("Defines how much damage the Mines do")
                .defineInRange("mine_damage", 10, 1, 100);
        POISON_MINE_EFFECT_DURATION      = SERVER_BUILDER.comment("Duration of the poison effect from the mine in ticks")
                .defineInRange("poison_mine_effect_duration", 280, 0, 10000);
        POISON_MINE_EFFECT_AMPLIFIER     = SERVER_BUILDER.comment("Amplifier level of the poison effect from the mine")
                .defineInRange("poison_mine_effect_amplifier", 1, 0, 10);
        POISON_MINE_CLOUD_DURATION       = SERVER_BUILDER.comment("Duration of the Poison Mine effect cloud in ticks")
                .defineInRange("poison_mine_cloud_duration", 50, 0, 10000);
        EXPLOSIVE_BLOCK_RADIUS           = SERVER_BUILDER.comment("Defines how big the explosion radius from the Explosive Block is")
                .defineInRange("explosive_block_radius", 3, 1, 100);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "toxin");
        TOXIN_POISON_EFFECT_DURATION     = SERVER_BUILDER.comment("Duration of the poison effect from the Toxin in ticks")
                .defineInRange("toxin_poison_effect_duration", 500, 0, 10000);
        TOXIN_POISON_EFFECT_AMPLIFIER    = SERVER_BUILDER.comment("Amplifier level of the poison effect from the Toxin")
                .defineInRange("toxin_poison_effect_amplifier", 0, 0, 10);
        TOXIN_CONFUSION_EFFECT_DURATION  = SERVER_BUILDER.comment("Duration of the confusion effect from the Toxin in ticks")
                .defineInRange("toxin_confusion_effect_duration", 100, 0, 10000);
        TOXIN_CONFUSION_EFFECT_AMPLIFIER = SERVER_BUILDER.comment("Amplifier level of the confusion effect from the Toxin")
                .defineInRange("toxin_confusion_effect_amplifier", 0, 0, 10);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "nail_traps");
        NAIL_TRAP_DAMAGE                 = SERVER_BUILDER.comment("Defines how much damage the Nail Traps do")
                .defineInRange("nail_trap_damage", 1, 1, 50);
        TOXIC_NAIL_TRAP_EFFECT_DURATION  = SERVER_BUILDER.comment("Duration of the poison effect from the Toxic Nail Trap in ticks")
                .defineInRange("toxic_nail_trap_effect_duration", 150, 0, 10000);
        TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER = SERVER_BUILDER.comment("Amplifier level of the poison effect from the Toxic Nail Trap")
                .defineInRange("toxic_nail_trap_effect_amplifier", 0, 0, 10);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "spikes");
        SPIKES_DAMAGE                    = SERVER_BUILDER.comment("Defines how much damage the Spikes do")
                .defineInRange("spikes_damage", 4, 1, 100);
        TOXIC_SPIKES_EFFECT_DURATION    = SERVER_BUILDER.comment("Duration of the poison effect from the Toxic Spikes in ticks")
                .defineInRange("toxic_spikes_effect_duration", 250, 0, 10000);
        TOXIC_SPIKES_EFFECT_AMPLIFIER   = SERVER_BUILDER.comment("Amplifier level of the poison effect from the Toxic Spikes")
                .defineInRange("toxic_spikes_effect_amplifier", 0, 0, 10);
        SERVER_BUILDER.pop();

        ConfigHelper.setCategory(SERVER_BUILDER, "other_traps");
        QUICKSAND_DAMAGE                 = SERVER_BUILDER.comment("Defines how much damage the Quicksand do")
                .defineInRange("quicksand_damage", 0, 1, 100);
        BEAR_TRAP_DAMAGE                 = SERVER_BUILDER.comment("Defines how much damage the Bear Trap do")
                .defineInRange("bear_trap_damage", 1, 1, 50);
        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
    }


    public static boolean getBarbedWireDestroyItems() { return BARBED_WIRE_DESTROY_ITEMS.get(); }
    public static int getBarbedWireDamage()           { return BARBED_WIRE_DAMAGE.get(); }
    public static int getBarbedWireFenceDamage()      { return BARBED_WIRE_FENCE_DAMAGE.get(); }
    public static int getRazorWireDamage()            { return RAZOR_WIRE_DAMAGE.get(); }
    public static int getBearTrapDamage()             { return BEAR_TRAP_DAMAGE.get(); }
    public static int getChestBombExplosionRadius()   { return CHEST_BOMB_EXPLOSION_RADIUS.get(); }
    public static int getMineExplosionRadius()        { return MINE_EXPLOSION_RADIUS.get(); }
    public static int getMineDamage()                 { return MINE_DAMAGE.get(); }
    public static int getNailTrapDamage()             { return NAIL_TRAP_DAMAGE.get(); }
    public static int getPoisonMineCloudDuration()    { return POISON_MINE_CLOUD_DURATION.get(); }
    public static int getPoisonMineEffectDuration()   { return POISON_MINE_EFFECT_DURATION.get(); }
    public static int getPoisonMineEffectAmplifier()  { return POISON_MINE_EFFECT_AMPLIFIER.get(); }
    public static int getToxicNailTrapEffectDuration(){ return TOXIC_NAIL_TRAP_EFFECT_DURATION.get(); }
    public static int getToxicNailTrapEffectAmplifier(){ return TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER.get(); }
    public static int getToxinPoisonEffectDuration()  { return TOXIN_POISON_EFFECT_DURATION.get(); }
    public static int getToxinPoisonEffectAmplifier() { return TOXIN_POISON_EFFECT_AMPLIFIER.get(); }
    public static int getToxinConfusionEffectDuration(){ return TOXIN_CONFUSION_EFFECT_DURATION.get(); }
    public static int getToxinConfusionEffectAmplifier(){ return TOXIN_CONFUSION_EFFECT_AMPLIFIER.get(); }
    public static int getSpikesDamage()               { return SPIKES_DAMAGE.get(); }
    public static int getToxicSpikesEffectDuration()  { return TOXIC_SPIKES_EFFECT_DURATION.get(); }
    public static int getToxicSpikesEffectAmplifier() { return TOXIC_SPIKES_EFFECT_AMPLIFIER.get(); }
    public static int getQuicksandDamage()            { return QUICKSAND_DAMAGE.get(); }
    public static int getExplosiveBlockRadius()       { return EXPLOSIVE_BLOCK_RADIUS.get(); }
}