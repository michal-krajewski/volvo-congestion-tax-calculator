package pl.byteit.volvodemo.taxcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

	@LocalServerPort
	protected int port;

	private RestTemplate restTemplate;

	@BeforeEach
	void setup() {
		restTemplate = new RestTemplateBuilder()
				.rootUri("http://localhost:" + port)
				.build();
	}

	protected <T> T post(String endpoint, Object body, Class<T> responseBody) {
		return restTemplate.exchange(endpoint, POST, new HttpEntity<>(body), responseBody).getBody();
	}

}
