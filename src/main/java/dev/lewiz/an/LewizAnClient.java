///  Мод написан студией LewizDev
///  Автор: Lewiz Dev (t.me/LewizDev)
///  t.me/LewizDev
///  discord.gg/27s7TMGeHy

package dev.lewiz.an;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LewizAnClient implements ClientModInitializer {

	public static final String MOD_ID = "lewizan";
	public static final Logger LOGGER = LoggerFactory.getLogger("LewizAn");

	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			for (int i = 1; i <= 30; i++) {
				final int number = i;
				dispatcher.register(ClientCommandManager.literal("an" + number)
						.executes(ctx -> {
							if (!AnarchyNavigator.get().start(number)) {
								ctx.getSource().sendError(Text.literal("Переход уже выполняется"));
								return 0;
							}
							return 1;
						}));
			}
		});

		ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> !AnarchyNavigator.get().onMessage(message.getString()));
		ClientTickEvents.END_CLIENT_TICK.register(client -> AnarchyNavigator.get().tick(client));
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> AnarchyNavigator.get().onJoin());
	}
}