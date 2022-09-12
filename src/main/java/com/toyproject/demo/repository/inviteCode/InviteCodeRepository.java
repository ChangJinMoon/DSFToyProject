package com.toyproject.demo.repository.inviteCode;

import com.toyproject.demo.domain.intvitecode.InviteCode;

public interface InviteCodeRepository {
    void saveInviteCode(InviteCode inviteCode);
    void getInviteCode(InviteCode inviteCode);
    void deleteInviteCode(InviteCode inviteCode);
}
