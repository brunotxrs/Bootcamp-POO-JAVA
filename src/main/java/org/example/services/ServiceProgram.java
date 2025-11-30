package org.example.services;

import org.example.domain.entities.Bootcamp;
import org.example.domain.entities.Content;
import org.example.domain.entities.Dev;
import org.example.domain.exceptions.*;

public class ServiceProgram {

    private final ContentService contentService;
    private final DevService devService;
    private final BootcampService bootcampService;

    public ServiceProgram(ContentService contentService, DevService devService, BootcampService bootcampService) {
        this.contentService = contentService;
        this.devService = devService;
        this.bootcampService = bootcampService;
    }

    // Adicionar
    public void addContent(Content content){
        contentService.addContent(content);
    }

    public void linkContentToBootcamp(String contentTitle, String bootcampName){
        // Busca Content (lança NotFound)
        Content content = contentService.findContent(contentTitle);

        // Busca Bootcamp (lança NotFound)
        Bootcamp bootcamp = bootcampService.findBootcamp(bootcampName);

        // Adiciona o Conteúdo ao Bootcamp
        bootcamp.addContent(content);

        System.out.printf("""
            ************************************************
            ✅ CONTEÚDO '%s' ADICIONADO AO BOOTCAMP '%s' COM SUCESSO.
            ************************************************
            """, content.getTitle().toUpperCase(), bootcamp.getName().toUpperCase());
    }

    public void addDev(Dev dev){
        devService.addDev(dev);
    }

    public void addBootcamp(Bootcamp bootcamp){
        bootcampService.addBootcamp(bootcamp);
    }

    // Buscar
    public Dev find(String registerNumber){
        return devService.findDev(registerNumber);
    }

    // ORQUESTRAÇÃO AVANÇADA: Criar só se existir
    public void registerDevInBootcamp(String devRegistration, String bootcampName){

        Dev dev = devService.findDev(devRegistration);

        Bootcamp bootcamp = bootcampService.findBootcamp(bootcampName);

        dev.signUpBootcamp(bootcamp);

    }

    public void devProgress(String devRegistration){
        Dev dev = devService.findDev(devRegistration);

        dev.progress();

        System.out.printf("""
                Progresso de %s registrado. XP total atual: %.2f
                """, dev.getName(), dev.calculationTotalXP());
    }

    public double calculateDevTotalXp(String devRegistration){

        Dev dev = devService.findDev(devRegistration);

        return dev.calculationTotalXP();

    }
}
