package com.thunder.light.error.exception;

public class CityNotFoundException extends BaseException {

    public CityNotFoundException(Long id) {
        super(String.format("No cities were found for the state with id %d", id));
    }

    public CityNotFoundException() {
        super(String.format("No city found"));
    }

}
