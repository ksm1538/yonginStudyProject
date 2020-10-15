package com.commonFunction.Controller;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.commonFunction.Service.chatService;
import com.commonFunction.VO.chatMessageVO;
import com.commonFunction.VO.chatRoomVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EchoHandler extends TextWebSocketHandler {
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource(name="chatService")
	private chatService chatService;
	
	// afterConnectionEstablished : 웹소켓이 연결되면 호출되는 함수
    // 웹소켓이 연결 되는 것 = 프론트에서 웹소켓이 정확한 경로를 잡아 생성 되는 것
    @Override
    public void afterConnectionEstablished(WebSocketSession session)  throws Exception {
        
    }

    // 웹소켓 클라이언트가 텍스트 메세지 전송시 호출되는 메소드
    // WebSocketSession session : 전송 주체 정보가 담긴 세션
    // TextMessage message : 전송 받은 메세지 정보
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
    	String msg = message.getPayload();
        chatMessageVO chatMessageVO = objectMapper.readValue(msg,chatMessageVO.class);
        chatRoomVO chatRoomVO = chatRoomRepository.findRoomById(chatMessageVO.getStudyCode());
        
        chatMessageVO.setStudyCode(chatRoomVO.getStudyCode());

        // 메시지가 null 값이 아닐 경우 (입장 or 퇴장이 아닌 메시지 전송일 경우)
        if(chatMessageVO.getMessage() != null) {
        	chatService.insertChat(chatMessageVO);
        }
        
        chatRoomVO.handleMessage(session,chatMessageVO,objectMapper);
        
    }

    // afterConnectionClosed : 웹소켓이 연결이 종료되면 호출되는 함수
    // 웹소켓이 연결이 종료 = 세션 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	chatRoomRepository.removeChatRoomBySession(session);
    }
    
}