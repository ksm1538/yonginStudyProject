package com.commonFunction.Controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import com.commonFunction.VO.chatRoomVO;

@Repository
public class chatRoomRepository {
	private static Map<String, chatRoomVO> chatRoomMap;
	
	@PostConstruct
	private void init(){
		chatRoomMap = new LinkedHashMap<>();
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
	
	public static void removeChatRoomBySession(WebSocketSession session) {
		for (Entry<String, chatRoomVO> entry : chatRoomMap.entrySet()) {
			chatRoomVO cv = entry.getValue();
			Set<WebSocketSession> sessions = cv.getSessions();
			boolean result = sessions.remove(session);
			
			if(result == true) {
				break;
			}
        }
		
	}
}
