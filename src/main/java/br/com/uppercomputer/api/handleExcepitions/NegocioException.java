package br.com.uppercomputer.api.handleExcepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class NegocioException extends RuntimeException {
	
	public NegocioException(String message) {
		super(message);
	}

}
