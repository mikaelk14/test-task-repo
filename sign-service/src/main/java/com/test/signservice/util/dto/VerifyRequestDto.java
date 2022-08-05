package com.test.signservice.util.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class VerifyRequestDto {

    private String publicKey;
    private String message;
    private String algorithm;
}
