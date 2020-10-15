package com.commonFunction.Controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.commonFunction.VO.chatRoomVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class chatRoomRepository {
	private static Map<String, chatRoomVO> chatRoomMap;
	
	@PostConstruct
	private void init(){
		chatRoomMap = new LinkedHashMap<String, chatRoomVO>();
    }
	
	public static chatRoomVO createChatRoom(String studyCode){
		chatRoomVO chatRoomVO = com.commonFunction.VO.chatRoomVO.create(studyCode);
		chatRoomMap.put(chatRoomVO.getStudyCode(), chatRoomVO);
        return chatRoomVO;
    }
	
	public static chatRoomVO findRoomById(String studyCode){
		
		// 채팅방이 없는 경우 만들고 반환
		if(chatRoomMap.get(studyCode)==null) {
			chatRoomVO cv = createChatRoom(studyCode);
			return cv;
		}
		// 있는 경우 바로 반환
        return chatRoomMap.get(studyCode);
    }
	
	// 비정상적 소켓 종료 함수
	public static void removeChatRoomBySession(WebSocketSession session) throws Exception {
		for (Entry<String, chatRoomVO> entry : chatRoomMap.entrySet()) {
			chatRoomVO cv = entry.getValue();
			Set<WebSocketSession> sessions = cv.getSessions();
			boolean result = sessions.remove(session);
			
			if(result == true) {
				HashMap<String, String> userList = cv.getUserList();
				String userId = userList.get(session.getId());
				String message = "STAT:[알림] "+ userId + "님이 퇴장하셨습니다.";
				
				userList.remove(session.getId());
				
				ObjectMapper objectMapper = new ObjectMapper();
				
				TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message));
				TextMessage userListMessage = new TextMessage(objectMapper.writeValueAsString("USER:"+userList.values()));
				
				for(WebSocketSession sess : sessions){
					sess.sendMessage(textMessage);
					sess.sendMessage(userListMessage);
		        }
				
				break;
			}
        }
		
	}
}
