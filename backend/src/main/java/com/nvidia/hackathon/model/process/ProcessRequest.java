package com.nvidia.hackathon.model.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import javax.ws.rs.FormParam;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.nvidia.hackathon.ai.LLM;
import com.nvidia.hackathon.config.ReloadableProperties;
import com.nvidia.hackathon.factory.LLMFactory;
import com.nvidia.hackathon.http.HTTPResponse;
import com.nvidia.hackathon.util.Misc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessRequest {

	@FormParam("name")
	private String name;
	@FormParam("frame_no")
	private int frame_no;
	private String outputFramePath;

	public String detectThreat() throws Exception {
		boolean success = generateImageFromFrame();
		if (success) {
			String prompt = ReloadableProperties.getLLamaVisionPrompt();
			LLM llm = LLMFactory.instance.getLLM("together", "meta-llama/Llama-3.2-11B-Vision-Instruct-Turbo", prompt);
			llm.setBase64EncodedImage(Misc.convertImageToBase64(this.outputFramePath));
			HTTPResponse httpResponse = llm.performVisionChatCompletion();
			if (httpResponse != null && httpResponse.isSuccessful()) {
				String response = httpResponse.getBodyAsString();
				if (StringUtils.isNotBlank(response)) {
					JSONObject llmResponseObject = new JSONObject(response);
					JSONObject responseObject = llmResponseObject.getJSONArray("choices").getJSONObject(0);
					String threatResponse = responseObject.getJSONObject("message").getString("content");
					try {
						JSONObject threatResponseObject = new JSONObject(threatResponse);
						return threatResponseObject.toString();
					} catch (JSONException e) {
						return threatResponse;
					}
				}
			}
		}
		return null;

	}

	public boolean generateImageFromFrame() throws Exception {
		String videoLocation = new StringBuilder(ReloadableProperties.getVideosLocation()).append(this.name).toString();
		this.outputFramePath = new StringBuilder("/opt/mumhack-videos/frames/")
				.append(this.name.substring(0, this.name.lastIndexOf("."))).append("_frame_")
				.append(System.currentTimeMillis()).append(".jpg").toString();
		String[] command = new String[] { "/opt/python/.venv/bin/python3", "/opt/python/frame_to_image.py",
				videoLocation, String.valueOf(this.frame_no), this.outputFramePath };
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Process process = processBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.trim().equalsIgnoreCase("success")) {
				return true;
			}
		}
		// Wait for the process to finish
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			System.err.println("Python script exited with code: " + exitCode);
			return false;
		}
		return false;
	}

}
