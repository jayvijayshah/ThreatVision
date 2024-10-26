package com.nvidia.hackathon.util;

import com.nvidia.hackathon.builder.send_message.SendMessage;
import com.nvidia.hackathon.config.ReloadableProperties;
import com.nvidia.hackathon.enums.Channel;
import com.nvidia.hackathon.queue.SendMessageProcessor;

public class NotificationUtil {

	public static void sendWhatsAppMessage(SendMessage sendMessage) {
		SendMessageProcessor.instance.addEntry(sendMessage);
	}

	public static SendMessage prepareWhatsAppMessage(long mobile, String msg) {

		SendMessage sendMessage = new SendMessage();
		sendMessage.setChannel(Channel.WHATSAPP.name());
		sendMessage.setUserid(ReloadableProperties.getWhatsAppOneWayAccountUserId());
		sendMessage.setPassword(ReloadableProperties.getWhatsAppOneWayAccountPassword());
		sendMessage.setMethod("sendmessage");
		sendMessage.setSend_to(String.valueOf(mobile));
		sendMessage.setMsg(msg);
		sendMessage.setMsg_type("text");
		return sendMessage;
	}

}
