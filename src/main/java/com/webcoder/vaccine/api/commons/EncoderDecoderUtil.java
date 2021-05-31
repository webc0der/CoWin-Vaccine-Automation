package com.webcoder.vaccine.api.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
public class EncoderDecoderUtil {

    public String encodeSHA256Hex (String text) {
        return DigestUtils.sha256Hex(text);
    }

    public String base64Decode (String text) {
        return new String(Base64.getDecoder().decode(text));
    }
}
