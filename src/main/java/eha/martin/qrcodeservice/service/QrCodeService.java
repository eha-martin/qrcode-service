package eha.martin.qrcodeservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class QrCodeService {

    private final QRCodeWriter qrCodeWriter = new QRCodeWriter();


    public BufferedImage generateQRCode(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);

        return image;
    }

    public BufferedImage generateQRCode(String contents, int size) {
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, size, size);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return bufferedImage;
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

}
