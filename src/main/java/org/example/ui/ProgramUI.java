package org.example.ui;

import org.example.domain.entities.*;
import org.example.domain.exceptions.ElementAlreadyExistsException;
import org.example.domain.exceptions.ElementNotFoundException;
import org.example.services.BootcampService;
import org.example.services.ContentService;
import org.example.services.DevService;
import org.example.services.ServiceProgram;
import org.example.utility.Utilities;

import java.time.LocalDate;
import java.util.Collection;

public class ProgramUI {

    private static final ContentService contentService = new ContentService();
    private static final DevService devService = new DevService();
    private static final BootcampService bootcampService = new BootcampService();
    private static final ServiceProgram serviceProgram = new ServiceProgram(
            contentService, devService, bootcampService);

    public void appRunner(){

        while (true){

            Utilities.banner();

            Utilities.menu();

            String question = String.valueOf(
                    Utilities.readValidPositiveInt("""
                                                Informe um valor do Menu:
                                                """));



            switch (question){
                case "0" -> exitProgram(question);
                case "1" -> createContent();
                case "2" -> createDev();
                case "3" -> createBootcamp();
                case "4" -> linkContentToBootcamp();
                case "5" -> registerDevInBootcamp();
                case "6" -> progressDev();
                case "7" -> calculateDevTotalXp();

            }

            if (question.equals("0"))break;

        }
    }

    private static void createContent(){

        String tipeOfContent;

        while(true){

            tipeOfContent = Utilities.readValidString("""
                Informe o tipo do Conteúdo [ Curso | Mentoria ]
                """);
            if (!tipeOfContent.equalsIgnoreCase("curso") && !tipeOfContent.equalsIgnoreCase("mentoria")){
                System.out.println("ERRO! o informe o tipo correto");
            }

            if (tipeOfContent.equalsIgnoreCase("curso") || tipeOfContent.equalsIgnoreCase("Mentoria")) break;

        }

        String titleContent = Utilities.readValidString("""
                Informe o titulo do Conteúdo
                """);

        String descriptionContent = Utilities.readValidString("""
                Informe a descrição do conteúdo
                """);

        int durationContent = Utilities.readValidNumberInteger("""
                Informe a duração do conteúdo ex [ 50 ]
                """);

        try {

            Course newCourse = null;
            Mentoring newMentoring= null;

            if (tipeOfContent.equalsIgnoreCase("curso")){
                newCourse = new Course(
                        tipeOfContent,
                        titleContent,
                        descriptionContent,
                        durationContent);
                serviceProgram.addContent(newCourse);

            } else if (tipeOfContent.equalsIgnoreCase("mentoria")){

                LocalDate dateInitial =Utilities.readValidData("""
                    Informe a data de inicio ex [ DD/MM/AAAA ]
                    """);
                LocalDate dataFinished = Utilities.readValidData("""
                    Informe a data final [ DD/MM/AAAA ]
                    """);

                newMentoring = new Mentoring(
                        tipeOfContent,
                        titleContent,
                        descriptionContent,
                        durationContent,
                        dateInitial,
                        dataFinished
                );

                serviceProgram.addContent(newMentoring);

            }


            String contentTipe;
            String  contentTitle;

            if (tipeOfContent.equalsIgnoreCase("curso")){
                assert newCourse != null;
                contentTipe = newCourse.getType().toUpperCase();
                contentTitle = newCourse.getTitle().toUpperCase();
            }else {
                assert newMentoring != null;
                contentTipe = newMentoring.getType().toUpperCase();
                contentTitle = newMentoring.getTitle().toUpperCase();
            }

            System.out.printf("""
                    ************************************************
                            ✅ %s REGISTRADO COM SUCESSO!
                    ************************************************
                    
                    TITULO: %s
                    ------------------------------------------------
                    """,
                    contentTipe,
                    contentTitle
                    );


        }catch (ElementAlreadyExistsException e){
            System.out.println(e.getMessage());
        }




    }

    private static void createDev(){
        String nameDev = Utilities.readValidName("""
                Informe o nome
                """);
        String registerNumberDev = String.valueOf(
                Utilities.readValidNumberInteger("""
                Informe o numero de registro do Dev
                """));

        try {

            Dev dev = new Dev(nameDev, registerNumberDev);

            serviceProgram.addDev(dev);

            System.out.printf("""
                    ************************************************
                            ✅ DEV REGISTRADO COM SUCESSO!
                    ************************************************
                    NOME:  %s
                    N° REGISTRO: %s
                    ------------------------------------------------
                    """,
                    dev.getName().toUpperCase(),
                    dev.getRegistrationNumber());

        }catch (ElementAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createBootcamp(){
        String nameBootcamp = Utilities.readValidString("""
                Informe o titulo do Bootcamp
                """);

        String descriptionBootcamp = Utilities.readValidString("""
                Informe a descrição para o Bootcamp
                """);

        LocalDate dateInitialBootcamp = Utilities.readValidData("""
                Informe a data de inicio do Bootcamp ex: [ DD/MM/AAAA ]
                """);

        try {

            Bootcamp bootcamp = new Bootcamp(nameBootcamp, descriptionBootcamp, dateInitialBootcamp);

            serviceProgram.addBootcamp(bootcamp);

            System.out.printf("""
                    ************************************************
                          ✅ BOOTCAMP REGISTRADO COM SUCESSO!
                    ************************************************
                    BOOTCAMP: %s
                    DATA DE INICIO:  %s
                    DATA DE FINALIZAR: %s
                    ------------------------------------------------
                    """,
                    bootcamp.getName().toUpperCase(),
                    Utilities.formatDateToString(bootcamp.getDateInitial()),
                    Utilities.formatDateToString(bootcamp.getDateFinal()));

        }catch (ElementAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }

    public static void linkContentToBootcamp(){
        // exibir todos os conteúdos
        showAllContents();

        String contentTitle = Utilities.readValidString("""
                Informe o título do Conteúdo a ser adicionado:
                """);
        // exibir todos os conteúdos
        showAllBootcamps();

        String bootcampName = Utilities.readValidString("""
                Informe o nome do Bootcamp de destino:
                """);

        try {
            serviceProgram.linkContentToBootcamp(contentTitle, bootcampName);

        } catch (ElementNotFoundException e) {
            // Trata se o Conteúdo ou o Bootcamp não for encontrado
            System.out.println(e.getMessage());
        }
    }

    private static void registerDevInBootcamp(){
        int numberRegisterDev = Utilities.readValidNumberInteger("""
                Informe o numero de registro do Dev
                """);

        String numberRegisterDevString = String.valueOf(numberRegisterDev).trim();

        // exibir todos os Bootcamps
        showAllBootcamps();

        String nameBootcamp = Utilities.readValidString("""
                Informe o nome do Bootcamp
                """);

        try {

            Dev dev = devService.findDev(numberRegisterDevString);
            Bootcamp bootcamp = bootcampService.findBootcamp(nameBootcamp);

            serviceProgram.registerDevInBootcamp(numberRegisterDevString, nameBootcamp);

            System.out.printf("""
                ************************************************
                ✅ DEV %s INSCRITO NO BOOTCAMP %s COM SUCESSO.
                ************************************************
                
                DATA PARA FINALIZAR O BOOTCAMP: %s
                ------------------------------------------------
                """,
                    dev.getName().toUpperCase(),
                    bootcamp.getName().toUpperCase(),
                    Utilities.formatDateToString(bootcamp.getDateFinal()));


        }catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    private static void progressDev(){

        int numberRegisterDev = Utilities.readValidNumberInteger("""
                Informe o numero de registro do Dev
                """);

        String numberRegisterDevString = String.valueOf(numberRegisterDev).trim();

        try {

            Dev dev = devService.findDev(numberRegisterDevString);

            serviceProgram.devProgress(numberRegisterDevString);

            System.out.printf("""
                ************************************************
                        ✅ PROGRESSO DE %s REGISTRADO.
                              XP TOTAL ATUAL: %.2f
                ************************************************
                """,
                    dev.getName().toUpperCase(),
                    dev.calculationTotalXP()
            );


        }catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    private static void calculateDevTotalXp(){
        int numberRegisterDev = Utilities.readValidNumberInteger("""
                Informe o numero de registro do Dev
                """);

        String numberRegisterDevString = String.valueOf(numberRegisterDev).trim();


        try {

            Dev dev = devService.findDev(numberRegisterDevString);

            serviceProgram.calculateDevTotalXp(numberRegisterDevString);

            System.out.printf("""
                ************************************************
                          ✅ XP ATUAL: %.2f
                             %s
                ************************************************
                """,
                    dev.calculationTotalXP(),
                    dev.getName().toUpperCase()
            );

        }catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }

    }


    private static void exitProgram(String promptInput){

        while (true){

            if (promptInput.equalsIgnoreCase("0")){
                System.out.println("""
                        PROGRAMA FINALIZADO!
                        """);
                break;
            }

        }
    }



    private static void showAllBootcamps(){
        Collection<Bootcamp> bootcamps = bootcampService.getAllBootcamps();

        if ((bootcamps.isEmpty())){
            System.out.println("""
                    ------------------------------------------------
                    ⚠️ Não a bootcamps registrado no sistema!
                    ------------------------------------------------
                    """);
            return;
        }

        System.out.println("""
                ************************************************
                      ✅ LISTAGEM DE TODOS OS BOOTCAMPS
                ************************************************
                """);

        for (Bootcamp bootcamp: bootcamps){
            System.out.printf("""
                    ------------------------------------------------
                    BOOTCAMP: %s
                    DESCRIÇÃO: %s
                    DATA INICIO: %s
                    DATA FINAL: %s
                    ------------------------------------------------
                    """,
                    bootcamp.getName(),
                    bootcamp.getDescription(),
                    Utilities.formatDateToString(bootcamp.getDateInitial()),
                    Utilities.formatDateToString(bootcamp.getDateFinal()));
        }

    }

    private static void showAllContents(){
        Collection<Content> contents = contentService.getAllContents();

        if ((contents.isEmpty())){
            System.out.println("""
                    ------------------------------------------------
                    ⚠️ Não a conteúdos registrado no sistema!
                    ------------------------------------------------
                    """);
            return;
        }


        System.out.println("""
                ************************************************
                      ✅ LISTAGEM DE TODOS OS CONTEÚDOS
                ************************************************
                """);

        for (Content content: contents){
            System.out.printf("""
                    ------------------------------------------------
                    %s
                    TITULO: %s
                    ------------------------------------------------
                    """,
                    content.typeContent().toUpperCase(),
                    content.getTitle().toUpperCase());
        }

    }

}
