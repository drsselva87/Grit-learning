package com.grit.learning.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {
	//private static final Logger logger = Logger.getLogger(ResponseGenerator.class);
	
	public ResponseEntity<Response> errorResponse(TransactionContext context, String errorMessage,HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", context.getToken());
		/*headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());*/
		headers.add("Content-Type", "application/json");
		Error error = new Error();
		error.setErrorCode(httpStatus.toString());
		error.setReason(errorMessage);
		Response response = new Response();
		response.setError(error);
		response.setStatus("failure");
		response.setCode("1");
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}
	
	public TransactionContext generateTransationContext(HttpHeaders httpHeaders) {

		TransactionContext context = new TransactionContext();
		
		if (httpHeaders.get("token") != null) {
			context.setToken(httpHeaders.get("token").get(0));
		} else {
			context.setToken(null);
		}
		/*if(null == httpHeaders) {
			context.setCorrelationId("demo");
			context.setApplicationLabel("demo");
			return context;
		}
		
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("demo");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("demo");
		}*/
		return context;
	}
	
	public ResponseEntity<Response> successGetResponse(TransactionContext context, String message, Object object, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", context.getToken());
		/*headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());*/
		headers.add("Content-Type", "application/json");
		Response response = new Response();
		response.setData(object);
		response.setMessage(message);
		response.setCode("0");
		response.setStatus("success");
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		//logger.debug("response class is " + Data.class);
		//logger.debug("response status is " + httpStatus.toString());
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	} 
	
}
