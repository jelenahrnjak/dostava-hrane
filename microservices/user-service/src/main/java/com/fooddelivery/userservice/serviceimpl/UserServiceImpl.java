package com.fooddelivery.userservice.serviceimpl;

import java.nio.CharBuffer;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.dto.CredentialDTO;
import com.fooddelivery.userservice.dto.UserDto;
import com.fooddelivery.userservice.exceptions.AppException;
import com.fooddelivery.userservice.mapper.UserMapper;
import com.fooddelivery.userservice.model.User;
import com.fooddelivery.userservice.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.fooddelivery.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private  PasswordEncoder passwordEncoder;
    
    @Autowired(required = false)
    private  UserMapper userMapper;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
	
	@Override
	public UserDto registration(UserDto newUser) {
		
		User user=new User(newUser);
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setRole("CUSTOMER"); 
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		return userMapper.toUserDtoWithToken(userRepository.save(user), createToken(user));
	}	
	
	
	@Override
	public UserDto findByUsername(String username) {  
        return userMapper.toUserDtoWithToken(userRepository.findByUsername(username), null);
	}

	@Override
	public UserDto findByEmail(String email) {  
        return userMapper.toUserDtoWithToken(userRepository.findByEmail(email), null);
	}

	@Override
	public User findById(String id) {
		return userRepository.findById(id).get();
	}
	
	public UserDto validateToken(String token) {
		 String username = Jwts.parser()
	                .setSigningKey(secretKey)
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	        Optional<User> userOptional = Optional.of(userRepository.findByUsername(username));

	        if (userOptional.isEmpty()) {

	            throw new AppException("User not found", HttpStatus.NOT_FOUND);
	        }

	        User user = userOptional.get();
	        return userMapper.toUserDtoWithToken(user, createToken(user));
	}

   private String createToken(User user) {
       Claims claims = Jwts.claims().setSubject(user.getUsername());//TODO: dodaj i rolu

       Date now = new Date();
       Date validity = new Date(now.getTime() + 3600000); // 1 hour

       return Jwts.builder()
               .setClaims(claims)
               .setIssuedAt(now)
               .setExpiration(validity)
//               .setRole()
               .signWith(SignatureAlgorithm.HS256, secretKey)
               .compact();
   }

	@Override
	public UserDto login(CredentialDTO credentialsDto) {
		 var user = Optional.of(userRepository.findByUsername(credentialsDto.getUsername()))
	                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

	        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
	             return userMapper.toUserDtoWithToken(user, createToken(user));
	         }

	        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}



}
