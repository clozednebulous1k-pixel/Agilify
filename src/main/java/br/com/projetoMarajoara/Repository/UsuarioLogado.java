package br.com.projetoMarajoara.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.projetoMarajoara.Model.ADM;
import br.com.projetoMarajoara.Model.Funcionario;
import br.com.projetoMarajoara.Model.Morador;

public class UsuarioLogado implements UserDetails{

	private ADM adm;
	private Funcionario fun; 
	private Morador mor;
	
	public UsuarioLogado(ADM adm) {
		this.adm = adm;
	}
	public UsuarioLogado(Funcionario fun) {
		this.fun = fun;
	}
	public UsuarioLogado(Morador mor) {
		this.mor = mor;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (adm != null) {
	        return List.of(new SimpleGrantedAuthority("ROLE_ADM"));
	    } else if (fun != null) {
	        return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
	    } else if (mor != null) {
	        return List.of(new SimpleGrantedAuthority("ROLE_MORADOR"));
	    }
	    return List.of();
	}

	@Override
	public String getPassword() {
		if (adm != null) return adm.getSenha();
		if (fun != null) return fun.getSenha();
		if (mor != null) return mor.getSenha();
		return null;
	}

	@Override
	public String getUsername() {
		if (adm != null) return adm.getEmail();
		if (fun != null) return fun.getEmail();
		if (mor != null) return mor.getEmail();
		return null;
	}

	
	
	public ADM getAdm() {
		return adm;
	}
	public void setAdm(ADM adm) {
		this.adm = adm;
	}
	public Funcionario getFun() {
		return fun;
	}
	public void setFun(Funcionario fun) {
		this.fun = fun;
	}
	public Morador getMor() {
		return mor;
	}
	public void setMor(Morador mor) {
		this.mor = mor;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
