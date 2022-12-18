package com.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("암호화 테스트")
public class JasyptTest {

    @Disabled // 테스트 진행 시 주석 처리
    @Test
    @DisplayName("암호화/복호화 일치 여부")
    void jasyptTests() throws Exception {
        //given
        String password = "";
        final var encryptor = new PooledPBEStringEncryptor(); //multicore system에서 해독을 병렬처리하는 Encryptor
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        String content = ""; //암호화할 내용

        //when
        final var encryptedContent = encryptor.encrypt(content);
        System.out.println(encryptedContent);
        final var decryptContent = encryptor.decrypt(encryptedContent);

        //then
        assertThat(decryptContent).isEqualTo(content);
    }
}
