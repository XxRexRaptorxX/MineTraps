package xxrexraptorxx.minetraps.datagen;
/*
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xxrexraptorxx.minetraps.main.References;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = References.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        ModBlockTags blockTags = new ModBlockTags(packOutput, lookupProvider);
        //generator.addProvider(event.includeServer(), blockTags);
        //generator.addProvider(event.includeServer(), new ModLootTables(packOutput)); TODO!
        //generator.addProvider(event.includeClient(), new ModItemModels(packOutput));

    }
}*/