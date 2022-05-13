package kz.pine.domain;

public enum Role {
    EMPLOYEE, EMPLOYER;

    public String getAuthority() {
        return name();
    }
}
