package app.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ResponseBody
@RequestMapping("/api")
/**
 * Kontrolleri baze qe sherben si klase prind per te gjithe kontrolloret e tjere.
 * Ofron metoda ndihmuese per trajtimin e HTTP responsave.
 */
public class BaseController {

    /**
     * Kthen nje pergjigje te suksesshme me te dhenat e dhena
     *
     * @param data Te dhenat qe do te kthehen
     * @return Pergjigje HTTP me status 200 OK
     */
    protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(new ApiResponse<>(true, data, null));
    }

    /**
     * Kthen nje pergjigje gabimi me mesazhin e dhene
     *
     * @param errorMessage Mesazhi i gabimit
     * @return Pergjigje HTTP me status 404 Not Found
     */
    protected <T> ResponseEntity<ApiResponse<T>> error(String errorMessage) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, null, errorMessage));
    }

    /**
     * Metode test per te verifikuar nese backend-i eshte ne funksionim
     *
     * @return Mesazh pershendetje
     */
    @GetMapping("/test")
    public String test(){
    	return "Pershendetje nga Backend";
    }

}
