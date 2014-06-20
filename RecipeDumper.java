package vitzli.recipedumper;

import java.util.Iterator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.MinecraftForge;
import vitzli.recipedumper.lib.Reference;
import vitzli.recipedumper.lib.CommandHandler;
import vitzli.recipedumper.lib.RecipeManager;
import vitzli.recipedumper.lib.Configuration;
import vitzli.recipedumper.modules.*;

@Mod(modid= Reference.MOD_ID, name = Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
			channels = {Reference.CHANNEL_NAME})
public class RecipeDumper {
    
    public RecipeManager recipemanager;
    
	@Instance(Reference.MOD_ID)
	public static RecipeDumper instance;
	
	public RecipeDumper() {
		this.recipemanager = new RecipeManager(this);
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// TODO
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		// TODO
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		recipemanager.registerModule(new ShapedRecipesModule());
		recipemanager.registerModule(new ShapelessModule());
		recipemanager.registerModule(new ShapedOreModule());
		recipemanager.registerModule(new ShapelessOreModule());
		recipemanager.registerModule(new SmeltingModule());
	}
		
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler.initCommand(event);
	}
	
}
