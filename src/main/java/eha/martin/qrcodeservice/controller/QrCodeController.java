package eha.martin.qrcodeservice.controller;

import eha.martin.qrcodeservice.service.QrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Service is up and running");
    }

    @GetMapping(value = "/qrcode", produces = "image/png")
    public ResponseEntity<BufferedImage> getQRCode() {
        BufferedImage image = qrCodeService.generateQRCode();
        return ResponseEntity.ok().body(image);
    }

}
