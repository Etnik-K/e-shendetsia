package edu.unipr.eshendetsia.controller;

import edu.unipr.eshendetsia.controller.abstracts.BaseController;
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
