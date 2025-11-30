package org.example.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Utilities {

    static Scanner input = new Scanner(System.in);

    public static void banner(){
        System.out.print("""
                ************************************************
                |                 Bem Vindo!                   |
                |        Cadastro de Conteúdos e Devs          |
                ================================================
                """);
    }

    public static void menu(){
        System.out.print("""
                | [ 0 ] - Sair                                 |
                | [ 1 ] - Adicionar Conteúdo                   |
                | [ 2 ] - Adicionar Desenvolvedor              |
                | [ 3 ] - Adicionar Bootcamp                   |
                | [ 4 ] - Ligar Conteúdo a Bootcamp            |
                | [ 5 ] - Inscrever Dev em Bootcamp            |
                | [ 6 ] - Progredir Dev                        |
                | [ 7 ] - Calcular XP do Dev                   |
                ************************************************
                """);
    }


    // Validador para o entrado do menu
    public static int readValidPositiveInt(String promptMessage) {
        int value;

        while (true) {
            System.out.print(promptMessage);

            String newInput = input.nextLine().trim(); // Lemos como String

            try {
                // Tenta converter a String para Int
                value = Integer.parseInt(newInput);

                if (value < 0 || value > 7) {
                    System.out.println("""
                                ------------------------------------------------
                                ❌ ERRO:
                                A entrada deve ser um numero referente ao menu.
                                Tente novamente.
                                ------------------------------------------------
                                """);
                } else {
                    // Se chegou aqui, o valor é um número válido e positivo
                    return value; // SAI DO LOOP
                }

            } catch (NumberFormatException e) {
                // 2. Validação: Checa se a entrada é um número (trata letras/caracteres)
                System.out.println("""
                        ------------------------------------------------
                        ❌ ERRO: Entrada inválida.
                        Por favor, digite apenas um número que esteja ao menu do sistema.
                        ------------------------------------------------
                        """);
            }
        }
    }

    // Validador de entrada pra String Vazia
    public static String readValidString(String promptMessage) {
        String newInput;

        while (true) {
            System.out.print(promptMessage);

            // LEITURA DA LINHA E REMOÇÃO DOS ESPAÇOS EM BRANCO DO INÍCIO AO FIM
            newInput = input.nextLine().trim();

            // CONDIÇÃO SE A STRING ESTÁ VAZIA
            if (newInput.isEmpty()) {
                System.out.println("""
                        ------------------------------------------------
                        ❌ ERRO: A entrada não pode ser vazia.
                        Por favor, digite uma informação válida.
                        ------------------------------------------------
                        """);

            } else {
                // A STRING É VALIDA E NÃO ESTÁ VAZIA
                return newInput; // SUCESSO: SAI DO LOOP
            }
        }

    }

    // Validar numero Inteiro
    public static int readValidNumberInteger(String promptMessage){
        int value;

        while (true){
            System.out.print(promptMessage);

            String newInput = input.nextLine().trim();

            try {
                value = Integer.parseInt(newInput);

                if (value <= 0){
                    System.out.println("""
                        ------------------------------------------------
                        ❌ ERRO:
                        A entrada deve ser um numero maior que 0.
                        Tente novamente.
                        ------------------------------------------------
                        """);

                }else {

                    return value;

                }

            }catch (NumberFormatException e){
                String message = """
                        ❌ ERRO: O Numero invalido
                        """;
                System.out.println(message);
            }



        }
    }

    // Validar data
    public static LocalDate readValidData(String promptMessage){

        LocalDate date;

        while (true){
            System.out.print(promptMessage);

            String newDate = input.nextLine().trim();
            String formatDate = "dd/MM/uuuu";

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate)
                        .withResolverStyle(ResolverStyle.STRICT);

                date = LocalDate.parse(newDate, formatter);

                return date;

            }catch (DateTimeParseException e){
                System.out.printf("""
                        Data inválida:
                        """, newDate);
            }

        }

    }

    // Validador para, NOME
    public static String readValidName(String promptMessage) {
        String newInput;

        while (true) {
            System.out.print(promptMessage);

            // LEITURA DA LINHA E REMOÇÃO DE ESPAÇOS EM BRANCO DO INICIO E FIM
            newInput = input.nextLine().trim();

            // CONDIÇÃO DE CHECAGEM DE STRING ESTEJA VAZIA
            if (newInput.isEmpty()) {
                System.out.println("""
                        ------------------------------------------------
                        ❌ ERRO: A entrada não pode ser vazia.
                        Tente novamente!
                        ------------------------------------------------
                        """);
                // O LOOP CONTINUA E O PROMPT É REIMPRESSO
            } else if(!isValidString(newInput)){
                System.out.println("""
                        ------------------------------------------------
                        ❌ ERRO: Nome inválido.
                        Por favor, utilize apenas letras e espaços.
                        ------------------------------------------------
                        """);
            } else {
                // A STRING É VÁLIDA
                return (newInput); // SAI DO LOOP
            }
        }

    }

    // Formata LocalDate para String no padrão brasileiro (dd/MM/uuuu)
    public static String formatDateToString(LocalDate date) {
        if (date == null) {
            return "Data Inválida/Não Definida";
        }

        // Define o formato desejado (dd/MM/uuuu)
        String formatDate = "dd/MM/uuuu";

        // Cria o formatador
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);

        // Aplica o formatador ao objeto LocalDate e retorna a String
        return date.format(formatter);
    }


    // Validador de entrada para String não seja números
    private static boolean isValidString(String promptName){
        if (promptName.trim().isEmpty()) {
            return false;
        }

        return promptName.trim().matches("[a-zA-Z\\s]+");
    }




}
