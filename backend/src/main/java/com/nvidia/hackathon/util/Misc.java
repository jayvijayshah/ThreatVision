package com.nvidia.hackathon.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class Misc {

	private static String UNKNOWN = "unknown";

	public static int generateRandomNumber(int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException("Start must be less than or equal to end.");
		}
		Random random = new Random();
		return random.nextInt((end - start) + 1) + start;
	}

	public static String getCurrentTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return now.format(formatter);
	}

	public static void downloadFile(String fileURL, String destination) throws IOException {
		URL url = new URL(fileURL);
		// Open a readable channel from the URL
		try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(destination)) {
			fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		}
	}

	public static void convertBase64EncodedToWavFile(String base64EncodedString, String outputFilePath)
			throws IOException {
		byte[] audioBytes = Base64.getDecoder().decode(base64EncodedString);
		File outputFile = new File(outputFilePath);
		try (FileOutputStream fos = new FileOutputStream(outputFile)) {
			fos.write(audioBytes);
		}
	}

	public static String convertImageToBase64(String imagePath) {
		File file = new File(imagePath);
		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());
			return Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			return null;
		}
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
