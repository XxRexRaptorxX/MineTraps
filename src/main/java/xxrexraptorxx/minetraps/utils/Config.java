package xxrexraptorxx.minetraps.utils;

// TODO Store config to file

import com.mojang.datafixers.util.Pair;
import xxrexraptorxx.minetraps.main.References;

public class Config {
    public static SimpleConfig CONFIG_FILE;
    private static MineTrapsConfigProvider configs;

    //public static final String CATEGORY_GENERAL = "general";
    //public static final String CATEGORY_BLOCKS = "blocks";

    public static Boolean UPDATE_CHECKER;
    public static Boolean BARBED_WIRE_DESTROY_ITEMS;
    public static Integer BARBED_WIRE_DAMAGE;
    public static Integer BARBED_WIRE_FENCE_DAMAGE;
    public static Integer RAZOR_WIRE_DAMAGE;
    public static Integer BEAR_TRAP_DAMAGE;
    public static Integer CHEST_BOMB_EXPLOSION_RADIUS;
    public static Integer MINE_EXPLOSION_RADIUS;
    public static Integer MINE_DAMAGE;
    public static Integer NAIL_TRAP_DAMAGE;
    public static Integer POSION_MINE_EFFECT_DURATION;
    public static Integer POSION_MINE_EFFECT_AMPLIFIER;
    public static Integer POSION_MINE_CLOUD_DURATION;
    public static Integer TOXIC_NAIL_TRAP_EFFECT_DURATION;
    public static Integer TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER;
    public static Integer TOXIN_POISON_EFFECT_DURATION;
    public static Integer TOXIN_POISON_EFFECT_AMPLIFIER;
    public static Integer TOXIN_CONFUSION_EFFECT_DURATION;
    public static Integer TOXIN_CONFUSION_EFFECT_AMPLIFIER;
    public static Integer SPIKES_DAMAGE;
    public static Integer QUICKSAND_DAMAGE;
    public static Integer TOXIC_SPIKES_EFFECT_DURATION;
    public static Integer TOXIC_SPIKES_EFFECT_AMPLIFIER;
    public static Integer EXPLOSIVE_BLOCK_RADIUS;
    public static Boolean PATREON_REWARDS;


    public static void initClient() {
        configs = new MineTrapsConfigProvider();
        configs.addKeyValuePair(new Pair<>("update-checker", false), "Values: true/false", "Activate the Update-Checker");

        CONFIG_FILE = SimpleConfig.of(References.MODID + "-client").provider(configs).request();

        UPDATE_CHECKER = CONFIG_FILE.getOrDefault("update-checker", false);
    }


    public static void initServer() {
        configs = new MineTrapsConfigProvider();
        createServerConfigs();

        CONFIG_FILE = SimpleConfig.of(References.MODID + "-server").provider(configs).request();

        assignServerConfigs();
    }


    private static void createServerConfigs() {
        configs.addKeyValuePair(new Pair<>("patreon_rewards", false), "Values: true/false)", "Enables ingame rewards on first spawn for Patreons");

        configs.addKeyValuePair(new Pair<>("barbed_wire_destroy_items", false), "Values: true/false", "If enabled, barbed wire and razor wire destroy items");
        configs.addKeyValuePair(new Pair<>("barbed_wire_damage", 1), "Range: 1 - 50", "Defines how much damage the Barbed Wires do");
        configs.addKeyValuePair(new Pair<>("barbed_wire_fence_damage", 1), "Range: 1 - 50", "Defines how much damage the Barbed Wire Fences do");
        configs.addKeyValuePair(new Pair<>("razor_wire_damage", 3), "Range: 1 - 50", "Defines how much damage the Razor Wires do");
        configs.addKeyValuePair(new Pair<>("bear_trap_damage", 1), "Range: 1 - 50", "Defines how much damage the Bear Traps do");
        configs.addKeyValuePair(new Pair<>("chest_bomb_explosion_radius", 3), "Range: 1 - 50", "Defines how big the explosion radius from a Chest Bombs is");
        configs.addKeyValuePair(new Pair<>("mine_explosion_radius", 5), "Range: 1 - 100", "Defines how big the explosion radius from the Mines is");
        configs.addKeyValuePair(new Pair<>("mine_damage", 10), "Range: 1 - 100", "Defines how much damage the Mines do");
        configs.addKeyValuePair(new Pair<>("nail_trap_damage", 1), "Range: 1 - 50", "Defines how much damage the Nail Traps do");
        configs.addKeyValuePair(new Pair<>("poison_mine_cloud_duration", 50), "Range: 0 - 10000", "Defines how long the duration of the Poison Mine effect cloud is");
        configs.addKeyValuePair(new Pair<>("poison_mine_effect_duration", 280), "Range: 0 - 10000", "Defines how long the duration of the poison effect from the mine is");
        configs.addKeyValuePair(new Pair<>("poison_mine_effect_amplifier", 1), "Range: 0 - 10", "Defines how long the amplifier of the poison effect from the mine is");
        configs.addKeyValuePair(new Pair<>("toxic_nail_trap_effect_duration", 150), "Range: 0 - 10000", "Defines how long the duration of the poison effect from the Toxic Nail Trap is");
        configs.addKeyValuePair(new Pair<>("toxic_nail_trap_effect_amplifier", 0), "Range: 0 - 10", "Defines how long the amplifier of the poison effect from the Toxic Nail Trap is");
        configs.addKeyValuePair(new Pair<>("toxin_poison_effect_duration", 500), "Range: 0 - 10000", "Defines how long the duration of the poison effect from the Toxin is");
        configs.addKeyValuePair(new Pair<>("toxin_poison_effect_amplifier", 0), "Range: 0 - 10", "Defines how long the amplifier of the poison effect from the Toxin is");
        configs.addKeyValuePair(new Pair<>("toxin_confusion_effect_duration", 100), "Range: 0 - 10000", "Defines how long the duration of the confusion effect from the Toxin is");
        configs.addKeyValuePair(new Pair<>("toxin_confusion_effect_amplifier", 0), "Range: 0 - 10", "Defines how long the amplifier of the confusion effect from the Toxin is");
        configs.addKeyValuePair(new Pair<>("spikes_damage", 4), "Range: 1 - 100", "Defines how much damage the Spikes do");
        configs.addKeyValuePair(new Pair<>("toxic_spikes_effect_duration", 250), "Range: 0 - 10000", "Defines how long the duration of the poison effect from the Toxic Spikes is");
        configs.addKeyValuePair(new Pair<>("toxic_spikes_effect_amplifier", 0), "Range: 0 - 10", "Defines how long the amplifier of the poison effect from the Toxic Spikes is");
        configs.addKeyValuePair(new Pair<>("quicksand_damage", 0), "Range: 1 - 100", "Defines how much damage the Quicksand does");
        configs.addKeyValuePair(new Pair<>("explosive_block_radius", 3), "Range: 1 - 100", "Defines how big the explosion radius from the Explosive Block is");
    }

    private static void assignServerConfigs() {
        PATREON_REWARDS = CONFIG_FILE.getOrDefault("patreon_rewards", false);

        BARBED_WIRE_DESTROY_ITEMS = CONFIG_FILE.getOrDefault("barbed_wire_destroy_items", false);
        BARBED_WIRE_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("barbed_wire_damage", 1), 1, 50);
        BARBED_WIRE_FENCE_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("barbed_wire_fence_damage", 1), 1, 50);
        RAZOR_WIRE_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("razor_wire_damage", 3), 1, 50);
        BEAR_TRAP_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("bear_trap_damage", 1), 1, 50);
        CHEST_BOMB_EXPLOSION_RADIUS = defineInRange(CONFIG_FILE.getOrDefault("chest_bomb_explosion_radius", 3), 1, 50);
        MINE_EXPLOSION_RADIUS = defineInRange(CONFIG_FILE.getOrDefault("mine_explosion_radius", 5), 1, 100);
        MINE_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("mine_damage", 10), 1, 100);
        NAIL_TRAP_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("nail_trap_damage", 1), 1, 50);
        POSION_MINE_CLOUD_DURATION = defineInRange(CONFIG_FILE.getOrDefault("poison_mine_cloud_duration", 50), 0, 10000);
        POSION_MINE_EFFECT_DURATION = defineInRange(CONFIG_FILE.getOrDefault("poison_mine_effect_duration", 280), 0, 10000);
        POSION_MINE_EFFECT_AMPLIFIER = defineInRange(CONFIG_FILE.getOrDefault("poison_mine_effect_amplifier", 1), 0, 10);
        TOXIC_NAIL_TRAP_EFFECT_DURATION = defineInRange(CONFIG_FILE.getOrDefault("toxic_nail_trap_effect_duration", 150), 0, 10000);
        TOXIC_NAIL_TRAP_EFFECT_AMPLIFIER = defineInRange(CONFIG_FILE.getOrDefault("toxic_nail_trap_effect_amplifier", 0), 0, 10);
        TOXIN_POISON_EFFECT_DURATION = defineInRange(CONFIG_FILE.getOrDefault("toxin_poison_effect_duration", 500), 0, 10000);
        TOXIN_POISON_EFFECT_AMPLIFIER = defineInRange(CONFIG_FILE.getOrDefault("toxin_poison_effect_amplifier", 0), 0, 10);
        TOXIN_CONFUSION_EFFECT_DURATION = defineInRange(CONFIG_FILE.getOrDefault("toxin_confusion_effect_duration", 100), 0, 10000);
        TOXIN_CONFUSION_EFFECT_AMPLIFIER = defineInRange(CONFIG_FILE.getOrDefault("toxin_confusion_effect_amplifier", 0), 0, 10);
        SPIKES_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("spikes_damage", 4), 1, 100);
        TOXIC_SPIKES_EFFECT_DURATION = defineInRange(CONFIG_FILE.getOrDefault("toxic_spikes_effect_duration", 250), 0, 10000);
        TOXIC_SPIKES_EFFECT_AMPLIFIER = defineInRange(CONFIG_FILE.getOrDefault("toxic_spikes_effect_amplifier", 0), 0, 10);
        QUICKSAND_DAMAGE = defineInRange(CONFIG_FILE.getOrDefault("quicksand_damage", 0), 1, 100);
        EXPLOSIVE_BLOCK_RADIUS = defineInRange(CONFIG_FILE.getOrDefault("explosive_block_radius", 3), 1, 100);

        System.out.println("All " + configs.getConfigsList().size() + " config entries have been set properly");
    }

    private static int defineInRange(Integer value, Integer min, Integer max) {
        if (value > max)
            return max;
        if (value < min)
            return min;
        return value;
    }

}