package dio.aula_spring_data_jpa.service;

import dio.aula_spring_data_jpa.model.Cliente;

public interface ClienteService {

	Iterable<Cliente> buscarTodos();
	Cliente buscarPorId(Long id);
	
	void inserir(Cliente cliente);
	void atualizar(Long id, Cliente cliente);
	void deletar(Long id);
}
