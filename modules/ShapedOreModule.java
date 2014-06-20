package vitzli.recipedumper.modules;

import java.util.ArrayList;
import java.util.Iterator;

import vitzli.recipedumper.lib.Formatter;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class ShapedOreModule implements IRecipesModule{

    
    @Override
    public IRecipeData[] getAllRecipes() {
        ArrayList<IRecipeData> list = new ArrayList<IRecipeData>();
        
        Iterator it = CraftingManager.getInstance().getRecipeList().iterator();
        while(it.hasNext())
        {
            Object o = it.next();
            if(o instanceof ShapedOreRecipe)
            {
                list.add(new RecipeData((ShapedOreRecipe)o));
            }
        }
        
        return list.toArray(new IRecipeData[0]);
    }
    
    @Override
    public String getPrefix() {
        return "recipedumper:shapedore";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public class RecipeData implements IRecipeData
    {
        String description = null;
        ShapedOreRecipe sr;

        public RecipeData(ShapedOreRecipe sr) {
            this.sr = sr;
        }
    
        
        @Override
        public String generateDescription() {
            if(description!=null) return description;
            description = getPrefix()+"!";
            int width =  (Integer) ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, sr, 4);
            int height = (Integer) ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, sr, 5);
            Object[] items = ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, sr, 3);
            
            description += Formatter.getWHString(width, height);
            
            for(Object o : items)
            {
                if(o == null) {
                	description += "(None)";
                } else if (o instanceof ItemStack) {
                	description+=Formatter.getISDescription((ItemStack) o);
                } else {
                    description+=Formatter.getItemOreDescription(o);
                }
            }
            ItemStack result = sr.getRecipeOutput();
            description+="->"+Formatter.getISDescription(result);
            
            return description;
        }

    }
}
