package xyz.nat1an.notebot;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import xyz.nat1an.notebot.commands.NotebotInfoCommand;
import xyz.nat1an.notebot.commands.NotebotLoadCommand;
import xyz.nat1an.notebot.commands.NotebotPlayCommand;
import xyz.nat1an.notebot.commands.NotebotStopCommand;

public class ModRegistries {
    public static void registerModStuff() {
        ModRegistries.registerCommands();
        ModRegistries.registerEvents();
    }

    private static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(NotebotInfoCommand::register);
        ClientCommandRegistrationCallback.EVENT.register(NotebotLoadCommand::register);
        ClientCommandRegistrationCallback.EVENT.register(NotebotPlayCommand::register);
        ClientCommandRegistrationCallback.EVENT.register(NotebotStopCommand::register);
    }

    private static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(NotebotPlayer::onTick);
    }
}