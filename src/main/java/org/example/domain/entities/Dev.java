package org.example.domain.entities;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class Dev {
    private final String name;
    private final String registrationNumber;


    private final Set<Content> subscribedContent = new LinkedHashSet<>();
    private final Set<Content> finishedContent = new LinkedHashSet<>();

    public Dev(String name, String registrationNumber) {
        this.name = name;
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    // 1. Method de Inscrição
    public void signUpBootcamp(Bootcamp bootcamp){
        this.subscribedContent.addAll(bootcamp.getContents());

        bootcamp.getDevs().add(this);

    }

    // 2. Method de Progresso
    public  void progress(){

        // Tenta pegar o primeiro conteúdo (o mais antigo na ordem de inscrição)
        Optional<Content> content = this.subscribedContent.stream().findFirst();

        if(content.isPresent()){

            Content nextContent = content.get();

            // Move o conteúdo para a lista de concluídos
            this.finishedContent.add(nextContent);

            // Remove da lista de inscritos
            this.subscribedContent.remove(nextContent);
        }else{

            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    // 3. Method de Cálculo de XP
    public double calculationTotalXP(){
        // Soma o XP de todos os conteúdos concluídos usando Polimorfismo
        return this.finishedContent
                .stream()
                .mapToDouble(Content::calculationXP)
                .sum();
    }


}
