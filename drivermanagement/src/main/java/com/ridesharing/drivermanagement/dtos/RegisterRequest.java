package com.ridesharing.drivermanagement.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
}
