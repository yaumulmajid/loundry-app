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

    protected List<String> errors;

    protected Object data;

    public APIDataResponseDTO(Boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.errors = new ArrayList<>();
    }

}
