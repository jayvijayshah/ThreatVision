package com.nvidia.hackathon.config;

import org.json.JSONObject;

public class ReloadableProperties {

	private ReloadableProperties() {

	}

	public static String getGroqLLMAPI() {
		return ReloadableCache.instance.get("groq.llm.api").trim();
	}

	public static String getGroqLLMTranscriptionAPI() {
		return ReloadableCache.instance.get("groq.llm.transcription.api").trim();
	}

	public static String getSarvamLLMTranscriptionAPI() {
		return ReloadableCache.instance.get("sarvam.llm.transcription.api").trim();
	}

	public static String getSarvamLLMTTSAPI() {
		return ReloadableCache.instance.get("sarvam.llm.tts.api").trim();
	}

	public static String getSarvamLLMTranslateAPI() {
		return ReloadableCache.instance.get("sarvam.llm.translate.api").trim();
	}

	public static String getTogetherLLMVisionAPI() {
		return ReloadableCache.instance.get("together.llm.vision.api").trim();
	}

	public static String getGroqLLMAPIKey() {
		return ReloadableCache.instance.get("groq.llm.api.key").trim();
	}

	public static String getSarvamLLMAPIKey() {
		return ReloadableCache.instance.get("sarvam.llm.api.key").trim();
	}

	public static String getTogetherLLMAPIKey() {
		return ReloadableCache.instance.get("together.llm.api.key").trim();
	}

	public static int getOkHttpConnectTimeoutInSeconds() {
		return Integer.parseInt(ReloadableCache.instance.get("okhttp.connect.timeout.in.seconds").trim());
	}

	public static int getOkHttpReadTimeoutInSeconds() {
		return Integer.parseInt(ReloadableCache.instance.get("okhttp.read.timeout.in.seconds").trim());
	}

	public static int getOkHttpWriteTimeoutInSeconds() {
		return Integer.parseInt(ReloadableCache.instance.get("okhttp.write.timeout.in.seconds").trim());
	}

	public static int getOkHttpMaxRequestsPerHost() {
		return Integer.parseInt(ReloadableCache.instance.get("okhttp.max.requests.per.host").trim());
	}

	public static int getInterceptorQueueBatchSize() {
		return Integer.parseInt(ReloadableCache.instance.get("interceptor.queue.batch.size").trim());
	}

	public static long getInterceptorQueueSleepTimeInMillis() {
		return Long.parseLong(ReloadableCache.instance.get("interceptor.queue.sleep.time.in.millis").trim());
	}

	public static int getInterceptorQueueNoOfThreads() {
		return Integer.parseInt(ReloadableCache.instance.get("interceptor.queue.no.of.threads").trim());
	}

	public static String getDefaultLLMProvider() {
		return ReloadableCache.instance.get("default.llm.provider").trim();
	}

	public static String getGroqLLMModel() {
		return ReloadableCache.instance.get("groq.llm.model").trim();
	}

	public static String getGroqWhisperModel() {
		return ReloadableCache.instance.get("groq.whisper.model").trim();
	}

	public static String getLLamaVisionPrompt() {
		return ReloadableCache.instance.get("llama.vision.prompt").trim();
	}

	public static String getVideosLocation() {
		return ReloadableCache.instance.get("videos.location").trim();
	}

	public static String getInferVerticalPrompt() {
		return ReloadableCache.instance.get("infer.vertical.prompt").trim();
	}

	public static String getVerticalPrompts() {
		return ReloadableCache.instance.get("vertical.prompts").trim();
	}

	public static JSONObject getAckMessages() {
		return new JSONObject(ReloadableCache.instance.get("ack.messages").trim());
	}

	public static long getIncomingProcessorSleepTimeInMillis() {
		return Long.parseLong(ReloadableCache.instance.get("incoming.processor.sleep.time.in.millis").trim());
	}

	public static int getIncomingProcessorBatchSize() {
		return Integer.parseInt(ReloadableCache.instance.get("incoming.processor.batch.size").trim());
	}

	public static int getIncomingProcessorNoOfThreads() {
		return Integer.parseInt(ReloadableCache.instance.get("incoming.processor.no.of.threads").trim());
	}

	public static long getSendMessageProcessorSleepTimeInMillis() {
		return Long.parseLong(ReloadableCache.instance.get("send.message.processor.sleep.time.in.millis").trim());
	}

	public static int getSendMessageProcessorBatchSize() {
		return Integer.parseInt(ReloadableCache.instance.get("send.message.processor.batch.size").trim());
	}

	public static int getSendMessageProcessorNoOfThreads() {
		return Integer.parseInt(ReloadableCache.instance.get("send.message.processor.no.of.threads").trim());
	}

	public static String getSendWhatsAppAPI() {
		return ReloadableCache.instance.get("send.whatsapp.api").trim();
	}

	public static String getSendSmsAPI() {
		return ReloadableCache.instance.get("send.sms.api").trim();
	}

	public static String getSendEmailAPI() {
		return ReloadableCache.instance.get("send.email.api").trim();
	}

	public static String getWhatsAppOneWayAccountUserId() {
		return ReloadableCache.instance.get("whatsapp.one.way.account.userid").trim();
	}

	public static String getWhatsAppOneWayAccountPassword() {
		return ReloadableCache.instance.get("whatsapp.one.way.account.password").trim();
	}

	public static String getWhatsAppTwoWayAccountUserId() {
		return ReloadableCache.instance.get("whatsapp.two.way.account.userid").trim();
	}

	public static String getWhatsAppTwoWayAccountPassword() {
		return ReloadableCache.instance.get("whatsapp.two.way.account.password").trim();
	}

	public static String getSMSAccountUserId() {
		return ReloadableCache.instance.get("sms.account.userid").trim();
	}

	public static String getSMSAccountPassword() {
		return ReloadableCache.instance.get("sms.account.password").trim();
	}

	public static String getEmailAccountUserId() {
		return ReloadableCache.instance.get("email.account.userid").trim();
	}

	public static String getEmailAccountPassword() {
		return ReloadableCache.instance.get("email.account.password").trim();
	}

	public static String getWhatsAppWABANumber() {
		return ReloadableCache.instance.get("whatsapp.waba.number").trim();
	}

	public static String getAudioFileDownloadLocation() {
		return ReloadableCache.instance.get("audio.file.download.location").trim();
	}

	public static String getTTSMp3FileUploadAPI() {
		return ReloadableCache.instance.get("tts.mp3.file.upload.api").trim();
	}

	public static String getMySQLClassForname() {
		return ReloadableCache.instance.get("mysql.class.for.name").trim();
	}

	public static String getHackthonDBJdbcUrl() {
		return ReloadableCache.instance.get("hackathon.db.jdbc.url").trim();
	}

	public static String getDbUsername() {
		return ReloadableCache.instance.get("db.username").trim();
	}

	public static String getDbPassword() {
		return ReloadableCache.instance.get("db.password").trim();
	}

	public static String getReminderWhatsAppMessageTemplateID() {
		return ReloadableCache.instance.get("reminder.whatsapp.message.template.id").trim();
	}

	public static String getReminderSMSMessageTemplateID() {
		return ReloadableCache.instance.get("reminder.sms.message.template.id").trim();
	}

	public static String getReminderSMSMessageTemplate() {
		return ReloadableCache.instance.get("reminder.sms.message.template").trim();
	}

	public static String getReminderEmailMessage() {
		return ReloadableCache.instance.get("reminder.email.message").trim();
	}

	public static String getVisionThreat() {
		return ReloadableCache.instance.get("vision.threat").trim();
	}

}
