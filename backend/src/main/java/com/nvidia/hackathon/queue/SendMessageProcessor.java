package com.nvidia.hackathon.queue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nvidia.hackathon.builder.send_message.SendMessage;
import com.nvidia.hackathon.config.ReloadableProperties;
import com.nvidia.hackathon.util.TomcatUtils;
import com.gupshup.perstQueue.queue.ControllablePersistentProcessor;

public class SendMessageProcessor extends ControllablePersistentProcessor<SendMessage> {
	public static final SendMessageProcessor instance = new SendMessageProcessor();
	private static final Logger LOG = LogManager.getLogger(SendMessageProcessor.class.getName());

	private SendMessageProcessor() {
		super(ReloadableProperties.getSendMessageProcessorSleepTimeInMillis(),
				ReloadableProperties.getSendMessageProcessorBatchSize(), true, true,
				ReloadableProperties.getSendMessageProcessorNoOfThreads(), true);
	}

	@Override
	protected String getProcessorName() {
		return new StringBuilder(TomcatUtils.getTomcatName()).append("-")
				.append(SendMessageProcessor.class.getSimpleName()).toString();
	}

	@Override
	protected void processEntries(List<SendMessage> sendMsgTuples) throws Exception {
		for (SendMessage sendMsgTuple : sendMsgTuples) {
			try {
				sendMsgTuple.send();
			} catch (Exception e) {
				LOG.error("Exception ", e);
			}
		}
	}
}