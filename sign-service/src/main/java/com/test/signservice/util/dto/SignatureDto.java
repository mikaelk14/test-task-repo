package com.test.signservice.util.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignatureDto {

    private String publicKey;
    private String message;
    private String signature;
    private String algorithm;

}
