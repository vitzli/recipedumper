package vitzli.recipedumper.modules;

import java.util.ArrayList;
import java.util.Iterator;

import vitzli.recipedumper.lib.Formatter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ShapelessOreModule implements IRecipesModule{

    
    @Override
    public IRecipeData[] getAllRecipes() {
        ArrayList<IRecipeData> shapelessOreList = new ArrayList<IRecipeData>();
        
        Iterator it = CraftingManager.getInstance().getRecipeList().iterator();
        while(it.hasNext())
        {
            Object o = it.next();
            if(o instanceof ShapelessOreRecipe)
            {
                shapelessOreList.add(new RecipeData((ShapelessOreRecipe)o));
            }
        }
        
        return shapelessOreList.toArray(new IRecipeData[0]);
    }
    

    @Override
    public String getPrefix() {
        return "recipedumper:shapelessore";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public class RecipeData implements IRecipeData
    {
        String description = null;
        ShapelessOreRecipe sr;

        public RecipeData(ShapelessOreRecipe sr) {
            this.sr = sr;
        }      
        
        
        @Override
        public String generateDescription() {
            if(description!=null) return description;
            
            description = getPrefix()+"!";
            ArrayList items = ObfuscationReflectionHelper.getPrivateValue(ShapelessOreRecipe.class, sr, 1);
            for(Object o : items)
            {
                if(o instanceof ItemStack)
                {
                    description+= Formatter.getISDescription((ItemStack)o);
                }
                else
                {
                    description+=Formatter.getItemOreDescription(o);
                }
                
            }
            ItemStack result = sr.getRecipeOutput();
            description+="->"+Formatter.getISDescription(result);
            
            return description;
        }
        
    }
}
