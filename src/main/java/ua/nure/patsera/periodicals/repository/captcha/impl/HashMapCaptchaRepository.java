package ua.nure.patsera.periodicals.repository.captcha.impl;

import ua.nure.patsera.periodicals.bean.Captcha;
import ua.nure.patsera.periodicals.repository.CaptchaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapCaptchaRepository implements CaptchaRepository {
    private final Map<String, Captcha> captchaMap = new HashMap<>();

    @Override
    public void add(String key, Captcha captcha) {
        synchronized (this.captchaMap) {
            this.captchaMap.put(key, captcha);
        }
    }

    @Override
    public Captcha get(String key) {
        return captchaMap.get(key);
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public List<Captcha> getAll() {
        return new ArrayList<>(captchaMap.values());
    }
}
