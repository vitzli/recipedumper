package vitzli.recipedumper.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

import vitzli.recipedumper.lib.Formatter;
import vitzli.recipedumper.lib.IDHandler;

public class ShapedRecipesModule implements IRecipesModule {

	@Override
	public IRecipeData[] getAllRecipes() {
		ArrayList<IRecipeData> shapedRecipesList = new ArrayList<IRecipeData>();

		for (Object obj: CraftingManager.getInstance().getRecipeList()) {
			if (obj instanceof ShapedRecipes) {
				shapedRecipesList.add(new RecipeData((ShapedRecipes) obj));
			}
		}
		
		return shapedRecipesList.toArray(new IRecipeData[0]);
	}

	@Override
	public String getPrefix() {
		return "recipedumper:shaped";
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public class RecipeData implements IRecipeData {
		
		String description = null;
		ShapedRecipes sr;

		public RecipeData(ShapedRecipes sr) {
			this.sr = sr;
		}
		
		@Override
		public String generateDescription() {
			if(this.description!=null) return this.description;
			
			description = getPrefix()+"!";
			int width = (Integer) ObfuscationReflectionHelper.getPrivateValue(ShapedRecipes.class, sr, 0);
			int height = (Integer) ObfuscationReflectionHelper.getPrivateValue(ShapedRecipes.class, sr, 1);
			ItemStack[] items = (ItemStack[]) ObfuscationReflectionHelper.getPrivateValue(ShapedRecipes.class, sr, 2);
			
			description += Formatter.getWHString(width, height);
			
			for (ItemStack is: items) {
				description += Formatter.getISDescription(is);
				IDHandler.updateMap(is);
			}
			
			ItemStack result = sr.getRecipeOutput();
			description += "->" + Formatter.getISDescription(result);
			IDHandler.updateMap(result);
			
			return this.description;
		}
		
		
	}
	
}
