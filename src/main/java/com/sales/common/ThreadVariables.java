package com.sales.common;

import com.sales.domain.auth.Constant;
import lombok.Getter;
import lombok.Setter;

public class ThreadVariables {

    public static ThreadLocal<ThreadVariables> threadLocal =
            ThreadLocal.withInitial(ThreadVariables::new);

    private int logRowNumberInThisThread;

    @Getter
    @Setter
    private String userId;

    @Getter
    private Constant.ROLE role;

    @Getter
    @Setter
    private String sessionId;

    public ThreadVariables() {
        this.logRowNumberInThisThread = 0;
        this.userId = "";
        this.role = Constant.ROLE.ROLE_GUEST;
        this.sessionId = "";
    }

    public int getLogRowNumberInThisThread() {
        return this.logRowNumberInThisThread++;
    }

    public void setRole(String role) {
        this.role = Constant.ROLE.valueOf(role);
    }
}
