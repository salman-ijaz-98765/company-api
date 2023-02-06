package com.afroze.projectmanagement.company.api.ui.model;

public class HttpResponseModel<T> {
    private final T data;
    private final boolean isError;
    private final String errorMessage;

    public T getData() {
        return data;
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    protected HttpResponseModel(T data, boolean isError, String errorMessage) {
        this.data = data;
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public static <T> HttpResponseModel<T> success(T data) {
        return new HttpResponseModel<>(data, false, "");
    }

    public static <T> HttpResponseModel<T> failure(T data, String errorMessage) {
        return new HttpResponseModel<>(data, true, errorMessage);
    }
}
