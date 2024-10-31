package com.example.SubApp.services;

import com.example.SubApp.models.ClienteModel;
import com.example.SubApp.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    IClienteRepository clienteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ClienteModel> getClientes(){

        return(List<ClienteModel>) clienteRepository.findAll();


    }


    public ClienteModel saveCliente(ClienteModel cliente){

        return clienteRepository.save(cliente);
    }


    public Optional<ClienteModel> getById(int id){

        return clienteRepository.findById(id);
    }

    public List<ClienteModel> getByName(String name){

        String sql = "SELECT id, nombre, apellidos, numero_buceos, certificaciones\n" +
                "FROM clientes\n" +
                "WHERE nombre LIKE ?";

            return jdbcTemplate.query(sql, (rs, rowNum) -> new ClienteModel(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getInt("numero_buceos"),
                            rs.getString("certificaciones")
                    ),
                    "%" + name + "%"
            );


    }


    public ClienteModel updateById(ClienteModel request, int id ){
        ClienteModel cliente = clienteRepository.findById(id).get();

        cliente.setNombre(request.getNombre());
        cliente.setApellidos(request.getApellidos());
        cliente.setFechaNacimiento(request.getFechaNacimiento());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setNumero_buceos(request.getNumero_buceos());
        cliente.setCertificaciones(request.getCertificaciones());
        cliente.setNivel(request.getNivel());

        return cliente;
    }


    public boolean deleteCliente(int id){

        try{
            clienteRepository.deleteById(id);
            return true;

        }catch (Exception e){
            return  false;
        }
    }
}
