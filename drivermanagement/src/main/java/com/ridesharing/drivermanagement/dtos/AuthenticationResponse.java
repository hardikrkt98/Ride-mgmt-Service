package com.ridesharing.drivermanagement.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class AuthenticationResponse {
    String token;
}
