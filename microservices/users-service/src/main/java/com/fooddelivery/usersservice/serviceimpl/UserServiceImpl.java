package com.fooddelivery.usersservice.serviceimpl;

import java.nio.CharBuffer;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.usersservice.dto.CredentialDTO;
import com.fooddelivery.usersservice.dto.UserDto;
import com.fooddelivery.usersservice.exceptions.AppException;
import com.fooddelivery.usersservice.mapper.UserMapper;
import com.fooddelivery.usersservice.model.User;
import com.fooddelivery.usersservice.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import com.fooddelivery.usersservice.repository.UserRepository;

@Service 
public class UserServiceImpl implements UserService{
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper; 
    
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
	
	@Override
	public UserDto registration(UserDto newUser) {
		
		User user=new User(newUser);
		user.setPassword(newUser.getPassword());
		user.setRole("CUSTOMER"); 
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		return userMapper.toUserDtoWithToken(userRepository.save(user), createToken(user));
	}	
	
	
	@Override
	public UserDto findByUsername(String username) {  
		User ret = userRepository.findByUsername(username);
		if(ret == null) {
			return null;
		}
		return userMapper.toUserDtoWithToken(ret, null);
	}

	@Override
	public UserDto findByEmail(String email) {  
		User ret = userRepository.findByEmail(email);
		if(ret == null) {
			return null;
		}
        return userMapper.toUserDtoWithToken(ret, null);
	}

	@Override
	public UserDto findById(String id) {
		User ret = userRepository.findById(id).get();
		if(ret == null) {
			return null;
		}
		return userMapper.toUserDtoWithToken(ret, null);
	}
	
	public UserDto validateToken(String token) {
		
		
		 String username = getUsernameFromToken(token);
	        Optional<User> userOptional = Optional.of(userRepository.findByUsername(username));

	        if (userOptional.isEmpty()) {

	            throw new AppException("User not found", HttpStatus.NOT_FOUND);
	        }

	        User user = userOptional.get();
	        if(user == null) {
	        	return null;
	        }
	        return userMapper.toUserDtoWithToken(user, createToken(user));
	}
	
	
    public String getUsernameFromToken(String token) {
        String username;

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            username = null;
        }

        return username;
    }
    
    private Claims getAllClaimsFromToken(String token) { 
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("somesecret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception e) {
            claims = null;
        } 

        return claims;
    }

   private String createToken(User user) { 
       Date now = new Date();
       Date validity = new Date(now.getTime() + 3600000); // 1 hour

       return Jwts.builder()
               .setIssuer("user-service")
               .setSubject(user.getUsername())
               .claim("role",user.getRole())
               .setAudience("web")
               .setIssuedAt(new Date())
               .setExpiration(validity)
               .signWith(SignatureAlgorithm.HS512, "somesecret").compact();
   }

	@Override
	public String login(CredentialDTO credentialsDto) {
		 	User user = userRepository.findByUsername(credentialsDto.getUsername()); 
		 	
		 	if(user == null) {
		 		return null;
		 	}
	        if (credentialsDto.getPassword().equals(user.getPassword())) {
	             return createToken(user);
	         }

	        //throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	        return null;
	}



}
