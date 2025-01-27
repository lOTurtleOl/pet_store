/**
 * 
 */
package pet.store.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RestControllerAdvice // marks class as primary handler for all controllers
@Slf4j
public class GlobalErrorHandler {

	@ExceptionHandler(NoSuchElementException.class) // specifies that the method handles this exception
	public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
		// Log the error
		log.error("NoSuchElementException: {}", ex.toString());
		
		// Return a map with a "message" key
		return Map.of("message", ex.toString());
	}
	
}
