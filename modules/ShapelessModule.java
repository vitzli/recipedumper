package vitzli.recipedumper.modules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vitzli.recipedumper.lib.Formatter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapelessRecipes;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class ShapelessModule implements IRecipesModule {

	@Override
	public IRecipeData[] getAllRecipes() {
		ArrayList<IRecipeData> shapelessRecipesList = new ArrayList<IRecipeData>();

		Iterator it = net.minecraft.item.crafting.CraftingManager.getInstance()
				.getRecipeList().iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (o instanceof ShapelessRecipes) {
				shapelessRecipesList.add(new RecipeData((ShapelessRecipes) o));
			}
		}

		return shapelessRecipesList.toArray(new IRecipeData[0]);
	}

	@Override
	public String getPrefix() {
		return "recipedumper:shapeless";
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public class RecipeData implements IRecipeData {
		String description = null;
		ShapelessRecipes sr;

		public RecipeData(ShapelessRecipes sr) {
			this.sr = sr;
		}

		@Override
		public String generateDescription() {
			if (description != null)
				return description;
			description = getPrefix() + "!";
			List<ItemStack> items = (List<ItemStack>) ObfuscationReflectionHelper
					.getPrivateValue(ShapelessRecipes.class, sr, 1);
			for (ItemStack is : items) {
				description += Formatter.getISDescription(is);
			}
			ItemStack result = sr.getRecipeOutput();
			description += "->" + Formatter.getISDescription(result);
			return description;
		}

	}
}
