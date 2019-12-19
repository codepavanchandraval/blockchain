package com.pavan.util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import com.vaadin.ui.Notification;

public class IdGenerator {

	public static String getSHA256Hash(String data) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes("UTF-8"));
			return bytesToHex(hash);
		} catch (Exception ex) {
			Notification.show("Error while Creating Hash");
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}
}
