package com.heydieproject.restapimahasiswa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDto<T> {

    private List<String> message = new ArrayList<>();
    private T payload;
    private boolean status;
}
