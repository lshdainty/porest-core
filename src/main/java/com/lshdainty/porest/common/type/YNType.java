package com.lshdainty.porest.common.type;

public enum YNType {
    Y("y", "Y", true),
    N("n", "N", false);

    private String lower;
    private String upper;
    private boolean bool;

    YNType(String lower, String upper,  boolean bool) {
        this.lower = lower;
        this.upper = upper;
        this.bool = bool;
    }

    public static boolean isY(YNType type) {
        return type.equals(YNType.Y);
    }

    public static boolean isN(YNType type) {
        return type.equals(YNType.N);
    }
}
