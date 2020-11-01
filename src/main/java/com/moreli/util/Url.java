package com.moreli.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Url {
	public static String decodeParam(String s) {
		return URLDecoder.decode(s, StandardCharsets.UTF_8);
	}	
}
