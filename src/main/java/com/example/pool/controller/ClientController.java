package com.example.pool.controller;

import com.example.pool.model.Client;
import com.example.pool.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/pool/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllClients() {
        var clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return ResponseEntity.ok().build();
    }
}
