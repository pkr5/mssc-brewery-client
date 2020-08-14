package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.file.Path;
import java.util.UUID;

@Component
@ConfigurationProperties(value="sfg.customer",ignoreUnknownFields = false)
public class CustomerClient {

    public final RestTemplate restTemplate;
    private final String PATH = "/api/v1/customer/";
    private String apihost;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apihost + PATH + uuid.toString(),CustomerDto.class);
    }

    public URI postCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apihost + PATH, customerDto);
    }

    public void putCustomer(UUID uuid, CustomerDto customerDto){
        restTemplate.put(apihost + PATH + uuid,customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apihost + PATH + uuid);
    }
}
