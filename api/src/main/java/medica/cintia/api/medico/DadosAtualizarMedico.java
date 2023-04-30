package medica.cintia.api.medico;

import jakarta.validation.constraints.NotNull;
import medica.cintia.api.endereco.DadosEndereco;

public record DadosAtualizarMedico(
		 	@NotNull
	        Long id,
	        String nome,
	        String telefone,
	        DadosEndereco endereco
	        ) {

}
