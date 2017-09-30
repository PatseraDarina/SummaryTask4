package ua.nure.patsera.periodicals.bean;

import java.awt.image.BufferedImage;

public class Captcha {
    private String captchaString;
    private String captchaId;
    private BufferedImage image;

    public Captcha(String captchaString, String captchaId, BufferedImage image) {
        this.captchaString = captchaString;
        this.captchaId = captchaId;
        this.image = image;
    }

    public String getCaptchaString() {
        return captchaString;
    }

    public void setCaptchaString(String captchaString) {
        this.captchaString = captchaString;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
