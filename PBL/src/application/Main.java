package application;


import java.util.*;

import application.model.Arbitro;
import application.model.ArbitroDAO;
import application.model.Jogador;
import application.model.JogadorDAO;
import application.model.Selecao;
import application.model.SelecaoDAO;
import application.model.Tecnico;
import application.model.TecnicoDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void criarArbitro(ArbitroDAO a) {
		Scanner leitor = new Scanner(System.in);
		String nome;
		
		System.out.println("Qual o nome do árbitro?");
		nome = leitor.next();
		Arbitro arb = new Arbitro(nome);
		a.InserirArbitro(arb);
		System.out.println("\nÁrbitro criado!!!");
		leitor.close();
	}
	
	public static void listarArbitros(ArbitroDAO a) {
		
		for (int key : a.ListaArbitro().keySet()) {

            Arbitro value = a.ListaArbitro().get(key);
            System.out.println("Código "+key + ": " + value);
		}
	}
	
	public static void editarArbitro(ArbitroDAO a) {
		Scanner leitor = new Scanner(System.in);
		int cod;
		String nome;
		
		listarArbitros(a);
		System.out.println("Digite o código do árbitro a ser editado");
		System.out.println("Código:");
		cod = leitor.nextInt();
		System.out.println("Digite o nome editado:");
		nome = leitor.next();
		a.ListaArbitro().get(cod).setNome(nome);
		System.out.println("\n2Árbitro editado!!!");
		leitor.close();
	}
	
	public static void removerArbitro(ArbitroDAO a) {
		Scanner leitor = new Scanner(System.in);
		int cod;
		
		listarArbitros(a);
		System.out.println("Digite o código do árbitro a ser removido:");
		cod = leitor.nextInt();
		a.RemoverArbitro(cod);
		System.out.println("\nÁrbitro removido!!!");
		leitor.close();
	}
	
	public static void criarSelecao(JogadorDAO j, SelecaoDAO s, TecnicoDAO t) {
		Scanner leitor = new Scanner(System.in);
		String nome, posicaoString;
		int posicao;
		
		System.out.println("Qual seleção deseja criar? (Digite o nome)");
		nome = leitor.next();
		Selecao sel = new Selecao(nome);
		System.out.println("Agora digite os dados dos 11 jogadores da selecao:");
		for(int i=1; i<3; i++) {
			System.out.println("Dados do "+i+"º jogador:");
			System.out.println("Nome:");
			nome = leitor.next();
			System.out.println("Posição (1-Goleiro; 2-Zagueiro; 3-Meia; 4-Atacante):");
			posicao = leitor.nextInt();
			switch(posicao) {
			case 1:
				posicaoString = "Goleiro";
				break;
			case 2:
				posicaoString = "Zagueiro";
				break;
			case 3:
				posicaoString = "Meia";
				break;
			case 4:
				posicaoString = "Atacante";
				break;
			default:
				posicaoString = "";
				break;
			}
			Jogador jog = new Jogador(nome, posicaoString);
			j.InserirJogador(jog);
			sel.getListaCodJog().add(jog.getCodJog());
			System.out.println("\n\nJogador cadastrado com sucesso!!!\n");
		}
		System.out.println("Agora digite nome do técnico:");
		nome = leitor.next();
		Tecnico tec = new Tecnico(nome);
		t.InserirTecnico(tec);
		sel.setCodTec(tec.getCodTec());
		s.InserirSelecao(sel);
		System.out.println("\n\nSeleção cadastrada com sucesso!!!\n");
		
		leitor.close();
	}
	
	public static void main(String[] args) {
		//launch(args);
		Scanner leitor = new Scanner(System.in);
		int entrada;
		
		JogadorDAO j = new JogadorDAO();
		SelecaoDAO s = new SelecaoDAO();
		TecnicoDAO t = new TecnicoDAO();
		ArbitroDAO a = new ArbitroDAO();
		
		System.out.println("Bem-vindo ao SysCopa! Este é o seu menu");
		System.out.println("Escolha qual entidade deseja manipular:");
		System.out.println("1-Seleção\n2-Árbitro\n3-Sair");
		entrada = leitor.nextInt();
		
		switch(entrada) {
		case 1:
			if(!s.ListaSelecao().isEmpty()) {
				System.out.println("Digite o número correspondente à ação que deseja tomar");
				//System.out.println("1-Criar Seleção\n2-Atualizar Seleção\n3-");
			}else {
				criarSelecao(j, s, t);
		
				for (int key : j.ListaJog().keySet()) {

                    Jogador value = j.ListaJog().get(key);
                    System.out.println(key + " = " + value);
				}
				for (int key : t.ListaTecnico().keySet()) {

                    Tecnico value = t.ListaTecnico().get(key);
                    System.out.println(key + " = " + value.getNome());
				}
				
				for(Selecao sell: s.ListaSelecao()) {
					System.out.println(sell.getNome());
					for(int codJogador: s.CodJogadoresSelecao(sell.getNome())) {
						System.out.println(codJogador);
					}
				}
			}
			break;
		case 2:
			if(!a.ListaArbitro().isEmpty()) {
				System.out.println("Digite o número correspondente à ação que deseja tomar");
				System.out.println("1-Criar árbitro\n2-Editar árbitro\n3-Remover árbitro\n4-Listar árbitros");
				entrada = leitor.nextInt();
				switch(entrada) {
				case 1:
					criarArbitro(a);
					break;
				case 2:
					editarArbitro(a);
					break;
				case 3:
					removerArbitro(a);
					break;
				case 4:
					listarArbitros(a);
					break;
				default:
					System.out.println("Você escolheu nenhuma das quatro opções");
					break;
				}
			}else {
				criarArbitro(a);
			}
			break;
		default:
			System.out.println("Tchau!");
			break;
		}
		
		leitor.close();
	}
}
	
