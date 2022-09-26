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
	static Scanner leitor = new Scanner(System.in);
	
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

	//Função de criação de árbitro
	public static void criarArbitro(ArbitroDAO a) {
		
		String nome;
		
		System.out.println("Qual o nome do árbitro?");
		nome = leitor.nextLine(); //Recebe o nome do árbitro
		Arbitro arb = new Arbitro(nome); //Instancia um objeto do tipo Arbitro
		a.InserirArbitro(arb); //Insere na coleção de árbitros no ArbitroDAO
		System.out.println("\nÁrbitro criado!!!");
		
	}
	
	//Função para listar todos os árbitros cadastrados
	public static void listarArbitros(ArbitroDAO a) {
		//Percorre toda a colção de árbitros, printando os códigos e seus respectivos nome
		for (int key : a.ListaArbitro().keySet()) {

            Arbitro value = a.ListaArbitro().get(key);
            System.out.println("Código "+key + ": " + value.getNome());
		}
	}
	
	//Função para editar um dos árbitros cadastrados
	public static void editarArbitro(ArbitroDAO a) {
		
		int cod;
		String nome;
		
		listarArbitros(a); //Lista todos os árbitros 
		System.out.println("Digite o código do árbitro a ser editado");
		System.out.println("Código:");
		cod = leitor.nextInt(); //O usuário digita o código do árbitro a ser removido
		leitor.nextLine(); //Limpa o buffer
		//Se o árbitro com esse código estiver na coleção...
		if(a.ListaArbitro().get(cod) != null) {
			System.out.println("Digite o nome editado:");
			nome = leitor.nextLine(); //Recebe o nome editado do árbitro
			a.ListaArbitro().get(cod).setNome(nome); //Altera o nome na coleção
			System.out.println("\nÁrbitro editado!!!");
		//Se o árbitro com esse código não for encontrado...
		}else {
			System.out.println("Árbitro não encontrado!!!");
		}
	}
	
	//Função para remover um dos árbitros cadastrados
	public static void removerArbitro(ArbitroDAO a) {
		
		int cod;
		
		listarArbitros(a); //Lista todos os árbitros
		System.out.println("Digite o código do árbitro a ser removido:");
		cod = leitor.nextInt(); //O usuário digita o código do árbitro a ser removido
		leitor.nextLine(); //Limpa o buffer
		if(a.RemoverArbitro(cod) != null) //Se for encontrado o árbitro, ele será removido
			System.out.println("\nÁrbitro removido!!!");
		else //Se não encontrar o árbitro...
			System.out.println("Não há nenhum árbitro com esse código");
		
	}
	
	//Função para listar todos os técnicos cadastrados
	public static void listarTecnicos(TecnicoDAO t) {
		//Percorre toda a colção de técnicos, printando os códigos e seus respectivos nome
		for (int key : t.ListaTecnico().keySet()) {

            Tecnico value = t.ListaTecnico().get(key);
            System.out.println("Código "+key + ": " + value.getNome());
		}
	}
	
	//Função para listar todos os jogadores cadastrados
	public static void listarJogadores(JogadorDAO j) {
		//Percorre toda a colção de jogadores, printando os códigos e seus respectivos dados
		for (int key : j.ListaJog().keySet()) {

            Jogador value = j.ListaJog().get(key);
            System.out.println("Código "+key + ": " + value);
		}
	}
	
	public static void listarSelecoesOuComponentes(JogadorDAO j, SelecaoDAO s, TecnicoDAO t) {
		System.out.println("Qual ação deseja tomar?");
		System.out.println("1-Listar todas as seleções\n2-Listar todos os componentes de uma seleção");
		int entrada = leitor.nextInt(); //Recebe entrada do usuário
		leitor.nextLine();
		switch(entrada) {
		case 1: //Se escolhar listar todas as seleções
			listarSelecoes(s);
			break;
		case 2: //Caso escolha listar tudo peretecente a uma seleção
			listarSelecoes(s); //Lista todas seleções para facilitar a escolha de qual seleção o usuário quer visualizar
			System.out.println("Qual a seleção deseja visualizar? (Digite o código)");
			int codSel = leitor.nextInt(); //Recebe o código da seleção a ser visualizada
			leitor.nextLine(); //Limpa o buffer
			listarMembrosSelecao(codSel, j, s, t); //Lista tudo da seleção caso a encontre
			break;
		default:
			System.out.println("Você digitou um valor inválido");
			break;
		}
	}
	
	//Função para listar todas as seleções cadastradas
 	public static void listarSelecoes(SelecaoDAO s) {
		//Percorre toda a colção de seleções, printando os códigos e seus respectivos nome
		for (int key : s.ListaSelecao().keySet()) {
			
            Selecao value = s.ListaSelecao().get(key);
            System.out.println(key + " = " + value.getNome());
		}
	}
	
	//Função para listar todos os componentes de uma seleção escolhida
	public static void listarMembrosSelecao(int cod, JogadorDAO j, SelecaoDAO s, TecnicoDAO t) {
		Selecao sele = s.BuscarSelecao(cod); //Instancia um objeto do tipo seleção, buscando-a pelo seu código
		if(sele != null) { //Se encontrar a seleção...
			System.out.println("Técnico: "+t.ListaTecnico().get(sele.getCodTec()).getNome());
			for(int codJog : s.CodJogadoresSelecao(cod)) {
				System.out.println("Código "+codJog+": "+j.BuscarJogador(codJog));
			}
		}else { //Se não encontrar a seleção...
			System.out.println("Seleção não encontrada!!!");
		}
	}
	
	//Função que cria a seleção e cria seus 11 jogadores e seu técnico
	public static void criarSelecao(JogadorDAO j, SelecaoDAO s, TecnicoDAO t) {
		
		String nome, posicaoString;
		int posicao;
		
		System.out.println("Qual seleção deseja criar? (Digite o nome)");
		nome = leitor.nextLine();
		Selecao sel = new Selecao(nome);
		System.out.println("Agora digite os dados dos 11 jogadores da selecao:");
		for(int i=1; i<3; i++) {
			System.out.println("Dados do "+i+"º jogador:");
			System.out.println("Nome:");
			nome = leitor.nextLine();
			System.out.println("Posição (1-Goleiro; 2-Zagueiro; 3-Meia; 4-Atacante):");
			posicao = leitor.nextInt();
			leitor.nextLine();
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
				System.out.println("Você digitou um valor inválido, a posição dele vai ser colocada como vazia. Altere-a futuramente!");
				posicaoString = "";
				break;
			}
			Jogador jog = new Jogador(nome, posicaoString);
			j.InserirJogador(jog);
			sel.getListaCodJog().add(jog.getCodJog());
			System.out.println("\n\nJogador cadastrado com sucesso!!!\n");
		}
		System.out.println("Agora digite nome do técnico:");
		nome = leitor.nextLine();
		Tecnico tec = new Tecnico(nome);
		t.InserirTecnico(tec);
		sel.setCodTec(tec.getCodTec());
		s.InserirSelecao(sel);
		System.out.println("\n\nSeleção cadastrada com sucesso!!!\n");
		
	}
	
	public static void editarSelecao(JogadorDAO j, SelecaoDAO s, TecnicoDAO t) {
		listarSelecoes(s); //Lista todas as seleções já cadastradas para facilitar que o usuário saiba qual editar
		System.out.println("Qual a seleção a ter seus dados alterados? (digite o código)");
		int cod = leitor.nextInt(); //Recebe o código da seleção a ser editada
		leitor.nextLine(); //Limpa o buffer
		if(s.BuscarSelecao(cod) != null) { //Se encontrar a seleção
			listarMembrosSelecao(cod, j, s, t); //Lista todos os membros da seleção
			System.out.println("Deseja editar o que de seleção?");
			System.out.println("1-Nome de Seleção\n2-Nome do técnico\n3-Dados de algum jogador");
			System.out.println("Escolha:");
			int entrada = leitor.nextInt(); //Recebe entrada do usuário
			leitor.nextLine(); //Limpa o buffer
			switch(entrada) {
			case 1: //Se quiser alterar o nome da seleção
				System.out.println("Qual o novo nome editado da seleção?");
				String nomeSel = leitor.nextLine(); //Recebe o novo nome editado da seleção
				s.BuscarSelecao(cod).setNome(nomeSel); //Seta o novo nome da seleção
				break;
			case 2: //Se for escolhido alterar o nome do técnico
				System.out.println("Qual o novo nome editado do técnico?");
				String nomeTec = leitor.nextLine(); //Recebe o novo nome do técnico
				t.ListaTecnico().get(s.BuscarSelecao(cod).getCodTec()).setNome(nomeTec);; //Altera o nome do técnico
				break;
			case 3: //Se for escolhido alterar os dados do jogador
				System.out.println("Deseja alterar dados de qual jogador? (digite seu código)");
				int codJog = leitor.nextInt(); //Recebe o código do jogador
				leitor.nextLine(); //Limpa o buffer
				editarJogador(codJog, cod, j, s);
				break;
			default:
				System.out.println("Você não digitou nenhuma das opções!!!");
				break;
			}
		}else {
			System.out.println("Seleção não encontrada!!");
		}
	}
	
	public static void editarJogador(int codJog, int codSel, JogadorDAO j, SelecaoDAO s) {
		if(s.CodJogadoresSelecao(codSel).contains(codJog)) {
			System.out.println("Deseja alterar o quê de "+j.BuscarJogador(codJog).getNome()+"?");
			System.out.println("1-Nome\n2-Posição\n3-Número de cartões\n4-Número de gols");
			System.out.println("Escolha:");
			int escolha = leitor.nextInt();
			leitor.nextLine();
			switch(escolha) {
			case 1:
				System.out.println("Qual o novo nome?");
				String nome = leitor.nextLine();
				j.BuscarJogador(codJog).setNome(nome);
				System.out.println("Nome alterado com sucesso!");
				break;
			case 2:
				System.out.println("Qual a nova posição? (1-Goleiro; 2-Zagueiro; 3-Meia; 4-Atacante)");
				String posicaoString;
				int posicao = leitor.nextInt();
				leitor.nextLine();
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
				j.BuscarJogador(codJog).setPosicao(posicaoString);
				System.out.println("Posição alterada com sucesso!");
				break;
			case 3:
				System.out.println("Quantos cartões amarelos esse jogador tem?");
				int cartAma = leitor.nextInt();
				System.out.println("Quantos cartões vermelhos esse jogador tem?");
				int cartVer = leitor.nextInt();
				leitor.nextLine(); //Limpa o buffer
				int[] cartoes = {cartAma, cartVer};
				j.BuscarJogador(codJog).setCartoes(cartoes);
				System.out.println("Edição feita com sucesso!");
				break;
			case 4:
				System.out.println("Quantos gols esse jogador tem?");
				int gols = leitor.nextInt();
				leitor.nextLine(); //Limpa o buffer
				j.BuscarJogador(codJog).setNumGols(gols);
				System.out.println("Edição feita com sucesso!");
				break;
			default:
				System.out.println("Você não digitou nenhuma das opções!!!");
				break;
			}
		}else {
			System.out.println("Jogador não encontrado!");
		}
	}
	
	//Função que remove a seleção e seus jogadores e técnico
	public static void removerSelecao(JogadorDAO j, SelecaoDAO s, TecnicoDAO t) {
		
		listarSelecoes(s);
		System.out.println("Digite o código da seleção que deseja remover, sabendo que ao remover uma seleção, estará removendo seus componentes!");
		System.out.println("Código:");
		int cod = leitor.nextInt();
		leitor.nextLine();
		if(s.BuscarSelecao(cod) != null) {
			for(int i: s.CodJogadoresSelecao(cod)) {
				j.RemoverJogador(i);
			}
			t.RemoverTecnico(s.BuscarSelecao(cod).getCodTec());
			s.RemoverSelecao(cod);
		}else {
			System.out.println("Você digitou um código inválido!!!");
		}
		System.out.println("Seleção removida com sucesso!");
	}

	public static void main(String[] args) {
		//launch(args);
		int entrada;
		boolean loop = true;
		
		//Instanciandos os objetos DAO
		JogadorDAO j = new JogadorDAO();
		SelecaoDAO s = new SelecaoDAO();
		TecnicoDAO t = new TecnicoDAO();
		ArbitroDAO a = new ArbitroDAO();
		
		System.out.println("Bem-vindo ao SysCopa! Este é o seu menu\n");
		
		//Loop do menu
		while(loop) {
			System.out.println("Escolha qual ação deseja tomar:");
			//Denrtro de seleção, pode-se manipular os seus jogadores e seu técnico
			System.out.println("1-Manipular seleção, seus jogadores e técnico\n2-Manipular árbitro\n3-Listar técnicos\n4-Listar jogadores\n5-Sair");
			entrada = leitor.nextInt();
			leitor.nextLine(); //Limpa o buffer
			switch(entrada) {
			case 1: //Caso o usuário digite 1
				//Se a lista com seleções não estiver vazia
				if(!s.ListaSelecao().isEmpty()) {
					System.out.println("Digite o número correspondente à ação que deseja tomar");
					System.out.println("1-Criar Seleção\n2-Editar Seleção\n3-Remover Seleção\n4-Listar Seleções");
					System.out.println("Opção:");
					entrada = leitor.nextInt(); //Recebe entrada do usuário
					leitor.nextLine(); //Limpando Buffer
					switch(entrada) {
					case 1: //Caso queira criar seleção
						criarSelecao(j, s, t);
						break;
					case 2: //Editar seleção
						editarSelecao(j, s, t);
						break;
					case 3:
						removerSelecao(j, s, t);
						break;
					case 4: //Caso seja escolhido listar seleções
						listarSelecoesOuComponentes(j, s, t);
						break;
					default:
						System.out.println("Você digitou um valor inválido");
						break;
					}
				}else { //Se não tiver nenhuma seleção cadastrada, cadastrar uma
					criarSelecao(j, s, t);
				}
				break;
			case 2:
				//Se a lista com árbitros não estiver vazia
				if(!a.ListaArbitro().isEmpty()) {
					System.out.println("Digite o número correspondente à ação que deseja tomar");
					System.out.println("1-Criar árbitro\n2-Editar árbitro\n3-Remover árbitro\n4-Listar árbitros");
					entrada = leitor.nextInt(); //Recebe a entrada do usuário
					leitor.nextLine(); //Limpa buffer
					switch(entrada) {
					case 1: //Se quiser criar árbitro
						criarArbitro(a);
						break;
					case 2: //Se quiser editar árbitro
						editarArbitro(a);
						break;
					case 3: //Se quiser remover árbitro
						removerArbitro(a);
						break;
					case 4: //Se quiser listar os árbitros
						listarArbitros(a);
						break;
					default: //Se não escolher nenhuma das opções
						System.out.println("Você escolheu nenhuma das quatro opções");
						break;
					}
				}else { //Se a lista com árbitros estiver vazia
					criarArbitro(a);
				}
				break;
			case 3: //Para listar os técnicos da copa
				listarTecnicos(t);
				break;
			case 4: //Para listar os jogadores da copa
				listarJogadores(j);
				break;
			default:
				//Encerra o menu e sai do loop
				loop = false;
				break;
			}
		}
		System.out.println("Obrigado por usar o SysCopa!!!");
		leitor.close();
	}
}