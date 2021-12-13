package com.manudev90.userapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FormatPasswordInvalidException extends RuntimeException 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public FormatPasswordInvalidException(String mensaje) {
        super(mensaje);
    }
}