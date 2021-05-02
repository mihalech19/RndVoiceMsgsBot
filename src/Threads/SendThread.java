package Threads;
import java.util.logging.Logger;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import Service.Bot;

public class SendThread implements Runnable {

	private static final Logger log = Logger.getLogger(SendThread.class.getName());
	
	private final Bot bot;

	@Override
	public void run() {
		log.info("Started message sender");
		while (!Thread.interrupted()) {
			Object message;
			try {
				message = bot.sendQueue.take();
				
				try {
					if (message instanceof SendMessage)
					{
						SendMessage sendmessage = (SendMessage) message;
						bot.execute(sendmessage);
						log.info("Send answer to ChatId : " + sendmessage.getChatId());
					}
					else if (message instanceof SendVoice)
					{
						SendVoice sendvoice = (SendVoice) message;
						bot.execute(sendvoice);
						log.info("Send answer to ChatId : " + sendvoice.getChatId());					
					}
					else
						log.warning("Unknown type of message to send");
							
				} catch (TelegramApiException e) {
					log.severe(e.getMessage());
				}
				
			} catch (InterruptedException e) {
				log.severe("Message sender was interrupted");
			}

		}
		log.info("Stopped message sender");
	}

	public SendThread(Bot _bot) {
		this.bot = _bot;

	}

}
