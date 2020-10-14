package com.commonFunction.VO;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.commonFunction.VO.chatMessageVO.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class chatRoomVO {
	private String studyCode;
    private Set<WebSocketSession> sessions = new HashSet<>();

    
    public static chatRoomVO create(String studyCode){
    	chatRoomVO chatRoom = new chatRoomVO();
        chatRoom.studyCode = studyCode;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, chatMessageVO chatMessage,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessage.getType() == MessageType.ENTER){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getWriter() + "¥‘¿Ã ¿‘¿Â«œºÃΩ¿¥œ¥Ÿ.");
        }
        else if(chatMessage.getType() == MessageType.LEAVE){
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getWriter() + "¥‘¿Ã ≈¿Â«œºÃΩ¿¥œ¥Ÿ.");
        }
        else{
            chatMessage.setMessage(chatMessage.getWriter() + " : " + chatMessage.getMessage());
        }
        System.out.println("chatMessage : "+chatMessage.getMessage());
        send(chatMessage,objectMapper);
    }

    private void send(chatMessageVO chatMessage, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chatMessage.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }

	public Set<WebSocketSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<WebSocketSession> sessions) {
		this.sessions = sessions;
	}

	public String getStudyCode() {
		return studyCode;
	}

	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
    
    
}
