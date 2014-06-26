package vitzli.recipedumper.lib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import vitzli.recipedumper.lib.Reference;
import vitzli.recipedumper.RecipeDumper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import net.minecraft.util.ChatMessageComponent;

public class CommandRPDumper extends CommandBase {

	RecipeDumper recipedumper;

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof ICommand) {
			return this.compareTo((ICommand) obj);
		} else {
			return 0;
		}
	}

	public CommandRPDumper() {
		this.recipedumper = RecipeDumper.instance;
	}

	@Override
	public String getCommandName() {
		return Reference.COMMAND_RD;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/" + getCommandName()
				+ " dumps recipes, items and ore dictionary";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (astring.length == 0 || astring[0].equals(Reference.COMMAND_HELP)) {
			icommandsender.sendChatToPlayer(ChatMessageComponent
					.createFromText("vitzli's recipe dumper command"));
			icommandsender.sendChatToPlayer(ChatMessageComponent
					.createFromText(" - " + Reference.COMMAND_HELP
							+ " - prints this text"));
			icommandsender.sendChatToPlayer(ChatMessageComponent
					.createFromText(" - " + Reference.COMMAND_ALL
							+ " - dumps everything"));
			icommandsender.sendChatToPlayer(ChatMessageComponent
					.createFromText(" - " + Reference.COMMAND_OREDICT
							+ " - dumps ore dictionary"));
			icommandsender.sendChatToPlayer(ChatMessageComponent
					.createFromText(" - " + Reference.COMMAND_ITEMRECIPES
							+ " - dumps recipes"));
			icommandsender.sendChatToPlayer(ChatMessageComponent
					.createFromText(" - " + Reference.COMMAND_FLUIDS
							+ " - dumps fluid registry"));
			return;
			
		} else {
			if (astring[0].equals(Reference.COMMAND_ALL)) {
				// dump recipes
				recipedumper.recipemanager.cmdDumpAll();
				icommandsender
						.sendChatToPlayer(ChatMessageComponent
								.createFromText("vitzli's recipe dumper: everything dumped"));
				return;
			}
			if (astring[0].equals(Reference.COMMAND_OREDICT)) {
				// dump ore dictionary
				recipedumper.recipemanager.cmdDumpOreDict();
				icommandsender
						.sendChatToPlayer(ChatMessageComponent
								.createFromText("vitzli's recipe dumper: ore dictionary dumped"));
				return;
			}
			if (astring[0].equals(Reference.COMMAND_ITEMRECIPES)) {
				// dump item recipes
				recipedumper.recipemanager.cmdDumpItemRecipes();
				icommandsender
						.sendChatToPlayer(ChatMessageComponent
								.createFromText("vitzli's recipe dumper: items dumped"));
				return;
			}
			if (astring[0].equals(Reference.COMMAND_FLUIDS)) {
				// dump fluid registry
				recipedumper.recipemanager.cmdDumpFluids();
				icommandsender
						.sendChatToPlayer(ChatMessageComponent
								.createFromText("vitzli's recipe dumper: fluid registry dumped"));
				return;
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		if (icommandsender instanceof EntityPlayer
				|| icommandsender instanceof MinecraftServer) {
			return true;
		} else
			return false;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {
		ArrayList<String> options = new ArrayList<String>();
		options.add(Reference.COMMAND_HELP);
		options.add(Reference.COMMAND_ALL);
		options.add(Reference.COMMAND_OREDICT);
		options.add(Reference.COMMAND_ITEMRECIPES);
		options.add(Reference.COMMAND_FLUIDS);
		return options;
	}

}
