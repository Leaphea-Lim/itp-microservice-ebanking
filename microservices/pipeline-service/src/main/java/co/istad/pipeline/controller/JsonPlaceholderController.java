package co.istad.pipeline.controller;

import co.istad.pipeline.client.JsonPlaceholderClient;
import co.istad.pipeline.client.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class JsonPlaceholderController {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    @GetMapping("/users")
    public List<UserResponse> getUser(){
        return jsonPlaceholderClient.getUser();
    }
}
