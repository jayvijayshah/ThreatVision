package com.nvidia.hackathon.ai.impl;

import org.json.JSONObject;

import com.nvidia.hackathon.ai.LLM;
import com.nvidia.hackathon.config.ReloadableProperties;
import com.nvidia.hackathon.enums.PayloadType;
import com.nvidia.hackathon.enums.RequestMethod;
import com.nvidia.hackathon.http.HTTPRequest;
import com.nvidia.hackathon.http.HTTPResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SarvamLLM extends LLM {

	@Override
	public HTTPResponse performChatCompletion() throws Exception {
		return null;
	}

	@Override
	public HTTPResponse performTranscription() throws Exception {
		String httpUrl = ReloadableProperties.getSarvamLLMTranscriptionAPI();
		JSONObject headers = new JSONObject();
		headers.put("api-subscription-key", ReloadableProperties.getSarvamLLMAPIKey());
		JSONObject payload = new JSONObject();
		payload.put("language_code", this.languageCode);
		payload.put("file", this.audioFilePath);
		payload.put("model", "saarika:v1");
		HTTPRequest httpRequest = new HTTPRequest(httpUrl, RequestMethod.POST, headers, PayloadType.FORMDATA,
				payload.toString());
		return httpRequest.execute();
	}

	@Override
	public HTTPResponse performTextToSpeech() throws Exception {
		String httpUrl = ReloadableProperties.getSarvamLLMTTSAPI();
		JSONObject headers = new JSONObject();
		headers.put("api-subscription-key", ReloadableProperties.getSarvamLLMAPIKey());
		HTTPRequest httpRequest = new HTTPRequest(httpUrl, RequestMethod.POST, headers, PayloadType.JSON,
				this.ttsPayload);
		return httpRequest.execute();
	}

	@Override
	public HTTPResponse performTranslation() throws Exception {
		String httpUrl = ReloadableProperties.getSarvamLLMTranslateAPI();
		JSONObject headers = new JSONObject();
		headers.put("api-subscription-key", ReloadableProperties.getSarvamLLMAPIKey());
		HTTPRequest httpRequest = new HTTPRequest(httpUrl, RequestMethod.POST, headers, PayloadType.JSON,
				this.translationPayload);
		return httpRequest.execute();
	}

	@Override
	public HTTPResponse performVisionChatCompletion() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
