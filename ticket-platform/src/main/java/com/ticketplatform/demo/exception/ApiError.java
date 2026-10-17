package com.ticketplatform.demo.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiError(
		int status, 
		String message,
		Map<String, String> fieldErrors,
		LocalDateTime timeStamp
		) {

}
