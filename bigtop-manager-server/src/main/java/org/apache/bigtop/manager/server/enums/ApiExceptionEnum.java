package org.apache.bigtop.manager.server.enums;

import lombok.Getter;
import org.apache.bigtop.manager.server.utils.MessageSourceUtils;

@Getter
public enum ApiExceptionEnum {

    NEED_LOGIN(10000, LocaleKeys.LOGIN_REQUIRED),
    USERNAME_OR_PASSWORD_REQUIRED(10001, LocaleKeys.LOGIN_ACCOUNT_REQUIRED),
    INCORRECT_USERNAME_OR_PASSWORD(10002, LocaleKeys.LOGIN_ACCOUNT_INCORRECT),
    USER_IS_DISABLED(10003, LocaleKeys.LOGIN_ACCOUNT_DISABLED),

    // Cluster Exceptions -- 11000 ~ 11999
    CLUSTER_NOT_FOUND(11000, LocaleKeys.CLUSTER_NOT_FOUND),

    // Host Exceptions -- 12000 ~ 12999
    HOST_NOT_FOUND(12000, LocaleKeys.HOST_NOT_FOUND),

    // Stack Exceptions -- 13000 ~ 13999
    STACK_NOT_FOUND(13000, LocaleKeys.STACK_NOT_FOUND),

    // Service Exceptions -- 14000 ~ 14999
    SERVICE_NOT_FOUND(14000, LocaleKeys.SERVICE_NOT_FOUND),

    // Component Exceptions -- 15000 ~ 15999
    COMPONENT_NOT_FOUND(15000, LocaleKeys.COMPONENT_NOT_FOUND),
    ;

    private final Integer code;

    private final LocaleKeys key;

    ApiExceptionEnum(Integer code, LocaleKeys key) {
        this.code = code;
        this.key = key;
    }

    public String getMessage() {
        return MessageSourceUtils.getMessage(key);
    }
}