package Commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import Service.DBManager;

public class CountCommand{

	public static SendMessage handle(Message message) {
		SendMessage answer = new SendMessage();
		answer.setChatId(message.getChatId().toString());
		answer.setText("Всего в базе голосовых сообщений: " + String.valueOf(DBManager.Count()));
        return answer;
	}

}
