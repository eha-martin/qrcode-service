package eha.martin.qrcodeservice.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class QrCodeService {

    public BufferedImage generateQRCode() {
        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 250, 250);

        return image;
    }

}
