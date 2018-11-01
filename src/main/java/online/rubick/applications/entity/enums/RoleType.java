package online.rubick.applications.entity.enums;

import org.hibernate.validator.constraints.NotBlank;

public enum RoleType {
    ADMIN("1", "系统管理员", "1"),
    CMANAGER("2","公司管理员","2");
    String code;

    String message;

    String order;

    private RoleType(String code, String message, String order) {
        this.code = code;
        this.message = message;
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public static RoleType getEnumByCode(@NotBlank(message = "code不能为空") String code) {
        RoleType[] values = RoleType.values();
        for (RoleType roleType : values) {
            if (roleType.getCode().equals(code)) {
                return roleType;
            }
        }

        return null;
    }
}
