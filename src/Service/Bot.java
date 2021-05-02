package Service;

import java.sql.Timestamp;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;

import Commands.*;

public class Bot extends TelegramLongPollingBot {

	String succesInsert = "Ваше голосовое послание было успешно сохранено :)";
	String failedInsert = "Упс, не получилось сохранить ваше голосовое послание. Возможно оно уже присутствует в базе.";

	public final LinkedBlockingQueue<Object> sendQueue = new LinkedBlockingQueue<>();
	public final LinkedBlockingQueue<Update> receiveQueue = new LinkedBlockingQueue<>();

	private static final Logger log = Logger.getLogger(Bot.class.getName());

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage()) {
			log.info("An update " + update.getUpdateId() + " was received from "
					+ update.getMessage().getFrom().getUserName());
			receiveQueue.add(update);
		}
	}

	public void handleCommand(Message message) {
		if (message.hasText()) {
			if (message.getText().equals("/start")) {
				sendQueue.add(StartCommand.handle(message));
				return;
			}
			if (message.getText().equals("/help")) {
				sendQueue.add(HelpCommand.handle(message));
				return;
			}

			if (message.getText().equals("/count")) {
				sendQueue.add(CountCommand.handle(message));
				return;
			}

			if (message.getText().equals("/random")) {
				SendMessage sendmessage = new SendMessage();
				sendmessage.setChatId(message.getChatId().toString());
				sendmessage.setText("Ищу послание для вас...");
				sendQueue.add(sendmessage);
				sendQueue.add(RandomCommand.handle(message));
				return;
			}
		}

		sendQueue.add(DefaultCommand.handle(message));

		return;

	}

	public void handleVoiceMsg(Message message) {
		SendMessage sendmessage = new SendMessage();

		sendmessage.setChatId(message.getChatId().toString());

		Voice voice = message.getVoice();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		VoiceMsg msg = new VoiceMsg(voice.getFileUniqueId(), voice.getFileId(), message.getFrom().getUserName(),
				timestamp);

		if (DBManager.Insert(msg) > 0) {
			sendmessage.setText(succesInsert);
			log.info("Message from " + message.getFrom().getUserName() + " was added to DB");
		} else {
			sendmessage.setText(failedInsert);
			log.warning("Message from " + message.getFrom().getUserName() + " was NOT added to DB");
		}

		sendQueue.add(sendmessage);

	}

	private final static String BotToken = System.getenv("BotToken");

	@Override
	public String getBotUsername() {

		return "RandomVoiceMessagesBot";
	}

	@Override
	public String getBotToken() {
		return BotToken;
	}

}