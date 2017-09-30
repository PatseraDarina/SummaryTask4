package ua.nure.patsera.periodicals.service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.nure.patsera.periodicals.bean.Captcha;
import ua.nure.patsera.periodicals.repository.CaptchaRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CaptchaService {
    private final int totalLength = 6;
    private final Random random = new Random();
    private final CaptchaRepository captchaRepository;
    private final int captchaHeight = 40;
    private final int captchaWidth = 150;
    private final Font font = new Font("Arial", Font.BOLD, 30);
    private final int circleNumber = 15;

    public CaptchaService(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    public void add(String key, Captcha value) {
        captchaRepository.add(key, value);
    }

    public Captcha get(String key) {
        return captchaRepository.get(key);
    }

    public boolean constains(String key) {
        return captchaRepository.contains(key);
    }

    public List<Captcha> getAll() {
        return new ArrayList<>(captchaRepository.getAll());
    }

    public Captcha generateAndWriteCaptchaToOutputStream(OutputStream outputStream) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(captchaWidth, captchaHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
        String captchaString = generateCaptchaString();
        String captchaId = generateCaptchaId();

        image.fillRect(0, 0, captchaWidth, captchaHeight);
        blurImage(image);
        image.setFont(font);
        printStringOnImage(image, captchaString);
        ImageIO.write(bufferedImage, "jpeg", outputStream);
        image.dispose();

        return new Captcha(captchaString, captchaId, bufferedImage);
    }

    private String generateCaptchaString() {
        return (Long.toString(Math.abs(random.nextLong()), 36)).substring(0, totalLength);
    }

    private String generateCaptchaId() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }

    private void blurImage(Graphics2D image) {
        for (int i = 0; i < circleNumber; i++) {
            image.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            int radius = (int) (Math.random() * captchaHeight / 2.0);
            int x = (int) (Math.random() * captchaWidth - radius);
            int y = (int) (Math.random() * captchaHeight - radius);
            image.fillRoundRect(x, y, radius * 2, radius * 2, 100, 100);
        }
    }

    private void printStringOnImage(Graphics2D image, String captchaString) {
        for (int i = 0; i < totalLength; i++) {
            image.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            int charYPosition;
            if (i % 2 == 0) {
                charYPosition = 24;
            } else {
                charYPosition = 35;
            }
            image.drawString(captchaString.substring(i, i + 1), 25 * i, charYPosition);
        }
    }
}
