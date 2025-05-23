package edu.unipr.eshendetsia.controller.concrete;

import edu.unipr.eshendetsia.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController extends BaseController {
    @SuppressWarnings("SameReturnValue")
    @GetMapping
    public String test() {
        return "Hello from /api/test";
    }
}
