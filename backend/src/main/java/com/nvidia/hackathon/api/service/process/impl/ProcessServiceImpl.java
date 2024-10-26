package com.nvidia.hackathon.api.service.process.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.nvidia.hackathon.api.service.process.ProcessService;
import com.nvidia.hackathon.builder.send_message.SendMessage;
import com.nvidia.hackathon.config.ReloadableProperties;
import com.nvidia.hackathon.enums.Channel;
import com.nvidia.hackathon.model.process.ProcessRequest;
import com.nvidia.hackathon.queue.SendMessageProcessor;

public class ProcessServiceImpl implements ProcessService {
	private static final Logger LOG = LogManager.getLogger(ProcessServiceImpl.class.getSimpleName());
	public static final ProcessServiceImpl instance = new ProcessServiceImpl();
	private static boolean alertSent = false;

	@Override
	public String process(ProcessRequest processRequest) throws Exception {
		JSONObject threatObject = new JSONObject(processRequest.detectThreat());
		if (threatObject.getInt("threat_level") >= 75) {
			if (!alertSent) {
				SendMessage sendMessage = new SendMessage();
				sendMessage.setChannel(Channel.WHATSAPP.name());
				sendMessage.setUserid(ReloadableProperties.getWhatsAppOneWayAccountUserId());
				sendMessage.setPassword(ReloadableProperties.getWhatsAppOneWayAccountPassword());
				sendMessage.setMethod("sendmessage");
				sendMessage.setSend_to("9967617170");
				sendMessage.setTemplate_id("7216456");
				Map<String, String> variables = new HashMap<>();
				variables.put("var1", String.valueOf(threatObject.getInt("threat_level")));
				variables.put("var2", threatObject.getJSONObject("crime_identified").getString("crime"));
				variables.put("var3", threatObject.getJSONObject("crime_identified").getString("description"));
				sendMessage.setVariables(variables);
				sendMessage.setMsg_type("text");
				SendMessageProcessor.instance.addEntry(sendMessage);
				alertSent = true;
			}

		}
		return processRequest.detectThreats().toString();
	}
}
