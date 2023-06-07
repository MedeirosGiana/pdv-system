package com.gm.pdv.dto;

import com.gm.pdv.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private List<String> messages;

    public ResponseDTO(String messages) {
        this.messages = Arrays.asList(messages);
    }
}
