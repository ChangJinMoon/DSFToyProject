package com.toyproject.demo.domain.intvitecode;

import lombok.Data;

@Data
public class InviteCode {
    private String inviteCode;
    private String hostId;
    private String targetId;
}
