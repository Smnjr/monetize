package br.com.sistema.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.dao.GenericDao;
import br.com.sistema.dao.UsuarioDao;
import br.com.sistema.exception.ApplicationException;
import br.com.sistema.exception.BusinessException;
import br.com.sistema.model.PerfilUsuario;
import br.com.sistema.model.Usuario;
import br.com.sistema.model.UsuarioVO;
import br.com.sistema.service.PerfilUsuarioService;
import br.com.sistema.service.UsuarioService;
import br.com.sistema.util.GenerateHashPasswordUtil;
import br.com.sistema.util.TipoMensagem;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioService, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1214898444983560348L;

	private UsuarioDao usuarioDao;

	private UsuarioServiceImpl() {

	}

	@Autowired
	private PerfilUsuarioService perfilUsuarioService;

	@Autowired
	public UsuarioServiceImpl(@Qualifier("usuarioDaoImpl") GenericDao<Usuario, Integer> genericDao) {
		super(genericDao);
		this.usuarioDao = (UsuarioDao) genericDao;
	}

	static final Integer USER = 1;

	Locale ptBR = new Locale("pt", "BR");

	@Autowired
	MessageSource messageSource;

	@Override
	@Transactional
	public void create(UsuarioVO usuarioVO) throws BusinessException, ApplicationException {
		try {

			Usuario usuario = getUsuario(usuarioVO);

			validarPreenchimentoNome(usuario.getUsername());
			validarPreenchimentoEmail(usuario.getEmail());
			validarComposicaoEmail(usuario.getEmail());
			validarPreenchimentoSenha(usuario.getPassword());
			validarPreenchimentoConfirmacaoSenha(usuario.getConfirmacaoSenha());
			validarComposicaoConfirmacaoSenha(usuario.getPassword(), usuario.getConfirmacaoSenha());

			PerfilUsuario profileUser = new PerfilUsuario();
			profileUser = getPerfilUser();
			usuario.setPerfilUsuario(profileUser);

			usuario.setDataCadastro(new Date());
			String passwordHash = GenerateHashPasswordUtil.generateHash(usuario.getPassword());
			usuario.setPassword(passwordHash);

			usuarioDao.save(usuario);
		} catch (BusinessException ex) {
			throw new BusinessException(ex.getMessage(), ex, ex.getTipoMensagem(), messageSource.getMessage("create.error", null, ptBR));
		}
	}

	private Usuario getUsuario(UsuarioVO usuarioVO) {
		Usuario usuario = new Usuario();
		usuario.setUsername(usuarioVO.getUsername());
		usuario.setEmail(usuarioVO.getEmail());
		usuario.setPassword(usuarioVO.getPassword());
		usuario.setConfirmacaoSenha(usuarioVO.getConfirmacaoSenha());
		return usuario;
	}

	@Override
	@Transactional
	public void update(Usuario u) throws ApplicationException {
		usuarioDao.update(u);
	}

	@Override
	@Transactional
	public void delete(Usuario u) throws ApplicationException {
		usuarioDao.delete(u);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByLogin(String username){
		return usuarioDao.findByLogin(username);
	}

	@Override
	@Transactional(readOnly = true)
	public void validarUsername(String userName) throws BusinessException, ApplicationException {
		Usuario user = usuarioDao.findByLogin(userName);
		if (user != null) {
			throw new BusinessException(messageSource.getMessage("usuario.exist", null, ptBR), TipoMensagem.AVISO);
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
		if ((email == null) || (email.trim().length() == 0)) {
			return false;
		}

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() throws ApplicationException {
		return usuarioDao.findAll();
	}

	private PerfilUsuario getPerfilUser() throws ApplicationException {
		return perfilUsuarioService.find(USER);
	}

}