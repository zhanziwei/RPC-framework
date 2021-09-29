package com.zhanziwei.rpc.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "成功"),
    fail(505, "失败"),
    NOT_FOUND_METHOD(404, "未找到指定方法"),
    NOT_FOUND_CLASS(407, "未找到指定类");
    private final int code;
    private final String message;
}
