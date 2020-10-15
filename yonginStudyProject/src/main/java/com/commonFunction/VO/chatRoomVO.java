package com.commonFunction.VO;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.commonFunction.VO.chatMessageVO.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class chatRoomVO {
	private String studyCode;
    private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();
    private HashMap<String, String> userList = new HashMap<String, String>();

    
    public static chatRoomVO create(String studyCode){
    	chatRoomVO chatRoom = new chatRoomVO();
        chatRoom.studyCode = studyCode;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, chatMessageVO chatMessage,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessage.getType() == MessageType.ENTER){
        	userList.put(session.getId(), chatMessage.getWriter());
            sessions.add(session);
            chatMessage.setMessage("STAT:[알림] "+chatMessage.getWriter() + "님이 입장하셨습니다.");
        }
        else if(chatMessage.getType() == MessageType.LEAVE){
        	userList.remove(session.getId());
            sessions.remove(session);
            chatMessage.setMessage("STAT:[알림] "+chatMessage.getWriter() + "님이 퇴장하셨습니다.");
        }
        else{
            chatMessage.setMessage("CHAT:"+chatMessage.getWriter() + " : " + chatMessage.getMessage());
        }
        send(chatMessage,objectMapper);
    }

    private void send(chatMessageVO chatMessage, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chatMessage.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
            
        }
        
        // 접속자 명단 전송
        if(chatMessage.getType() == MessageType.LEAVE || chatMessage.getType() == MessageType.ENTER) {
        	TextMessage userListMessage = new TextMessage(objectMapper.writeValueAsString("USER:"+userList.values()));
        	
        	for(WebSocketSession sess : sessions){
        		sess.sendMessage(userListMessage);
                
            }
        	
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

	public HashMap<String, String> getUserList() {
		return userList;
	}

	public void setUserList(HashMap<String, String> userList) {
		this.userList = userList;
	}
    
    
}
