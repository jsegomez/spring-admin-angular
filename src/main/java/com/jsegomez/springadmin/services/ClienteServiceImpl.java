package com.jsegomez.springadmin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsegomez.springadmin.models.dao.IClienteDao;
import com.jsegomez.springadmin.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return clienteDao.findById(id);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByEmail(String email) {
		return (List<Cliente>) clienteDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByName(String name) {
		return (List<Cliente>) clienteDao.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByLastName(String lastName) {
		return (List<Cliente>) clienteDao.findByLastName(lastName);
	}
}
