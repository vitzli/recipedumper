package vitzli.recipedumper.lib;

import java.util.Set;
import java.util.Collection;

import net.minecraft.item.ItemStack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.util.Map.Entry;
import java.util.Set;


public class IDHandler {
    
    private static SetMultimap<Integer, Integer> itemIds = HashMultimap.create();
    
    public static void updateMap(int itemId, int meta) {
        itemIds.put(itemId, meta);
    }
    
    public static void updateMap(ItemStack is) {
        if (is!=null) {
            updateMap(is.itemID, is.getItemDamage());
        }
    }
    
    public static SetMultimap<Integer,Integer> getMap() {
        return itemIds;
    }
    
    public static Set getKeys() {
        return itemIds.keySet();
    }
    
    public static Collection get(int key) {
        return itemIds.get(key);
    }
    
    public static Set<Entry<Integer,Integer>> getEntries() {
        return itemIds.entries();
    }
}
