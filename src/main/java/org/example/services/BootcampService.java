package org.example.services;

import org.example.domain.entities.Bootcamp;
import org.example.domain.exceptions.ElementAlreadyExistsException;
import org.example.domain.exceptions.ElementNotFoundException;

import java.util.Collection;
import java.util.HashMap;

public class BootcampService {

    private final HashMap<String, Bootcamp> bootcamps = new HashMap<>();

    public void addBootcamp(Bootcamp bootcamp){
        String key = sanitizeKey(bootcamp.getName());

        if (bootcamps.containsKey(key)){
            String message = String.format("""
                    O %s,
                    já está cadastrado no sistema.
                    """, key);

            throw new ElementAlreadyExistsException(message);
        }

        bootcamps.put(key, bootcamp);
    }

    // Encontrar Bootcamp
    public Bootcamp findBootcamp(String bootcamp){

        String key = sanitizeKey(bootcamp);

        if(!bootcamps.containsKey(key)){
            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    """, key);

            throw new ElementNotFoundException(message);
        }

        return bootcamps.get(key);
    }


    // Atualizar Bootcamp
    public void updateBootcamp(Bootcamp bootcamp){
        String key = sanitizeKey(bootcamp.getName());

        if (!bootcamps.containsKey(key)){
            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    A atualização não pode ser realizada.
                    """, key);

            throw new ElementNotFoundException(message);
        }


        bootcamps.put(key, bootcamp);
    }


    // Deletar Bootcamp
    public Bootcamp deleteBootcamp(String bootcamp){
        String key = sanitizeKey(bootcamp);

        if (!bootcamps.containsKey(key)){
            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    Não é possível seguir em deletar.
                    """, key);
            throw new ElementNotFoundException(message);
        }

        return bootcamps.remove(key);
    }

    // Selecionar todos os Bootcamps
    public Collection<Bootcamp> getAllBootcamps(){
        return bootcamps.values();
    }


    // Method auxiliar de padronizar a chave
    private String sanitizeKey(String title){
        if (title == null || title.isEmpty()){
            return "";
        }

        return title.toUpperCase();
    }
}
