package fr.krepe.belzebutil;

import com.mojang.logging.LogUtils;
import fr.krepe.belzebutil.block.ModBlocks;
import fr.krepe.belzebutil.block.ModBlockEntities;
import fr.krepe.belzebutil.block.SpecialModBlocks;
import fr.krepe.belzebutil.entity.ModEntityTypes;
import fr.krepe.belzebutil.item.ModItems;
import fr.krepe.belzebutil.network.ModMessages;
import fr.krepe.belzebutil.recipies.ModRecipes;
import fr.krepe.belzebutil.screen.EnergyGeneratorScreen;
import fr.krepe.belzebutil.screen.ModMenuTypes;
import fr.krepe.belzebutil.screen.LeadStationScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(fr.krepe.belzebutil.Belzebutil.MOD_ID)
public class Belzebutil
{
    public static final String MOD_ID = "belzebutil";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Belzebutil()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.registerItem(eventBus);
        ModBlocks.registerBlock(eventBus);
        SpecialModBlocks.registerSpeBlock(eventBus);
        ModBlockEntities.register(eventBus);

        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);

        //ModConfiguredFeatures.register(eventBus);
        //ModPlacedFeatures.register(eventBus);

        ModEntityTypes.register(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {

        MenuScreens.register(ModMenuTypes.LEAD_STATION_MENU.get(), LeadStationScreen::new);
        MenuScreens.register(ModMenuTypes.ENERGY_GENERATOR_MENU.get(), EnergyGeneratorScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModMessages.register();
    }


}
