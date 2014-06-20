package vitzli.recipedumper.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

import vitzli.recipedumper.lib.Formatter;

public class SmeltingModule implements IRecipesModule{

    @Override
    public IRecipeData[] getAllRecipes() {
        ArrayList<IRecipeData> list = new ArrayList<IRecipeData>();
        
        Map<Integer, ItemStack> smeltingList = ObfuscationReflectionHelper.getPrivateValue(FurnaceRecipes.class, FurnaceRecipes.smelting(), 1);

        for(int id : smeltingList.keySet()) {
            ItemStack source = new ItemStack(id, 1, -1);
            ItemStack target = smeltingList.get(id);
            list.add(new RecipeData(source, target));
        }
        
        for(Entry<List<Integer>, ItemStack> smEntry: FurnaceRecipes.smelting().getMetaSmeltingList().entrySet()) {
        	List<Integer> sourceItemIDandMeta = smEntry.getKey();
        	ItemStack targetItemstack = smEntry.getValue();
        	if (sourceItemIDandMeta.size() == 2) {
        		list.add(new RecipeData(
        				new ItemStack(sourceItemIDandMeta.get(0), 1, sourceItemIDandMeta.get(1))
        				, targetItemstack));
        	}
        }
        
        return list.toArray(new IRecipeData[0]);
    }
    
    @Override
    public String getPrefix() {
        return "recipedumper:furnace";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public class RecipeData implements IRecipeData
    {
        String description = null;
        ItemStack source;
        ItemStack target;

        public RecipeData(ItemStack source, ItemStack target) {
            this.source = source;
            this.target = target;
        }
       
        @Override
        public String generateDescription() {
            if(description!=null) return description;
			description = getPrefix() + "!";
			description += Formatter.getISDescription(source);
			description += "->" + Formatter.getISDescription(target);
            
            return description;
        }
        
    }
}
