package com.jsegomez.springadmin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsegomez.springadmin.models.dao.IClienteDao;
import com.jsegomez.springadmin.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	
	@Autowired
	private IClienteDao clienteDao;

	// Método para devolver clientes sin paginar
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	// Método para devolver clientes paginados
	@Override
	public Page<Cliente> findAll(Pageable paginador) {
		return clienteDao.findAll(paginador);
	}

	// Método para buscar clientes por ID
	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return clienteDao.findById(id);
	}

	// Método para crear/actualizar cliente
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	// Método para eliminar un cliente por ID
	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}

	// Método para buscar clientes por email
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByEmail(String email) {
		return (List<Cliente>) clienteDao.findByEmail(email);
	}

	// Método para buscar cliente por nombre
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByName(String name) {
		return (List<Cliente>) clienteDao.findByName(name);
	}

	// Método para buscar cliente por apellido
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByLastName(String lastName) {
		return (List<Cliente>) clienteDao.findByLastName(lastName);
	}
}
