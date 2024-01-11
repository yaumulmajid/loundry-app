package com.codeapps.loundry.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class APIDataResponseDTO {

    protected Boolean success;

    protected List<String> errorCodes;

    protected List<String> errors;

    protected Object data;

    protected List<String> messageCodes;

    protected List<String> messages;

    public APIDataResponseDTO(Boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.errors = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.errorCodes = new ArrayList<>();
        this.messageCodes = new ArrayList<>();
    }

}
