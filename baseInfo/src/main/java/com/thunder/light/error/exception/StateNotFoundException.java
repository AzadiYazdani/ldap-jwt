package com.thunder.light.error.exception;

public class StateNotFoundException extends BaseException {

    public StateNotFoundException(Long id) {
        super(String.format("the post %d not found", id));
    }

    public StateNotFoundException() {
        super(String.format("No post found"));
    }

}
