package vitzli.recipedumper.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import vitzli.recipedumper.lib.Reference;

public class LogHelper {
	
	private static Logger vLogger = Logger.getLogger(Reference.MOD_ID);
	
	public static void init()
	{
		vLogger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level loglevel, Object object)
	{
		vLogger.log(loglevel, String.valueOf(object));
	}
	
	public static void severe(Object object)
	{
		log(Level.SEVERE, object);
	}
	
	public static void debug(Object object)
    {
        log(Level.INFO, String.format("[DEBUG] %s", String.valueOf(object)));
    }

    public static void warning(Object object)
    {
        log(Level.WARNING, object);
    }

    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    public static void config(Object object)
    {
        log(Level.CONFIG, object);
    }

    public static void fine(Object object)
    {
        log(Level.FINE, object);
    }

    public static void finer(Object object)
    {
        log(Level.FINER, object);
    }

    public static void finest(Object object)
    {
        log(Level.FINEST, object);
    }
}
