package Commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DefaultCommand {
	public static SendMessage handle(Message message)
	{
		SendMessage answer = new SendMessage();
		answer.setChatId(message.getChatId().toString());
        answer.setText("� ��� �� �������! �������������� �������� /help ��� �����������");	
        return answer;
	}
}
