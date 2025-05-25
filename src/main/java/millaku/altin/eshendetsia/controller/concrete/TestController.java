package millaku.altin.eshendetsia.controller.concrete;

import millaku.altin.eshendetsia.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController extends BaseController {
    @GetMapping
    public String test() {
        return "Hello from /api/test";
    }
}
