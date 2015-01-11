package org.qulix.dotabase.enums;

public enum Role {
    CARRY("carry"),
    MID("mid"),
    OFFLANE("offlane"),
    SUPPORT("support"),
    UNDEFINED("undefined");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role fromString(String role) {
        if (role != null) {
            if (role.equalsIgnoreCase(CARRY.value)) {
                return CARRY;
            }
            if (role.equalsIgnoreCase(MID.value)) {
                return MID;
            }
            if (role.equalsIgnoreCase(OFFLANE.value)) {
                return OFFLANE;
            }
            if (role.equalsIgnoreCase(SUPPORT.value)) {
                return SUPPORT;
            }
        }
        return UNDEFINED;
    }
}
