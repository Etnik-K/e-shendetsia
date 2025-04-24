package app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import app.dto.ApiResponse;

@RestController
public class BaseController {
	protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
		return ResponseEntity.ok(new ApiResponse<>(true, data, null));
	}

	protected ResponseEntity<ApiResponse<Object>> badRequest(String errorMessage) {
		return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, errorMessage));
	}

	protected ResponseEntity<ApiResponse<Object>> notFound(String errorMessage) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse<>(false, null, errorMessage));
	}

	@GetMapping("/test")
	public String test(){
		return "Pershendetje nga " + this.getClass().getSimpleName();
	}
}
