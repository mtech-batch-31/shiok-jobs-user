package com.mtech.sjmsuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SnsUpdateUserDto {

    private String accountUuid;
    private boolean seekingJob;
}