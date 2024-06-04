package com.threeaxislabs.ams.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {
    private Object data;
    private int status;
    private Object meta;
}
