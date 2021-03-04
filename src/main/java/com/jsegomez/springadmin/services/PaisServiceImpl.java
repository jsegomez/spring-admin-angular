package com.jsegomez.springadmin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsegomez.springadmin.models.dao.IPaisDao;
import com.jsegomez.springadmin.models.entity.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	
	@Autowired
	private IPaisDao paisDao;

	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAll() {
		return paisDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pais> findById(Long id) {
		return paisDao.findById(id);
	}

	@Override
	@Transactional
	public Pais save(Pais pais) {
		return paisDao.save(pais);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		paisDao.deleteById(id);
	}

}
