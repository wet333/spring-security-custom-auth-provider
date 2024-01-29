package online.awet.learning.customauthprovider.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> publicEndpoint() {
        Map<String, Object> responsePayload = new HashMap<>();
        responsePayload.put("message", "hi!, im a public endpoint");
        return ResponseEntity.ok(responsePayload);
    }

    @GetMapping("/private")
    public ResponseEntity<Map<String, Object>> privateEndpoint() {
        Map<String, Object> responsePayload = new HashMap<>();
        responsePayload.put("message", "hi!, im a private endpoint");
        return ResponseEntity.ok(responsePayload);
    }

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> adminEndpoint() {
        Map<String, Object> responsePayload = new HashMap<>();
        responsePayload.put("message", "hi!, im an admin endpoint");
        return ResponseEntity.ok(responsePayload);
    }
}
