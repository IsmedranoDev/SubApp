package com.example.SubApp.controllers;

import com.example.SubApp.models.ClienteModel;
import com.example.SubApp.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteModel> getClientes(){

        return this.clienteService.getClientes();
    }


    @PostMapping("/registroCliente")
    public ClienteModel saveCliente(@RequestBody ClienteModel cliente){

        return this.clienteService.saveCliente(cliente);
    }


    @GetMapping(path="/{id}")
    public Optional<ClienteModel> getClienteById(@PathVariable int id){

        return this.clienteService.getById(id);
    }

    @GetMapping(path = "/nombre/{nombre}")
    public List<ClienteModel> getClienteByName(@PathVariable String nombre){
        return this.clienteService.getByName(nombre);
    }

    @PutMapping(path="/{id}")
    public ClienteModel updateClienteById(@RequestBody ClienteModel request,@PathVariable int id){
        return this.clienteService.updateById(request, id);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteClienteById(@PathVariable("id") int id){
        boolean ok = this.clienteService.deleteCliente(id);

        if(ok){
            return "Cliente con usuario " + id + " ha sido borrado";

        }else {
            return "Erro al borrar al cliente";
        }
    };

}
