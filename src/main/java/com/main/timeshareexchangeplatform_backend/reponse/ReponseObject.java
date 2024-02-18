package com.main.timeshareexchangeplatform_backend.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReponseObject {
    private int code;
    private String message;
    private Object data;
}
