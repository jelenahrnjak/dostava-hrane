package rs.ac.uns.acs.smpuos.GatewayServis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	

    private long id;
    private String login;
    private String token;
    
}



