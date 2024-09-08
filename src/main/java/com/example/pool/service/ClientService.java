package com.example.pool.service;

import com.example.pool.exception.ClientNotFoundException;
import com.example.pool.model.Client;
import com.example.pool.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Map<String, Object>> getAllClients() {
        return clientRepository.findAll().stream().map(client -> {
            Map<String, Object> clientMap = new HashMap<>();
            clientMap.put("id", client.getId());
            clientMap.put("name", client.getName());
            return clientMap;
        }).collect(Collectors.toList());
    }

    public void updateClient(Client clientNew){
        Client client = clientRepository.findById(clientNew.getId()).
                orElseThrow(() -> new ClientNotFoundException("Client with ID " + clientNew.getId() + " not found"));

        client.setName(clientNew.getName());
        client.setPhone(clientNew.getPhone());
        client.setEmail(clientNew.getEmail());

        clientRepository.save(client);
    }
}
