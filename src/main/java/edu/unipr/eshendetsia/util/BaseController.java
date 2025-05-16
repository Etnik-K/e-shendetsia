package edu.unipr.eshendetsia.util;

import edu.unipr.eshendetsia.http.response.ApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ResponseBody
@RequestMapping("/api")
public class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(new ApiResponse<>(true, data, null));
    }

    protected <T> ResponseEntity<ApiResponse<T>> error(String errorMessage, HttpStatusCode code) {
        return ResponseEntity.status(code)
                .body(new ApiResponse<>(false, null, errorMessage));
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test(){
    	return this.ok("Pershendetje nga Backend");
    }

}
