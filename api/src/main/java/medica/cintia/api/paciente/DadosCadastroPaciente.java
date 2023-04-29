package medica.cintia.api.paciente;

import medica.cintia.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(
		String nome, String email, String telefone , String cpf, DadosEndereco endereco) {

}
