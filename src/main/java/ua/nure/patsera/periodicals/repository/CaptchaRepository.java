package ua.nure.patsera.periodicals.repository;

import ua.nure.patsera.periodicals.bean.Captcha;

import java.util.List;

public interface CaptchaRepository {
    void add(String key, Captcha captcha);

    Captcha get(String key);

    boolean contains(String key);

    List<Captcha> getAll();
}
