package main;

import java.util.Scanner;

public class NavigationMenu {

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("----------------------------------");
            System.out.println("BEM-VINDO(A) À LOCADORA VIDEO_RENT!");
            System.out.println("----------------------------------");
            System.out.println();

            System.out.println("-----MENU-----\n1 - CATÁLOGO\n2 - CLIENTE\n0 - SAIR");
            while (true){
                try {
                    option = sc.nextInt();
                    break;
                } catch (Exception e){
                    System.err.println("Erro: " + e);
                    System.err.print("Tente novamente: ");
                    sc.next();
                }
            }
            switch (option){
                case 1:
                    System.out.println("catalogo");
                    int optionMovie;
                    do {
                        System.out.println("---MENU DO CATÁLOGO---");
                        System.out.println("\n1 - INSERIR FILME\n2 - VER CATÁLOGO\n3 - ATUALIZAR CATÁLOGO\n4 - REMOVER FILME\n5 - LIMPAR TUDO\n0 - SAIR");
                        while (true){
                            try {
                                optionMovie = sc.nextInt();
                                break;
                            } catch (Exception e){
                                System.err.println("Erro: " + e);
                                System.err.print("Tente novamente: ");
                                sc.next();
                            }
                        }
                        switch (optionMovie){
                            case 1:
                                System.out.println(" registrar filme");
                                break;
                            case 2:
                                System.out.println(" ver filmes");
                                break;
                            case 3:
                                System.out.println(" atualizar filmes");
                                break;
                            case 4:
                                System.out.println("deletar filmes");
                                break;
                            case 5:
                                System.out.println("Limpar tudo");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("OPÇÃO INVÁLIDA! INDIQUE NOVAMENTE.");
                                break;
                        }
                    } while (optionMovie != 0);
                    break;
                case 2:
                    int optionClient;
                    do {
                        System.out.println("---MENU DE CLIENTES---");
                        System.out.println("\n1 - REGISTRAR CLIENTE\n2 - VER CLIENTES\n3 - ATUALIZAR INFO.\n4 - REMOVER CLIENTE\n5 - LIMPAR TUDO\n0 - SAIR");
                        while (true){
                            try {
                                optionClient = sc.nextInt();
                                break;
                            } catch (Exception e){
                                System.err.println("Erro: " + e);
                                System.err.print("Tente novamente: ");
                                sc.next();
                            }
                        }
                        switch (optionClient){
                            case 1:
                                System.out.println(" registrar cliente");
                                break;
                            case 2:
                                System.out.println(" ver cliente");
                                break;
                            case 3:
                                System.out.println(" atualizar cliente");
                                break;
                            case 4:
                                System.out.println("deletar cliente");
                                break;
                            case 5:
                                System.out.println("Limpar tudo");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("OPÇÃO INVÁLIDA! INDIQUE NOVAMENTE.");
                                break;
                        }
                    } while (optionClient != 0);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA! INDIQUE NOVAMENTE.");
                    break;
            }
        } while (option != 0);
    sc.close();
    }
}
