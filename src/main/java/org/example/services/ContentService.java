package org.example.services;

import org.example.domain.entities.Content;
import org.example.domain.exceptions.ElementAlreadyExistsException;
import org.example.domain.exceptions.ElementNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ContentService {

    private final Map<String, Content> contentMap = new HashMap<>();


    // Adicionar Conteúdo
    public void addContent(Content content){

        String key = sanitizeKey(content.getTitle());

        if(contentMap.containsKey(key)){

            String message = String.format("""
                    O  %s,
                    já está cadastrado no sistema.
                    """, content.getTitle());

            throw new ElementAlreadyExistsException(message);
        }

        contentMap.put(key, content);

    }


    // Encontrar Conteúdo
    public Content findContent(String title){

        String key = sanitizeKey(title);

        if(!contentMap.containsKey(key)){
            String message = String.format("""
                        O %s,
                        não está cadastrado no sistema.
                        """, key.toUpperCase());
            throw new ElementNotFoundException(message);
        }
        return contentMap.get(key);

    }


    // Atualizar Conteúdo
    public void updateContent(Content content){
        String key = sanitizeKey(content.getTitle());

        if (!contentMap.containsKey(key)){

            String message = String.format("""
                    O %s,
                    não está cadastrado no sistema.
                    A atualização não pode ser realizada.
                    """, key);

            throw new ElementNotFoundException(message);
        }

        contentMap.put(key, content);

    }

    // Deletar conteúdo
    public Content deleteContent(String title){

        String key = sanitizeKey(title);

        if (!contentMap.containsKey(key)){
            String message = String.format("""
                        O %s,
                        não está cadastrado no sistema.
                        Não é possível seguir em deletar.
                        """, key);

            throw new ElementNotFoundException(message);
        }

        return contentMap.remove(key);

    }


    // Selecionar todos os Conteúdos
    public Collection<Content> getAllContents(){
        return contentMap.values();
    }


    // Method auxiliar para padronizar a chave
    public String sanitizeKey(String title){
        if (title == null || title.isEmpty()){
            return "";

        }

        return title.toUpperCase();
    }

}
