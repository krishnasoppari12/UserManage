package com.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse{

    //private int status;
    private String status1;
   // private String message;
    private List<ErrorDTO> errors;
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "ddMMyy hh:mm:ss")
   private Date timestamp;


}*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class APIResponse<T> {

    private String status;
    private List<ErrorDTO> errors;
    private T results;

}
