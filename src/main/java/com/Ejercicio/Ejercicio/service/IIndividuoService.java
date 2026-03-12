package com.Ejercicio.Ejercicio.service;

import com.Ejercicio.Ejercicio.domain.Individuo;
import java.util.List;


public interface IIndividuoService {

    List<Individuo> listarIndividuos();

    Individuo buscarIndividuoPorId(Integer id);
    void guardarIndividuo(Individuo individuo);



    
}
