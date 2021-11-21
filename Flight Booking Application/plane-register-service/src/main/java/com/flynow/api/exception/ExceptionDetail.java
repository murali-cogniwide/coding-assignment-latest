package com.flynow.api.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ExceptionDetail {
	
	/*
	 * exception occured time
	 */
	private LocalDateTime timestamp;
	
	/*
	 * Http Method GET, POST,etc.,
	 */
	private String method;
	
	/*
	 * Service path
	 */
	private String path;
	
	/*
	 * Http Status message
	 */
	private String statusMessage;
	
	private String exceptionClass;
	
	private String exceptionMsg;
	

}
