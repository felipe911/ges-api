package br.com.ges.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ges.api.enums.StatusEnum;
import br.com.ges.api.exception.BusinessException;
import br.com.ges.api.model.Contrato;
import br.com.ges.api.model.Estagio;
import br.com.ges.api.repository.ContratoRepository;
import br.com.ges.api.repository.EstagioRepository;
import br.com.ges.api.wrapper.AssociarContratoWrapper;

@Service
public class ContratoService {

	private static final String CNE = "Contrato não encontrado.";
	private String mensagem = null;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	public String associarContrato(AssociarContratoWrapper associarContrato) {

		Contrato contrato = new Contrato();
		Estagio estagio = new Estagio();

		contrato = associarContrato.getContrato();
		contrato.setEmpresa(associarContrato.getEmpresa());
		estagio.setIdAluno(associarContrato.getAluno().getId());

		Contrato contratoSalvo = contratoRepository.save(contrato);
		Long id = contratoSalvo.getId();
		estagio.setIdContrato(id);
		estagio.setStatus(StatusEnum.ATIVO);
		estagioRepository.save(estagio);

		return this.mensagem = "Contrato associado com sucesso.";
	}

	public Contrato exibir(Long id) {
		return contratoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CNE));
	}

	public List<Contrato> listar() {
		return (List<Contrato>) contratoRepository.findAll();
	}

	public String deletar(Long id) throws BusinessException {
		Contrato contratoEncontrado = contratoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CNE));

		if (possuiEstagioAtivoNoContrato(contratoEncontrado)) {
			return this.mensagem;

		} else {
			try {
				contratoRepository.deleteById(id);
				this.mensagem = "Contrato deletado com sucesso.";
			} catch (Exception e) {
				// TODO: Criar Exception correta
			}
		}
		return this.mensagem;
	}

	private boolean possuiEstagioAtivoNoContrato(Contrato contratoEncontrado) {

		List<Estagio> estagios = estagioRepository.findAll();
		estagios.removeIf(c -> c.getIdContrato() != contratoEncontrado.getId());

		if (!estagios.isEmpty()) {
			for (Estagio estagio : estagios) {
				if (estagio.getStatus() == StatusEnum.ATIVO) {
					this.mensagem = "Não é possivel deletar um contrato com estágio ativo";
					return true;
				}
			}
		}
		return false;
	}

	public String atualizar(AssociarContratoWrapper associarContrato) {

		Contrato contrato = new Contrato();
		Estagio estagio = new Estagio();

		contrato = associarContrato.getContrato();
		contrato.setEmpresa(associarContrato.getEmpresa());
		estagio.setIdAluno(associarContrato.getAluno().getId());

		Contrato contratoSalvo = contratoRepository.save(contrato);
		Long id = contratoSalvo.getId();
		estagio.setIdContrato(id);
		estagio.setStatus(StatusEnum.ATIVO);
		estagioRepository.save(estagio);

		return this.mensagem = "Contrato atualizado com sucesso.";
	}
}
