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