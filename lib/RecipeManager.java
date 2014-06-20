package vitzli.recipedumper.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import vitzli.recipedumper.RecipeDumper;
import vitzli.recipedumper.lib.LogHelper;
import vitzli.recipedumper.lib.Configuration;
import vitzli.recipedumper.modules.IRecipesModule;



public class RecipeManager {

	RecipeDumper recipeDumper;
	CraftingManager mcCraftMan = CraftingManager.getInstance();
	
	ArrayList<IRecipesModule> modules = new ArrayList<IRecipesModule>();
	
	public RecipeManager(RecipeDumper rd) {
		this.recipeDumper = rd;
	}
	
	public void registerModule(String modulePath) {
        try
        {
            Class moduleclass = Loader.instance().getModClassLoader().loadClass(modulePath);
            IRecipesModule module = (IRecipesModule) moduleclass.getConstructor().newInstance();
            if(module.isEnabled()) {
            	registerModule(module);
            } else {
				LogHelper.fine("Module " + modulePath + " is disabled");
            }
        } catch(Exception ex)
        {
        	LogHelper.severe("Exception raised during loading of recipe module: " + modulePath);
            ex.printStackTrace();
        }
	}
	
	public void registerModule(IRecipesModule module){
        if(module.isEnabled()) {
        	modules.add(module);
        } else {
			LogHelper.fine("Module " + module.getPrefix() + " is disabled");
        }
	}
	
	public void cmdDumpAll() {
		LogHelper.info("Dumping everything");
		dumpRecipes();
		dumpOreDictionary();
		dumpItems();
	}
	
	public void cmdDumpOreDict() {
		LogHelper.info("Dumping ore dictionary");
		dumpOreDictionary();
		dumpItems();		
	}
	
	public void cmdDumpItemRecipes() {
		LogHelper.info("Dumping recipes");
		dumpRecipes();
		dumpItems();		
	}
	

	private void dumpItems() {
		LogHelper.fine("Dumping item IDs");
		String description = null;
		try {
			
			File fp = Configuration.createIDFile();
			Writer itemWriter = new FileWriter(fp);
			
			for (Entry<Integer,Integer> itemEntry : IDHandler.getEntries()) {
				try {
					description = Formatter.getISDumpString(itemEntry.getKey(), itemEntry.getValue());
					itemWriter.write(description);
					itemWriter.write("\n");
				} catch (Exception ex) {
					LogHelper.severe("Exception caught when writing item "
							+ itemEntry.getKey() + ":" + itemEntry.getValue());
				}
			}
			
			itemWriter.close();
		} catch (Exception ex) {
			LogHelper.severe("Exception caught during dumping item ids");
		}
	}
	
	private void dumpRecipes() {
		LogHelper.fine("Dumping recipes");
		try {
			File fp = Configuration.createDumpFile();
			Writer recipeWriter = new FileWriter(fp);
			
			for (IRecipesModule module : modules) {
				for (IRecipesModule.IRecipeData recipe: module.getAllRecipes()) {
					try {
						recipeWriter.write(recipe.generateDescription());
						recipeWriter.write("\n");
					} catch (Exception ex) {
						LogHelper.severe("Cannot describe an object in module "+module.getPrefix());
					}
				}
			}
			
			recipeWriter.close();
		} catch (Exception ex) {
			LogHelper.severe("Exception raised during recipe dumping");
			ex.printStackTrace();
		}
	}
	
	private void dumpOreDictionary() {
		LogHelper.fine("Dumping ore dictionary...");
		String description = null;
		try {
			
			File fp = Configuration.createOreFile();
			Writer oreWriter = new FileWriter(fp);
			
			for (String oreName : OreDictionary.getOreNames()) {
				
				description = String.format("@%s!", oreName);

				for (ItemStack is : OreDictionary.getOres(oreName)) {
					IDHandler.updateMap(is);
					description += Formatter.getISDescription(is);
				}
				
				oreWriter.write(description + "\n");
			}
			oreWriter.close();
		} catch (Exception ex) {
			LogHelper.severe("Exception during dumping ore dictionary");
			ex.printStackTrace();
		}
	}
}
