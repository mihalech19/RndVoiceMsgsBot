package Threads;
import java.util.logging.Logger;

import org.telegram.telegrambots.meta.api.objects.Update;

import Service.Bot;

public class RecieveThread implements Runnable {

	private final Bot bot;
	
	private static final Logger log = Logger.getLogger(RecieveThread.class.getName());

	@Override
	public void run() {
		log.info("Started message reciever");
		while (!Thread.interrupted()) {
			Update update;
			try {

				update = bot.receiveQueue.take();
				log.info("Message " + update.getUpdateId() + " was received for processing");
				
				if (update.getMessage().hasVoice())
					bot.handleVoiceMsg(update.getMessage());
				else
					bot.handleCommand(update.getMessage());

			} catch (InterruptedException e) {
				log.severe("Message reciever was interrupted");
			}

		}
		
		log.info("Stopped message reciever");
	}

	public RecieveThread(Bot _bot) {
		this.bot = _bot;

	}

}
