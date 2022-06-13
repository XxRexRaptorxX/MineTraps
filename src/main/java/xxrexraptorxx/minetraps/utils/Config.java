package xxrexraptorxx.minetraps.utils;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_WORLD = "world";
    public static final String CATEGORY_BLOCKS = "blocks";

    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue UPDATE_CHECKER;
    public static ForgeConfigSpec.BooleanValue WORLD_GENERATION;
    public static ForgeConfigSpec.BooleanValue BARBED_WIRE_DESTROY_ITEMS;
    public static ForgeConfigSpec.IntValue BARBED_WIRE_DAMAGE;
    public static ForgeConfigSpec.IntValue BARBED_WIRE_FENCE_DAMAGE;
    public static ForgeConfigSpec.IntValue RAZOR_WIRE_DAMAGE;
    public static ForgeConfigSpec.IntValue BEAR_TRAP_DAMAGE;
    public static ForgeConfigSpec.IntValue CHEST_BOMB_EXPLOSION_RADIUS;
    public static ForgeConfigSpec.IntValue MINE_EXPLOSION_RADIUS;
    public static ForgeConfigSpec.IntValue MINE_DAMAGE;
    public static ForgeConfigSpec.IntValue NAIL_TRAP_DAMAGE;
    public static ForgeConfigSpec.IntValue POSION_MINE_EFFECT_DURATION;
    public static ForgeConfigSpec.IntValue POSION_MINE_EFFECT_AMPLIFIER;
    public static ForgeConfigSpec.IntValue POSION_MINE_CLOUD_DURATION;
    public static ForgeConfigSpec.IntValue TOXIC_NAIL_TRAP_EFFECT_DURATION;
    public static ForgeConfigSpec.IntValue TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER;
    public static ForgeConfigSpec.IntValue TOXIN_POISON_EFFECT_DURATION;
    public static ForgeConfigSpec.IntValue TOXIN_POISON_EFFECT_AMPLIFIER;
    public static ForgeConfigSpec.IntValue TOXIN_CONFUSION_EFFECT_DURATION;
    public static ForgeConfigSpec.IntValue TOXIN_CONFUSION_EFFECT_AMPLIFIER;
    public static ForgeConfigSpec.IntValue SPIKES_DAMAGE;
    public static ForgeConfigSpec.IntValue QUICKSAND_DAMAGE;
    public static ForgeConfigSpec.IntValue TOXIC_SPIKES_EFFECT_DURATION;
    public static ForgeConfigSpec.IntValue TOXIC_SPIKES_EFFECT_AMPLIFIER;
    public static ForgeConfigSpec.IntValue EXPLOSIVE_BLOCK_RADIUS;



    public static void init() {
        initClient();
        initServer();
        initCommon();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }


    public static void initClient() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("General").push(CATEGORY_GENERAL);
        UPDATE_CHECKER = builder.comment("Activate the Update-Checker").define("update-checker", true);
        builder.pop();

        CLIENT_CONFIG = builder.build();
    }


    public static void initServer() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Blocks").push(CATEGORY_BLOCKS);
        BARBED_WIRE_DESTROY_ITEMS = builder.comment("If enabled, barbed wire and razor wire destroys items").define("barbed_wire_destroy_items", false);
        BARBED_WIRE_DAMAGE = builder.comment("Defines how much damage the Barbed Wire do").defineInRange("barbed_wire_damage", 1, 1, 50);
        BARBED_WIRE_FENCE_DAMAGE = builder.comment("Defines how much damage the Barbed Wire Fence do").defineInRange("barbed_wire_fence_damage", 1, 1, 50);
        RAZOR_WIRE_DAMAGE = builder.comment("Defines how much damage the Razor Wire do").defineInRange("razor_wire_damage", 3, 1, 50);
        BEAR_TRAP_DAMAGE = builder.comment("Defines how much damage the Bear Trap do").defineInRange("bear_trap_damage", 1, 1, 50);
        CHEST_BOMB_EXPLOSION_RADIUS = builder.comment("Defines how big the explosion radius from a Chest Bombs is").defineInRange("chest_bomb_explosion_radius", 3, 1, 50);
        MINE_EXPLOSION_RADIUS = builder.comment("Defines how big the explosion radius from the Mines is").defineInRange("mine_explosion_radius", 5, 1, 100);
        MINE_DAMAGE = builder.comment("Defines how much damage the Mines do").defineInRange("mine_damage", 10, 1, 100);
        NAIL_TRAP_DAMAGE = builder.comment("Defines how much damage the Nail Traps do").defineInRange("nail_trap_damage", 1, 1, 50);
        POSION_MINE_CLOUD_DURATION = builder.comment("Defines how long the duration of the Poison Mine effect cloud is").defineInRange("poison_mine_cloud_duration", 50, 0, 10000);
        POSION_MINE_EFFECT_DURATION = builder.comment("Defines how long the duration of the poison effect from the mine is").defineInRange("poison_mine_effect_duration", 280, 0, 10000);
        POSION_MINE_EFFECT_AMPLIFIER = builder.comment("Defines how long the amplifier of the poison effect from the mine is").defineInRange("poison_mine_effect_amplifier", 1, 0, 10);
        TOXIC_NAIL_TRAP_EFFECT_DURATION = builder.comment("Defines how long the duration of the poison effect from the Toxic Nail Trap is").defineInRange("toxic_nail_trap_effect_duration", 150, 0, 10000);
        TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER = builder.comment("Defines how long the amplifier of the poison effect from the Toxic Nail Trap is").defineInRange("toxic_nail_trap_effect_amplifier", 0, 0, 10);
        TOXIN_POISON_EFFECT_DURATION = builder.comment("Defines how long the duration of the poison effect from the Toxin is").defineInRange("toxin_effect_duration", 500, 0, 10000);
        TOXIN_POISON_EFFECT_AMPLIFIER = builder.comment("Defines how long the amplifier of the poison effect from the Toxin is").defineInRange("toxin_effect_amplifier", 0, 0, 10);
        TOXIN_CONFUSION_EFFECT_DURATION = builder.comment("Defines how long the duration of the confusion effect from the Toxin is").defineInRange("toxin_effect_duration", 100, 0, 10000);
        TOXIN_CONFUSION_EFFECT_AMPLIFIER = builder.comment("Defines how long the amplifier of the confusion effect from the Toxin is").defineInRange("toxin_effect_amplifier", 0, 0, 10);
        SPIKES_DAMAGE = builder.comment("Defines how much damage the Spikes do").defineInRange("spikes_damage", 4, 1, 100);
        TOXIC_SPIKES_EFFECT_DURATION = builder.comment("Defines how long the duration of the poison effect from the Toxic Spikes is").defineInRange("toxic_spikes_effect_duration", 250, 0, 10000);
        TOXIC_SPIKES_EFFECT_AMPLIFIER = builder.comment("Defines how long the amplifier of the poison effect from the Toxic Spikes is").defineInRange("toxic_spikes_effect_amplifier", 0, 0, 10);
        QUICKSAND_DAMAGE = builder.comment("Defines how much damage the Quicksand do").defineInRange("quicksand_damage", 0, 1, 100);
        EXPLOSIVE_BLOCK_RADIUS = builder.comment("Defines how big the explosion radius from the Explosive Block is").defineInRange("explosive_block_radius", 3, 1, 100);

        builder.pop();

        SERVER_CONFIG = builder.build();
    }


    public static void initCommon() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("World").push(CATEGORY_WORLD);
        WORLD_GENERATION = builder.comment("Quicksand/Mud/Toxin generation in the world").define("world_generation", true);
        COMMON_CONFIG = builder.build();
    }


}