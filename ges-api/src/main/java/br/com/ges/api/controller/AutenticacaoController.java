//package br.com.ges.api.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.ges.api.dto.TokenDto;
//import br.com.ges.api.model.Usuario;
//import br.com.ges.api.security.TokenService;
//
//@RestController
//@RequestMapping("/auth")
//public class AutenticacaoController {
//	
//	@Autowired
//	private AuthenticationManager authManager;
//	
//	@Autowired
//	private TokenService tokenService;
//	
//	@PostMapping
//	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid Usuario usuario){
//		UsernamePasswordAuthenticationToken dadosLogin = usuario.converter();
//		
//		try {
//			Authentication authentication = authManager.authenticate(dadosLogin);
//			String token = tokenService.gerarToken(authentication);
//
//			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
//			
//		} catch (AuthenticationException e) {
//			return ResponseEntity.badRequest().build();
//		}
//		
//
//		
//	}
//
//}
