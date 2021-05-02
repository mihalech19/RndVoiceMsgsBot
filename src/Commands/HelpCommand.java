package Commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpCommand {
	
	public static SendMessage handle(Message message)
	{
		SendMessage answer = new SendMessage();
		answer.setChatId(message.getChatId().toString());
        answer.setText("1) Бот может сохранить ваше голосовое послание в базе - просто отправьте голосовое сообщение.\n"
        		+ "2) Команда /random выдаст случайное голосовое послание из базы.\n"
        		+ "3) С помощью команды /count бот выведет вам текущее количество сохраненных сообщений.\n"
        		+ "4) Развлекайтесь!");	
        return answer;
	}

}
