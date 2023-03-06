package org.project.omwp.chatbot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BotMessage {
    // 봇 메세지 => 클라이언트의 메세지에 응답하기 위한 메세지
    private String message;

}
