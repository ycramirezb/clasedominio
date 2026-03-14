package com.Ejercicio.Ejercicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.Ejercicio.Ejercicio.dao.IndividuoDao;
import com.Ejercicio.Ejercicio.domain.Individuo;

@Service
public class IndividuoService implements IIndividuoService {

    @Autowired
    private IndividuoDao individuoDao;

    @Override
    public List<Individuo> listarIndividuos(){

        return (List<Individuo>) individuoDao.findAll();
    }

    @Override
    public Individuo buscarIndividuoPorId(Integer id){
        return individuoDao.findById(id).orElse(null);
    }

    public void guardarIndividuo(Individuo individuo){
        individuoDao.save(individuo);
    }

    @Override
    public void eliminarIndividuo(Integer id){
        individuoDao.deleteById(id);
    }
    
    @Override
    public void editarIndividuo(Integer id){
        individuoDao.findById(id);
    }
}
