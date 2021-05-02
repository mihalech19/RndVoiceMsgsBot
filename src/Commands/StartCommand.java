package Commands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand{

	public StartCommand() {
		
	}

	public static SendMessage handle(Message message) {
		SendMessage answer = new SendMessage();
		answer.setChatId(message.getChatId().toString());
        answer.setText("������. ������� ����� ���� ������, ��� �� ��������� ��������� �������� �� ������ ����� "
        		+ "� ���� ������ � ������ �� ������� ��������� ��������� ���������. �����? - �� ����, ���� ������� :)");	
        return answer;
	}

}
