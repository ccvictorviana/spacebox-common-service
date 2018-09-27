package br.com.spacebox.common.service.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class AuthClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499)
            throw new AuthorizationException();
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
