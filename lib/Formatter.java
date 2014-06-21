package vitzli.recipedumper.lib;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Formatter {
    public static String getISDescription(ItemStack is) {
        if (is == null) {
            return "(None)";
        } else {
        	IDHandler.updateMap(is);
            return String.format("(%d:%d,%d)", is.itemID, is.getItemDamage(), is.stackSize);
        }
    }
    
    public static String getItemOreDescription(Object obj) {
        ArrayList<ItemStack> aList = (ArrayList<ItemStack>) obj;
        ItemStack is = aList.get(0);
        String outputName = null;
        for (String oreName : OreDictionary.getOreNames()) {
            for (ItemStack item: OreDictionary.getOres(oreName)) {
                if (item.itemID == is.itemID && item.getItemDamage() == is.getItemDamage()) {
                    outputName = oreName;
                }
            }
        }
        return String.format("(@%s,%d)", outputName, is.stackSize);
    }
    
    @SuppressWarnings("unused")
    public static String getISDumpString(int itemId, int meta) {
        ItemStack is = new ItemStack(itemId, 1, meta);
        if (is == null) {
            return String.format("*item!%d:%d not found", itemId, meta);
        }
        return String.format("recipedumper:item!%d:%d U=%s L=%s", is.itemID, is.getItemDamage(), getUnlocalizedName(is), getDisplayName(is));
    }
    
    public static String getWHString(int width, int height) {
        return String.format("(w=%d,h=%d)", width, height);
    }

    private static String getUnlocalizedName(ItemStack is) {
        String out = "None";
        try {
            out = is.getUnlocalizedName();
        } catch (Exception ex) {
            //
        }
        return out;
    }

    private static String getDisplayName(ItemStack is) {
        String out = "None";
        try {
            out = is.getDisplayName();
        } catch (Exception ex) {
            //
        }
        return out;     
    }

}
