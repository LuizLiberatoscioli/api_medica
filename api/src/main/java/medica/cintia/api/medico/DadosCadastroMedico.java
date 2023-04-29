package medica.cintia.api.medico;

import medica.cintia.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
		String nome, 
		String email, 
		String crm, 
		Especialidade especialidade, 
		DadosEndereco endereco) {

}
