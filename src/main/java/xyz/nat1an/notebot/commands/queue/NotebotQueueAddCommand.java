package xyz.nat1an.notebot.commands.queue;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;
import xyz.nat1an.notebot.NotebotPlayer;
import xyz.nat1an.notebot.suggestions.SongSuggestionProvider;

import static xyz.nat1an.notebot.Notebot.mc;

public class NotebotQueueAddCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> clientCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess) {
        clientCommandSourceCommandDispatcher.register(
            ClientCommandManager.literal("notebot")
                .then(ClientCommandManager.literal("queue")
                    .then(ClientCommandManager.literal("add")
                        .then(ClientCommandManager.argument("song", StringArgumentType.greedyString())
                            .suggests(new SongSuggestionProvider())
                            .executes(NotebotQueueAddCommand::run)
                        )
                    )
                )
        );
    }

    private static int run(CommandContext<FabricClientCommandSource> context) {
        NotebotPlayer.queue.add(context.getArgument("song", String.class));

        mc.player.sendMessage(
            Text.literal("§6Added §a" + context.getArgument("song", String.class) + "§6 to the queue.")
        );

        return 1;
    }
}
