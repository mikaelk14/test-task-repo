package com.test.signservice.util;

import com.test.signservice.util.dto.SignatureDto;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

@Service
public class EncodingService {
    private static final String SPEC = "secp256r1";
    private static final String ALGORITHM = "SHA256withECDSA";

    private KeyPair keyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ECGenParameterSpec spec = new ECGenParameterSpec(SPEC);
        KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance("EC");
        kpGenerator.initialize(spec, new SecureRandom());
        return kpGenerator.generateKeyPair();
    }

    public SignatureDto signSignature(byte [] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidAlgorithmParameterException {
        KeyPair keyPair = keyPair();

        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(keyPair.getPrivate());
        signature.update(message);
        byte [] signatureBytes = signature.sign();
        String pubKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String sign = Base64.getEncoder().encodeToString(signatureBytes);

        return SignatureDto.builder()
                .algorithm(ALGORITHM)
                .message(new String(message))
                .publicKey(pubKey)
                .signature(sign)
                .build();
    }


}
