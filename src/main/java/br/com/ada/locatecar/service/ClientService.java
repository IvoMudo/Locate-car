package br.com.ada.locatecar.service;

import br.com.ada.locatecar.model.Client;
import br.com.ada.locatecar.model.ClientDTO;
import br.com.ada.locatecar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void createClient(Client client) {
//        Client clientDB = convertDTOtoEntity(clientDTO);
        clientRepository.save(client);
    }

    public List<Client> listarTodos() {
        return this.clientRepository.findAll();
    }

    public Page<Client> listarPaginado(Integer numeroPagina, Integer tamanhoPagina) {
        return this.clientRepository
                .findAll(PageRequest.of(numeroPagina, tamanhoPagina, Sort.by(Sort.Order.asc("id"))));
    }

    public void deleteClientByrId(Long id) {
        this.clientRepository.deleteById(id);
    }

    public Optional<Client> buscarVeiculoPorId(Long id) {
        return this.clientRepository.findById(id);
    }

    private Client convertDTOtoEntity(ClientDTO dto) {
        Client client = Client.builder()
                .name(dto.getName())
                .document(dto.getDocument())
                .phone(dto.getPhone())
                .type(dto.getType())
                .build();

        return client;
    }
}
