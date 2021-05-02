package Service;
import java.io.Serializable;
import java.sql.*;

public class VoiceMsg implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4264025677185437530L;

	public String getFileUniqueId() {
		return fileUniqueId;
	}

	public void setFileUniqueId(String fileUniqueId) {
		this.fileUniqueId = fileUniqueId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getSendtime() {
		return sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public VoiceMsg(String fileUniqueId, String fileId, String username, Timestamp sendtime) {
		super();
		this.fileUniqueId = fileUniqueId;
		this.fileId = fileId;
		this.username = username;
		this.sendtime = sendtime;
	}


	private String fileUniqueId;
	
	private String fileId;
	
	private String username;
	
    private Timestamp sendtime;
}
