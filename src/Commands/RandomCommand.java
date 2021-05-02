package Commands;

import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import Service.DBManager;
import Service.VoiceMsg;

public class RandomCommand {
	public static SendVoice handle(Message message) {
		
		SendVoice answer = new SendVoice();
		answer.setChatId(message.getChatId().toString());
		VoiceMsg voicemsg = DBManager.GetRandom();
		answer.setVoice(new InputFile().setMedia(voicemsg.getFileId()));	
        return answer;
	}

}
