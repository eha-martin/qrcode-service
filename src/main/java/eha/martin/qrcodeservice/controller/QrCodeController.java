package eha.martin.qrcodeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QrCodeController {

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Service is up and running");
    }

    @GetMapping("/qrcode")
    public ResponseEntity<String> getQRCode() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body("Not implemented yet");
    }
}
