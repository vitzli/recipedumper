package vitzli.recipedumper.lib;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandHandler {
	
	public static void initCommand(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandRPDumper());
	}

}
