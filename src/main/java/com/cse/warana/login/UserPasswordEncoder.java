package com.cse.warana.login;

import com.cse.warana.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Sashika
 * on Jan 06 0006, 2015.
 */
public class UserPasswordEncoder implements PasswordEncoder {

    @Autowired
    @Qualifier("cryptoService")
    private CryptoService cryptoService;

    @Override
    public String encode(CharSequence rawPassword) {
        return cryptoService.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (this.encode(rawPassword).equals(encodedPassword)) {
            return true;
        }
        return false;
    }
}
