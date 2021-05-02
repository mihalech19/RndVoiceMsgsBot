package Commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpCommand {
	
	public static SendMessage handle(Message message)
	{
		SendMessage answer = new SendMessage();
		answer.setChatId(message.getChatId().toString());
        answer.setText("1) ��� ����� ��������� ���� ��������� �������� � ���� - ������ ��������� ��������� ���������.\n"
        		+ "2) ������� /random ������ ��������� ��������� �������� �� ����.\n"
        		+ "3) � ������� ������� /count ��� ������� ��� ������� ���������� ����������� ���������.\n"
        		+ "4) �������������!");	
        return answer;
	}

}
