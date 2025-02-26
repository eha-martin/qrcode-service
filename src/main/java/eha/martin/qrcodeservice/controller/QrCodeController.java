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
    @ResponseStatus(HttpStatus.OK)
    public void getHealth() {}

    @GetMapping("/qrcode")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void getQrCode() {}

}
