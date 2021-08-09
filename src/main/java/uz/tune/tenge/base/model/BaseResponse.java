package uz.tune.tenge.base.model;

import java.util.List;
import java.util.UUID;

public class BaseResponse<T> {

    private T data;
    private List<UUID> errorRef;
    private List<String> errorMessage;
    private Long timestamp;

    public BaseResponse() {
    }

    public static <T> BaseResponse<T> of(T data){
        return new BaseResponse<>(data);
    }

    public static <T> BaseResponse<T> of(T data, List<UUID> errorRef, List<String> errorMessage){
        return new BaseResponse<>(data, errorRef, errorMessage);
    }

    public static <T> BaseResponse<T> of(List<UUID> errorRef, List<String> errorMessage){
        return new BaseResponse<>(errorRef, errorMessage);
    }

    public BaseResponse(T data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public BaseResponse(T data, List<UUID> errorRef, List<String> errorMessage) {
        this.data = data;
        this.errorRef = errorRef;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    public BaseResponse(List<UUID> errorRef, List<String> errorMessage) {
        this.errorRef = errorRef;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public List<UUID> getErrorRef() {
        return errorRef;
    }

    public BaseResponse<T> setErrorRef(List<UUID> errorRef) {
        this.errorRef = errorRef;
        return this;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public BaseResponse<T> setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public BaseResponse<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
