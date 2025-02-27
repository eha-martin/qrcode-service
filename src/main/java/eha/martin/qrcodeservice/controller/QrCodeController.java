package eha.martin.qrcodeservice.controller;

import eha.martin.qrcodeservice.service.QrCodeService;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api")
@Validated
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

    @GetMapping(value = "/qrcode")
    public ResponseEntity<BufferedImage> getQRCode(
            @RequestParam @Range(min = 150, max = 350, message = "Image size must be between 150 and 350 pixels") int size,
            @RequestParam @Pattern(regexp = "png|jpeg|gif", message = "Only png, jpeg and gif image types are supported") String type) {
        BufferedImage image = qrCodeService.generateQRCode(size);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/" + type))
                .body(image);
    }

}
