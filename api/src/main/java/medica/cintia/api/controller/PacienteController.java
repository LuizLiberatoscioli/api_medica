package medica.cintia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medica.cintia.api.paciente.DadosAtualizacaoPaciente;
import medica.cintia.api.paciente.DadosCadastroPaciente;
import medica.cintia.api.paciente.DadosListagemPaciente;
import medica.cintia.api.paciente.Paciente;
import medica.cintia.api.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
    private PacienteRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
		repository.save(new Paciente(dados));
	}
	
	@GetMapping
	public Page<DadosListagemPaciente> listar(@PageableDefault(size= 10 ,sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemPaciente::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
	    var paciente = repository.getReferenceById(dados.id());
	    paciente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
	    var paciente = repository.getReferenceById(id);
	    paciente.inativar();
	}
	
	
	
}
