package com.usermanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String field;
    private String errorMessage;
   // @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "ddMMyy hh:mm:ss")
   // private LocalDateTime timestamp;
}*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String field;
    private String errorMessage;
}
