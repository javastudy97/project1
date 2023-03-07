package org.project.omwp.chatbot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatBotController {

    @MessageMapping("/msgTo") // 실제 -> /app/msgTo
    @SendTo("/topic/serverGo")//stompClient.subscribe
    public BotMessage serverGo(ClientMessage message) throws Exception {
        Thread.sleep(500); // 0.5초 delay
        LocalDateTime today=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDay=today.format(formatter);
        String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));

        // 초기 메시지
        return new BotMessage(
                "<div class='flex center date' id='botMsgDate' >"+formattedDay+"</div>"+
                        "<div class='msg bot flex' id='botMsgBox'>"+
                        "<div class='message'>"+
                        "<div class='icon'>"+
                        "<img src='https://cdn-icons-png.flaticon.com/512/6873/6873405.png' style='width: 30px; height: 30px;'  th:alt=\"#{chat}\" />" +
                        "</div>"+
                        "<div class='part'>"+
                        "<p>안녕하세요, 멘토존 챗봇입니다.</p>" +
                        "<p>무엇을 도와드릴까요?</p>"+
                        "</div>" +
                        "<div class='part'>"+
                        "<div class='flex center menu' id='menu'>"+
                        "<div class='menu-item' onclick='menuclicked(this)'><span>상품문의</span></div>"+
                        "<div class='menu-item' onclick='menuclicked(this)'><span>주문문의</span></div>"+
                        "</div>"+
                        "</div>"+
                        "<div class='time'>"+
                        formattedtime+
                        "</div>"+
                        "</div>"+

                        "</div>");
    }


    @MessageMapping("/message")// 실제 -> /app/message
    @SendTo("/topic/message")//stompClient.subscribe
    public BotMessage message(ClientMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        LocalDateTime today=LocalDateTime.now();
        String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));

        String responseText=message.getContent()+"에 대한 답장입니다.";

//        if (responseText.equals("상품문의")) {
//
//        } else if (responseText.equals("결제문의")) {
//
//        } else if (responseText.equals(""))

        //클라이언트 메시지  /topic/message 요청시
        return new BotMessage(
                "<div class='msg bot flex' id='botMsgBox'>"+
                        "<div class='message'>"+
                        "<div class='icon'>"+
                        "<img src='https://cdn-icons-png.flaticon.com/512/6873/6873405.png' style='width: 30px; height: 30px;'  th:alt=\"#{chat}\" />" +
                        "</div>"+
                        "<div class='part'>"+
                        "<p>"+responseText+"</p>"+
                        "</div>"+
                        "<div class='time'>"+
                        formattedtime+
                        "</div>"+
                        "</div>"+
                        "</div>");
    }

}
