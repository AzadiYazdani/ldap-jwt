package com.thunder.light.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = 2583865165014770858L;

    private String username;
    private String password;
}