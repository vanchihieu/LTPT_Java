package entities;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String messageText;
		
	public Message() {
	}
	
	public Message(String messageText) {
		
		this.messageText = messageText;
	}
	
	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "Message [messageText=" + messageText + "]";
	}
	
}