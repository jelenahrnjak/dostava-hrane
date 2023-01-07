package rs.ac.uns.acs.smpuos.GatewayServis.kontroler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Mono;
import rs.ac.uns.acs.smpuos.GatewayServis.dto.UserDto;
import rs.ac.uns.acs.smpuos.GatewayServis.servis.UserService;

@RestController
public class GatewayServisKontroler {
	
	//@Autowired
	//@LoadBalanced
	//private RestTemplate restTemplate;

	@RequestMapping("/korisnik-servis-circuit-breaker")
	public Mono<String> korisnikServisCircuitBreaker() {
		return Mono.just("Кориснички сервис биће ускоро доступан.");
	}
	
	@PostMapping("/register")
	public String resgisterUser(@RequestBody UserDto user) {
	    
		RestTemplate restTemplate = new RestTemplate();
		
		String response = restTemplate.postForObject(
			    "http://localhost:8081/register", user,
			    String.class);
		return response;
	}
	

}
