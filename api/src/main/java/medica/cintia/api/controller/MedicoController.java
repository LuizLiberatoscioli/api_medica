package medica.cintia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import medica.cintia.api.medico.DadosAtualizarMedico;
import medica.cintia.api.medico.DadosCadastroMedico;
import medica.cintia.api.medico.DadosListagemMedico;
import medica.cintia.api.medico.Medico;
import medica.cintia.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		repository.save(new Medico(dados));
	}
	
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size= 10 ,sort = {"nome"}) Pageable paginacao){
		//return repository.findAll(paginacao).map(DadosListagemMedico::new);
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	/*  Exclusao
	 * @DeleteMapping("/{id}")
	 * 
	 * @Transactional public void excluir(@PathVariable Long id) {
	 * repository.deleteById(id); }
	 */
	
	// Exclusao logica
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
	}
	
}
