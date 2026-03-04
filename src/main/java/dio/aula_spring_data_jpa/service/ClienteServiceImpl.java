package dio.aula_spring_data_jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.aula_spring_data_jpa.model.Cliente;
import dio.aula_spring_data_jpa.model.Endereco;
import dio.aula_spring_data_jpa.repository.ClienteRepository;
import dio.aula_spring_data_jpa.repository.EnderecoRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ViaCepService viaCepService;

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isEmpty()) {
			return; // controller pode retornar 404
		}

		cliente.setId(id);
		salvarClienteComCep(cliente);
	}

	@Override
	public void deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			return; // controller pode retornar 404
		}
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		if (cliente == null) return;
		if (cliente.getEndereco() == null) {
			clienteRepository.save(cliente);
			return;
		}

		String cep = cliente.getEndereco().getCep();
		if (cep == null || cep.isBlank()) {
			clienteRepository.save(cliente);
			return;
		}

		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			// se o ViaCEP não retornar nada válido, evitamos salvar nulo
			if (novoEndereco == null || novoEndereco.getCep() == null) {
				return null;
			}
			return enderecoRepository.save(novoEndereco);
		});

		if (endereco != null) {
			cliente.setEndereco(endereco);
		}

		clienteRepository.save(cliente);
	}
}