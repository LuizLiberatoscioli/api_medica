package medica.cintia.api.paciente;

import jakarta.validation.Valid;
import medica.cintia.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
		Long id,
	    String nome,
	    String telefone,
	    @Valid 
	    DadosEndereco endereco) {
	
	
}
