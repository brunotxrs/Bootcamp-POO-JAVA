package org.example.services;

import org.example.domain.entities.Dev;
import org.example.domain.exceptions.ElementAlreadyExistsException;
import org.example.domain.exceptions.ElementNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DevService {
    private final Map<String, Dev> devMap = new HashMap<>();


    // add Dev
    public void addDev(Dev dev){
        String registerNumberClean = dev.getRegistrationNumber().replaceAll("[^0-9]", "");

        if (devMap.containsKey(registerNumberClean)){

            String message = String.format("""
                    Erro: Esse %s desenvolvedor já existe
                    """, registerNumberClean);
            throw new ElementAlreadyExistsException(message);

        }

        devMap.put(registerNumberClean, dev);

    }

    // Buscar Dev
    public Dev findDev(String registerNumber){
        String registerNumberClean = registerNumber.replaceAll("[^0-9]", "");

        if (!devMap.containsKey(registerNumberClean)){
            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    """, registerNumberClean);

            throw new ElementNotFoundException(message);
        }

        return devMap.get(registerNumberClean);
    }

    // Atualizar Dev
    public void updateDev(Dev dev){
        String registerNumberClean = dev.getRegistrationNumber().replaceAll("[^0-9]", "");

        if (!devMap.containsKey(registerNumberClean)){
            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    A atualização não pode ser realizada.
                    """, registerNumberClean);

            throw new ElementNotFoundException(message);
        }


        devMap.put(registerNumberClean, dev);
        System.out.printf("Desenvolvedor %s Atualizado", registerNumberClean);

    }

    // Deletar Dev
    public Dev deleteDev(String registerNumberDev){
        String registerNumberClean = registerNumberDev.replaceAll("[^0-9]", "");

        if(!devMap.containsKey(registerNumberClean)){
            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    Não é possível seguir em deletar.
                    """, registerNumberClean);

            throw new ElementNotFoundException(message);
        }

        return devMap.remove(registerNumberClean);

    }


    // Selecionando todos Devs
    public Collection<Dev> getAllDevs(){
        return devMap.values();
    }

}