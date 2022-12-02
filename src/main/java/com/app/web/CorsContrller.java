package com.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CorsContrller {

    @GetMapping("/cors")
    public String cors() {
        return "cors";
    }

}
