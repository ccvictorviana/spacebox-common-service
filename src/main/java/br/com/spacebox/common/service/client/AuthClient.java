package br.com.spacebox.common.service.client;

import br.com.spacebox.common.model.response.UserResponse;
import br.com.spacebox.common.service.config.AuthClientConfiguration;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authClient", url = "${service.auth.host}", configuration = AuthClientConfiguration.class)
public interface AuthClient {
    @GetMapping("/users/")
    @Headers("Content-Type: application/json")
    UserResponse detail(@RequestHeader("Authorization") String bearerToken);
}