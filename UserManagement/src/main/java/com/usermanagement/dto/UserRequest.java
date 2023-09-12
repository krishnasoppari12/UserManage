package com.usermanagement.dto;

import com.usermanagement.model.UserDesignationInfo;
import com.usermanagement.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private UserDetails userDetails;
    private UserDesignationInfo userDesignationInfo;
}
