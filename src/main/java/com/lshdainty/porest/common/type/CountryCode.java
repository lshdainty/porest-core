package com.lshdainty.porest.common.type;

public enum CountryCode implements DisplayType {
    KR("KOR", "410", "Asia"),
    US("USA", "840", "America"),
    JP("JPN", "392", "Asia"),
    CN("CHN", "156", "Asia"),
    VN("VNM", "704", "Asia"),
    MY("MYS", "458", "Asia"),
    PL("POL", "616", "Europe");

    private static final String MESSAGE_KEY_PREFIX = "type.country.code.";
    private String alpha3;
    private String numeric;
    private String continent;

    CountryCode(String alpha3, String numeric, String continent) {
        this.alpha3 = alpha3;
        this.numeric = numeric;
        this.continent = continent;
    }

    @Override
    public String getMessageKey() {
        return MESSAGE_KEY_PREFIX + this.name().toLowerCase();
    }

    @Override
    public Long getOrderSeq() {
        return (long) this.ordinal();
    }

    public String getAlpha3() {
        return alpha3;
    }

    public String getNumeric() {
        return numeric;
    }

    public String getContinent() {
        return continent;
    }
}
