package br.com.sistema.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.PerfilUsuario;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.enums.TipoPerfil;
import br.com.sistema.repository.UserProfileRepository;
import br.com.sistema.repository.UsuarioRepository;
import br.com.sistema.service.UsuarioService;
import br.com.sistema.util.GenerateHashPasswordUtil;
import br.com.sistema.util.TipoMensagem;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1214898444983560348L;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	Locale ptBR = new Locale("pt", "BR");
	
	@Autowired
	MessageSource messageSource;
	
	@Transactional
	public void create(Usuario usuario) throws BusinessException, ApplicationException {
		try {
			validarPreenchimentoNome(usuario.getUsername());
			validarPreenchimentoEmail(usuario.getEmail());
			validarComposicaoEmail(usuario.getEmail());
			validarPreenchimentoSenha(usuario.getPassword());
			validarPreenchimentoConfirmacaoSenha(usuario.getConfirmacaoSenha());
			validarComposicaoConfirmacaoSenha(usuario.getPassword(), usuario.getConfirmacaoSenha());
			validarUsuarioExistente(usuario);
	
			PerfilUsuario profile = new PerfilUsuario();
			profile.setTipoPerfil(TipoPerfil.ROLE_USER);
			usuario.setPerfisUsuario(new ArrayList<PerfilUsuario>());
	
			userProfileRepository.create(profile);
	
			usuario.getPerfisUsuario().add(profile);
			usuario.setDataCadastro(new Date());
			String passwordHash = GenerateHashPasswordUtil.generateHash(usuario.getPassword());
			usuario.setPassword(passwordHash);
	
			usuarioRepository.create(usuario);
		} catch (BusinessException ex) {
			throw new BusinessException(ex.getMessage(), ex, ex.getTipoMensagem(), messageSource.getMessage("create.error", null, ptBR));
		}
	}

	@Transactional
	public void update(Usuario u) throws BusinessException, ApplicationException {
		usuarioRepository.update(u);
	}

	@Transactional
	public void delete(Usuario u) throws BusinessException, ApplicationException {
		usuarioRepository.delete(u);
	}

	@Transactional(readOnly = true)
	public Usuario findByLogin(String username){
		return usuarioRepository.findByLogin(username);
	}

	

	
	@Transactional(readOnly = true)
	private void validarUsuarioExistente(Usuario usuario) throws BusinessException, ApplicationException{
		Usuario user = usuarioRepository.findByNameAndEmail(usuario.getUsername(), usuario.getEmail());
		if (user != null && user.getId() != null) {
			if (user.getUsername().equals(usuario.getUsername())) {
				throw new BusinessException(messageSource.getMessage("usuario.exist", null, ptBR), TipoMensagem.AVISO);
			}
		}
	}

	private void validarComposicaoEmail(String email) throws BusinessException {
		if (!isEmailValid(email)) {
			throw new BusinessException(messageSource.getMessage("email.invalido", null, ptBR), TipoMensagem.AVISO);
		}
	}

	private void validarComposicaoConfirmacaoSenha(String senha, String confirmacaoSenha) throws BusinessException {
		if (!senha.equals(confirmacaoSenha)) {
			throw new BusinessException(messageSource.getMessage("confirmPassword.invalid", null, ptBR), TipoMensagem.AVISO);
		}

	}

	private void validarPreenchimentoConfirmacaoSenha(String confirmacaoSenha) throws BusinessException {
		if (confirmacaoSenha == null || confirmacaoSenha.equals("")) {
			throw new BusinessException(messageSource.getMessage("confirmPassword.requided", null, ptBR), TipoMensagem.AVISO);
		}
	}

	private void validarPreenchimentoSenha(String password) throws BusinessException {
		if (password == null || password.equals("")) {
			throw new BusinessException(messageSource.getMessage("password.requided", null, ptBR), TipoMensagem.AVISO);
		}
	}

	private void validarPreenchimentoEmail(String email) throws BusinessException {
		if (email == null || email.equals("")) {
			throw new BusinessException(messageSource.getMessage("email.required", null, ptBR), TipoMensagem.AVISO);
		}
	}

	private void validarPreenchimentoNome(String userName) throws BusinessException {
		if (userName == null || userName.equals("")) {
			throw new BusinessException(messageSource.getMessage("username.required", null, ptBR), TipoMensagem.AVISO);
		}
	}

	private static boolean isEmailValid(String email) {
		if ((email == null) || (email.trim().length() == 0))
			return false;

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	@Transactional(readOnly = true)
	public List<Usuario> findAll() throws BusinessException, ApplicationException {
		return usuarioRepository.findAll();
	}

}