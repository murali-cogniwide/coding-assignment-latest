package com.flynow.api.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

//This class is to handle the exception globally across all the packages and uses Exception Detail Class to print the Exception details in response.
@ControllerAdvice
@Slf4j
public class FlyNowExceptionHandler extends ResponseEntityExceptionHandler {
	@ResponseBody
	@ExceptionHandler(UncategorizedSQLException.class)
	public ResponseEntity<?> handleUncategorizedException(final UncategorizedSQLException ex,final WebRequest request) {
		log.info(">> handleUncategorizedException");
		log.warn("UncategorizedSQLException");
		final ExceptionDetail detail = ExceptionDetail.builder().exceptionClass(ex.getClass().getName())
				.exceptionMsg(ex.getMessage()).timestamp(LocalDateTime.now())
				.method(getStatusRequest(request).getMethod())
				.path(getStatusRequest(request).getContextPath())
				.statusMessage(HttpStatus.NOT_FOUND.getReasonPhrase()).build();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ResponseBody
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleDataAccessException(final DataAccessException ex,final WebRequest request) {
		log.info(">> handleDataAccessException");
		log.warn("DataAccessException");
		final ExceptionDetail detail = ExceptionDetail.builder().exceptionClass(ex.getClass().getName())
				.exceptionMsg(ex.getMessage()).timestamp(LocalDateTime.now())
				.method(getStatusRequest(request).getMethod())
				.path(getStatusRequest(request).getContextPath())
				.statusMessage(HttpStatus.NOT_FOUND.getReasonPhrase()).build();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ResponseBody
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<?> handleIllegalStateException(final IllegalStateException ex,final WebRequest request) {
		log.info(">> handleIllegalStateException");
		log.warn("IllegalStateException");
		final ExceptionDetail detail = ExceptionDetail.builder().exceptionClass(ex.getClass().getName())
				.exceptionMsg(ex.getMessage()).timestamp(LocalDateTime.now())
				.method(getStatusRequest(request).getMethod())
				.path(getStatusRequest(request).getContextPath())
				.statusMessage(HttpStatus.NOT_FOUND.getReasonPhrase()).build();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(final IllegalArgumentException ex,final WebRequest request) {
		log.info(">> IllegalArgumentException");
		log.warn("IllegalArgumentException");
		final ExceptionDetail detail = ExceptionDetail.builder().exceptionClass(ex.getClass().getName())
				.exceptionMsg(ex.getMessage()).timestamp(LocalDateTime.now())
				.method(getStatusRequest(request).getMethod())
				.path(getStatusRequest(request).getContextPath())
				.statusMessage(HttpStatus.NOT_FOUND.getReasonPhrase()).build();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	
	@ResponseBody
	@ExceptionHandler(DataSourceLookupFailureException.class)
	public ResponseEntity<?> handleDataSourceLookupFailureException(final DataSourceLookupFailureException ex,final WebRequest request) {
		log.info(">> handleInvalidDataException");
		log.warn("DataSourceLookupFailureException");
		ex.printStackTrace();
		final ExceptionDetail detail = ExceptionDetail.builder().exceptionClass(ex.getClass().getName())
				.exceptionMsg(ex.getMessage()).timestamp(LocalDateTime.now())
				.method(getStatusRequest(request).getMethod())
				.path(getStatusRequest(request).getContextPath())
				.statusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(final Exception ex,final WebRequest request) {
		log.info(">> handleGenericException");
		log.warn("Exception");
		ex.printStackTrace();
		final ExceptionDetail detail = ExceptionDetail.builder().exceptionClass(ex.getClass().getName())
				.exceptionMsg(ex.getMessage()).timestamp(LocalDateTime.now())
				.method(getStatusRequest(request).getMethod())
				.path(getStatusRequest(request).getContextPath())
				.statusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private HttpServletRequest getStatusRequest(WebRequest request) {
		if(request instanceof ServletWebRequest) {
			HttpServletRequest httpServletRequest = ((ServletWebRequest)request).getNativeRequest(HttpServletRequest.class);
			return httpServletRequest;
		}
		return null;
	}
}
