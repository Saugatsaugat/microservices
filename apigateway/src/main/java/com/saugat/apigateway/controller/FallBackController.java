package com.saugat.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {
    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport(){
        return Mono.just("An error occurred. Please try after some time, if the issue persists contact support team.");
    }
}
