package com.afroze.projectmanagement.company.api.security;

public class Permissions {
    private Permissions(){}

    public static final String READ_COMPANY = "SCOPE_read:company";
    public static final String WRITE_COMPANY = "SCOPE_write:company";
    public static final String UPDATE_COMPANY = "SCOPE_update:company";
    public static final String DELETE_COMPANY = "SCOPE_delete:company";
}
