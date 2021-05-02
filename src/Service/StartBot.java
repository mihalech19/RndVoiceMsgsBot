package Service;
import java.io.IOException;
import java.util.logging.LogManager;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import Threads.RecieveThread;
import Threads.SendThread;

public class StartBot {

	public static void main(String[] args) {
		  try {
	            LogManager.getLogManager().readConfiguration(
	                    StartBot.class.getResourceAsStream("/logging.properties"));
	        } catch (IOException e) {
	            System.err.println("Could not setup logger configuration: " + e.toString());
	        }
		  
		try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Bot RndVoiceMsgBot = new Bot();
            botsApi.registerBot(RndVoiceMsgBot);
            
            Thread recieveThread = new Thread(new RecieveThread(RndVoiceMsgBot));
            recieveThread.setDaemon(true);
            recieveThread.start();
            
            Thread sendThread = new Thread(new SendThread(RndVoiceMsgBot));
            sendThread.setDaemon(true);
            sendThread.start();

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
	}

