package com.convoy.model.Response;

/**
 * @Author: jojo
 * @Description: 返回状态码枚举
 * @Date: Created on 2019/4/11 21:41
 */
/**
 * @Author: zhaoone
 * @Description: 添加LOGIN_FAILED情况
 * @Date: Created on 2019/10/14
 */
public enum ResponseEnum {

    SUCCESS(200, "操作成功！"),
    ERROR(400, "error!"),

    NO_CONTENT(404, "请求的内容不存在"),
    REQUEST_ERROR(400, "请求错误"),
    RESOURCE_CAN_NOT_VISIT(403, "请求的资源不允许访问"),
    INNER_SERVER_ERROR(500, "内部服务器错误"),
    ERROR_PARAM(403, "参数错误"),
    CAN_NOT_FIND_FILE(404, "找不到该文件"),
    UNIMPLEMENTED_SERVICE(501, "未实现的接口服务"),
    MEDIA_TYPE_NOT_SUPPORT(403, "请求参数类型不支持"),

    USERNAME_EXIST(403, "用户名已存在"),
    PASSWORD_TOO_SHORT(403, "密码长度过短，请输入至少6位密码"),
    PASSWORD_CONFIRM_ERROR(403, "两次输入密码不一致"),
    LOGIN_FAILED(403, "用户名或者密码错误，犯人id错误"),
    DO_NOT_LOGIN(403, "您还未登陆，请先登陆"),
    INVALID_USER_TOKEN(403, "无效的token，请重新登陆"),
    EXPIRED_USER_TOKEN(403, "token已过期，请重新登陆"),
    ALREADY_LOGIN(403, "您已经在其它地点登陆，请重新登陆"),

    DEVICE_NOT_EXIST(404, "请求的设备不存在"),
    DEVICE_REGISTER_FAIL(400, "注册设备失败");

    private Integer code;
    private String message;
    
    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
