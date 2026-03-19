package br.com.projetoMarajoara.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projetoMarajoara.Model.ADM;
import br.com.projetoMarajoara.Model.Funcionario;
import br.com.projetoMarajoara.Model.Morador;
import br.com.projetoMarajoara.Repository.ADMRepository;
import br.com.projetoMarajoara.Repository.FuncionarioRepository;
import br.com.projetoMarajoara.Repository.MoradorRepository;
import br.com.projetoMarajoara.Repository.UsuarioLogado;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	FuncionarioRepository funcRepo;
	
	@Autowired
	ADMRepository admRepo;
	
	@Autowired
	MoradorRepository morRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		ADM adm = admRepo.findByEmail(email);
		Funcionario fun = funcRepo.findByEmail(email); 
		Morador mor = morRepo.findByEmail(email);	
		
		if( adm == null && fun == null && mor == null) {
			throw new UsernameNotFoundException("Usuario n encontrado");
		}
		
		if(mor != null) {
			return new UsuarioLogado(mor);
		} else if(fun != null) {
			return new UsuarioLogado(fun);			
		}
		return new UsuarioLogado(adm);
	}
}
