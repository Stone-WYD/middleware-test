package spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xh
 * @date 2025-01-16
 * @Description:
 */
@RestController
public class WebController {

    @GetMapping("/web/test")
    public String justTest() {
        return "just test";
    }
}
