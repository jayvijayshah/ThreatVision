package com.nvidia.hackathon.factory;

import com.nvidia.hackathon.ai.LLM;
import com.nvidia.hackathon.ai.impl.GroqLLM;
import com.nvidia.hackathon.ai.impl.SarvamLLM;
import com.nvidia.hackathon.ai.impl.TogetherLLM;

public class LLMFactory {

	public static final LLMFactory instance = new LLMFactory();

	public LLM getLLM(String llmProvider, String llmModel, String prompt) {
		if (llmProvider.trim().equalsIgnoreCase("groq")) {
			LLM llm = new GroqLLM();
			llm.setModel(llmModel);
			llm.setPrompt(prompt);
			return llm;
		}
		if (llmProvider.trim().equalsIgnoreCase("sarvam")) {
			LLM llm = new SarvamLLM();
			llm.setModel(llmModel);
			llm.setPrompt(prompt);
			return llm;
		}
		if (llmProvider.trim().equalsIgnoreCase("together")) {
			LLM llm = new TogetherLLM();
			llm.setModel(llmModel);
			llm.setPrompt(prompt);
			return llm;
		}
		return null;
	}
}
