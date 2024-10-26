package com.nvidia.hackathon.ai.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvidia.hackathon.ai.LLM;
import com.nvidia.hackathon.builder.chat_completion.ChatCompletion;
import com.nvidia.hackathon.builder.chat_completion.Messages;
import com.nvidia.hackathon.builder.chat_completion.ResponseFormat;
import com.nvidia.hackathon.config.ReloadableProperties;
import com.nvidia.hackathon.enums.PayloadType;
import com.nvidia.hackathon.enums.RequestMethod;
import com.nvidia.hackathon.http.HTTPRequest;
import com.nvidia.hackathon.http.HTTPResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TogetherLLM extends LLM {

	@Override
	public HTTPResponse performChatCompletion() throws Exception {
		return null;
	}

	@Override
	public HTTPResponse performTranscription() throws Exception {
		return null;
	}

	@Override
	public HTTPResponse performTextToSpeech() throws Exception {
		return null;
	}

	private ChatCompletion constructVisionChatCompletionObject() {
		List<Messages> allMessages = new ArrayList<>();
		Messages message = new Messages();
		message.setRole("user");
		JSONArray contentArray = new JSONArray();
		JSONObject contentObject = new JSONObject();
		contentObject.put("type", "text");
		contentObject.put("text", this.prompt);
		contentArray.put(contentObject);

		JSONObject contentObject2 = new JSONObject();
		contentObject2.put("type", "image_url");
		JSONObject imageUrlObject = new JSONObject();
		imageUrlObject.put("url", new StringBuilder("data:image/jpeg;base64,").append(this.base64EncodedImage))
				.toString();
		contentObject2.put("image_url", imageUrlObject);
		contentArray.put(contentObject2);
		message.setContent(contentArray.toString());
		allMessages.add(message);
		ResponseFormat response_format = new ResponseFormat();
		response_format.setType("json_object");
		ChatCompletion.Builder chatCompletionBuilder = new ChatCompletion.Builder(this.model, allMessages)
				.maxTokens(1024).topP(1.0).temperature(this.temperature).setN(1);
		return chatCompletionBuilder.build();
	}

	@Override
	public HTTPResponse performTranslation() throws Exception {
		return null;
	}

	@Override
	public HTTPResponse performVisionChatCompletion() throws Exception {
		String httpUrl = ReloadableProperties.getTogetherLLMVisionAPI();
		JSONObject headers = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		headers.put("Authorization", ReloadableProperties.getTogetherLLMAPIKey());
		HTTPRequest httpRequest = new HTTPRequest(httpUrl, RequestMethod.POST, headers, PayloadType.JSON,
				mapper.writeValueAsString(constructVisionChatCompletionObject()));
		return httpRequest.execute();
	}

}
