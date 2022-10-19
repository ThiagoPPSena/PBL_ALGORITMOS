/*
Autor: Luan Barbosa dos Santos Costa e Thiago Pinto Pereira Sena
Componente Curricular: EXA863 - MI Programação
Concluido em: 26/09/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
*/

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
	
	static Scanner leitor = new Scanner(System.in); //Objeto para entrada de dados
	static ArbitroDAO arbitroDAO;
	static JogadorDAO jogadorDAO;
	static SelecaoDAO selecaoDAO;
	static TecnicoDAO tecnicoDAO;
	
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
	public static void criarArbitro() {
		
		String nome;
		
		System.out.println("Qual o nome do árbitro?");
		nome = leitor.nextLine(); //Recebe o nome do árbitro
		Arbitro arb = new Arbitro(nome); //Instancia um objeto do tipo Arbitro
		arbitroDAO.InserirArbitro(arb); //Insere na coleção de árbitros no ArbitroDAO
		System.out.println("\nÁrbitro criado!!!");
		
	}
	
	//Função para listar todos os árbitros cadastrados
	public static void listarArbitros() {
		//Percorre toda a colção de árbitros, printando os códigos e seus respectivos nome
		for (int key : arbitroDAO.ListaArbitro().keySet()) {

            Arbitro value = arbitroDAO.ListaArbitro().get(key);
            System.out.println("Código "+key + ": " + value.getNome());
		}
	}
	
	//Função para editar um dos árbitros cadastrados
	public static void editarArbitro() {
		
		int cod;
		String nome;
		
		listarArbitros(); //Lista todos os árbitros 
		System.out.println("Digite o código do árbitro a ser editado");
		System.out.println("Código:");
		cod = leitor.nextInt(); //O usuário digita o código do árbitro a ser removido
		leitor.nextLine(); //Limpa o buffer
		//Se o árbitro com esse código estiver na coleção...
		if(arbitroDAO.ListaArbitro().get(cod) != null) {
			System.out.println("Digite o nome editado:");
			nome = leitor.nextLine(); //Recebe o nome editado do árbitro
			arbitroDAO.ListaArbitro().get(cod).setNome(nome); //Altera o nome na coleção
			System.out.println("\nÁrbitro editado!!!");
		//Se o árbitro com esse código não for encontrado...
		}else {
			System.out.println("Árbitro não encontrado!!!");
		}
	}
	
	//Função para remover um dos árbitros cadastrados
	public static void removerArbitro() {
		
		int cod;
		
		listarArbitros(); //Lista todos os árbitros
		System.out.println("Digite o código do árbitro a ser removido:");
		cod = leitor.nextInt(); //O usuário digita o código do árbitro a ser removido
		leitor.nextLine(); //Limpa o buffer
		if(arbitroDAO.RemoverArbitro(cod) != null) //Se for encontrado o árbitro, ele será removido
			System.out.println("\nÁrbitro removido!!!");
		else //Se não encontrar o árbitro...
			System.out.println("Não há nenhum árbitro com esse código");
		
	}
	
	//Função para listar todos os técnicos cadastrados
	public static void listarTecnicos() {
		//Percorre toda a colção de técnicos, printando os códigos e seus respectivos nome
		for (int key : tecnicoDAO.ListaTecnico().keySet()) {

            Tecnico value = tecnicoDAO.ListaTecnico().get(key);
            System.out.println("Código "+key + ": " + value.getNome());
		}
	}
	
	//Função para listar todos os jogadores cadastrados
	public static void listarJogadores() {
		//Percorre toda a colção de jogadores, printando os códigos e seus respectivos dados
		for (int key : jogadorDAO.ListaJog().keySet()) {

            Jogador value = jogadorDAO.ListaJog().get(key);
            System.out.println("Código "+key + ": " + value);
		}
	}
	
	//Função mais ampla que chama chama outras duas funções: listarSelecoes e listarMembrosSelecao
	public static void listarSelecoesOuComponentes() {
		System.out.println("Qual ação deseja tomar?");
		System.out.println("1-Listar todas as seleções\n2-Listar todos os componentes de uma seleção");
		int entrada = leitor.nextInt(); //Recebe entrada do usuário
		leitor.nextLine();
		switch(entrada) {
		case 1: //Se escolhar listar todas as seleções
			listarSelecoes();
			break;
		case 2: //Caso escolha listar tudo peretecente a uma seleção
			listarSelecoes(); //Lista todas seleções para facilitar a escolha de qual seleção o usuário quer visualizar
			System.out.println("Qual a seleção deseja visualizar? (Digite o código)");
			int codSel = leitor.nextInt(); //Recebe o código da seleção a ser visualizada
			leitor.nextLine(); //Limpa o buffer
			listarMembrosSelecao(codSel); //Lista tudo da seleção caso a encontre
			break;
		default:
			System.out.println("Você digitou um valor inválido");
			break;
		}
	}
	
	//Função para listar todas as seleções cadastradas
 	public static void listarSelecoes() {
		//Percorre toda a colção de seleções, printando os códigos e seus respectivos nome
		for (int key : selecaoDAO.ListaSelecao().keySet()) {
			
            Selecao value = selecaoDAO.ListaSelecao().get(key);
            System.out.println(key + " = " + value.getNome());
		}
	}
	
	//Função para listar todos os componentes de uma seleção escolhida
	public static void listarMembrosSelecao(int cod) {
		Selecao sele = selecaoDAO.BuscarSelecao(cod); //Instancia um objeto do tipo seleção, buscando-a pelo seu código
		if(sele != null) { //Se encontrar a seleção...
			System.out.println("Técnico: "+tecnicoDAO.ListaTecnico().get(sele.getCodTec()).getNome()); //Printa o nome do técnico
			for(int codJog : selecaoDAO.CodJogadoresSelecao(cod)) {
				System.out.println("Código "+codJog+": "+jogadorDAO.BuscarJogador(codJog)); //Printa o nome de todos os jogadores
			}
		}else { //Se não encontrar a seleção...
			System.out.println("Seleção não encontrada!!!");
		}
	}
	
	//Função que cria a seleção e cria seus 11 jogadores e seu técnico
	public static void criarSelecao() {
		
		String nome, posicaoString;
		int posicao;
		
		System.out.println("Qual seleção deseja criar? (Digite o nome)");
		nome = leitor.nextLine(); //Recebe o nome da seleção
		Selecao sel = new Selecao(nome); //Instancia um objeto Selecao
		System.out.println("Agora digite os dados dos 11 jogadores da selecao:");
		for(int i=1; i<12; i++) { //Cria os 11 jogadores da selecao
			System.out.println("Dados do "+i+"º jogador:");
			System.out.println("Nome:");
			nome = leitor.nextLine(); //Recebe o nome do jogador
			System.out.println("Posição (1-Goleiro; 2-Zagueiro; 3-Meia; 4-Atacante):");
			posicao = leitor.nextInt(); //Recebe um inteiro que simboliza sua posicao
			leitor.nextLine(); //Limpa o buffer
			switch(posicao) {
			case 1: //Se digitou 1, sua posição é goleiro
				posicaoString = "Goleiro";
				break;
			case 2: //Se digitou 2, sua posição é zagueiro
				posicaoString = "Zagueiro";
				break;
			case 3: //Se digitou 3, sua posição é meia
				posicaoString = "Meia";
				break;
			case 4: //Se digitou 4, sua posição é atacante
				posicaoString = "Atacante";
				break;
			default: //Se não for digitado nenhum dos inteiros esperados
				System.out.println("Você digitou um valor inválido, a posição dele vai ser colocada como vazia. Altere-a futuramente!");
				posicaoString = "";
				break;
			}
			Jogador jog = new Jogador(nome, posicaoString); //Instancia um novo objeto Jogador
			jogadorDAO.InserirJogador(jog); //Insere no DAO de jogador
			sel.getListaCodJog().add(jog.getCodJog()); //Adiciona seu código à sua respectiva seleção
			System.out.println("\n\nJogador cadastrado com sucesso!!!\n");
		}
		System.out.println("Agora digite nome do técnico:");
		nome = leitor.nextLine(); //Recebe o nome do técnico da seleção
		Tecnico tec = new Tecnico(nome); //Instancia um novo objeto Tecnico
		tecnicoDAO.InserirTecnico(tec); //Insere no DAO  de tecnico
		sel.setCodTec(tec.getCodTec()); //Adiciona seu código à sua respectiva seleção
		selecaoDAO.InserirSelecao(sel); //Insere a seleção já criada na lista do DAO selecao
		System.out.println("\n\nSeleção cadastrada com sucesso!!!\n");
		
	}
	
	//Função que edita a seleção, seus jogadores e seus técnicos
	public static void editarSelecao() {
		listarSelecoes(); //Lista todas as seleções já cadastradas para facilitar que o usuário saiba qual editar
		System.out.println("Qual a seleção a ter seus dados alterados? (digite o código)");
		int cod = leitor.nextInt(); //Recebe o código da seleção a ser editada
		leitor.nextLine(); //Limpa o buffer
		if(selecaoDAO.BuscarSelecao(cod) != null) { //Se encontrar a seleção
			listarMembrosSelecao(cod); //Lista todos os membros da seleção
			System.out.println("Deseja editar o que de seleção?");
			System.out.println("1-Nome de Seleção\n2-Nome do técnico\n3-Dados de algum jogador");
			System.out.println("Escolha:");
			int entrada = leitor.nextInt(); //Recebe entrada do usuário
			leitor.nextLine(); //Limpa o buffer
			switch(entrada) {
			case 1: //Se quiser alterar o nome da seleção
				System.out.println("Qual o novo nome editado da seleção?");
				String nomeSel = leitor.nextLine(); //Recebe o novo nome editado da seleção
				selecaoDAO.BuscarSelecao(cod).setNome(nomeSel); //Seta o novo nome da seleção
				break;
			case 2: //Se for escolhido alterar o nome do técnico
				System.out.println("Qual o novo nome editado do técnico?");
				String nomeTec = leitor.nextLine(); //Recebe o novo nome do técnico
				tecnicoDAO.ListaTecnico().get(selecaoDAO.BuscarSelecao(cod).getCodTec()).setNome(nomeTec);; //Altera o nome do técnico
				break;
			case 3: //Se for escolhido alterar os dados do jogador
				System.out.println("Deseja alterar dados de qual jogador? (digite seu código)");
				int codJog = leitor.nextInt(); //Recebe o código do jogador
				leitor.nextLine(); //Limpa o buffer
				editarJogador(codJog, cod);
				break;
			default:
				System.out.println("Você não digitou nenhuma das opções!!!");
				break;
			}
		}else {
			System.out.println("Seleção não encontrada!!");
		}
	}
	
	//Função que edita um jogador de uma seleção escolhida
	public static void editarJogador(int codJog, int codSel) {
		if(selecaoDAO.CodJogadoresSelecao(codSel).contains(codJog)) { //Se o a seleção escolhida tiver o jogador escolhido para ser editado...
			System.out.println("Deseja alterar o quê de "+jogadorDAO.BuscarJogador(codJog).getNome()+"?");
			System.out.println("1-Nome\n2-Posição\n3-Número de cartões\n4-Número de gols");
			System.out.println("Escolha:"); 
			int escolha = leitor.nextInt(); //O usuário escolhe o que editar do jogador
			leitor.nextLine();
			switch(escolha) {
			case 1: //Se escolher editar nome...
				System.out.println("Qual o novo nome?");
				String nome = leitor.nextLine();
				jogadorDAO.BuscarJogador(codJog).setNome(nome); //Seta o nome no DAO jogador
				System.out.println("Nome alterado com sucesso!");
				break;
			case 2: //Se escolher a posição...
				System.out.println("Qual a nova posição? (1-Goleiro; 2-Zagueiro; 3-Meia; 4-Atacante)");
				String posicaoString;
				int posicao = leitor.nextInt(); //Recebe um inteiro que simboliza a nova posição do jogador
				leitor.nextLine(); //Limpa o buffer
				switch(posicao) {
				case 1: //Se escolheu goleiro...
					posicaoString = "Goleiro";
					break;
				case 2: //Se escolheu zagueiro...
					posicaoString = "Zagueiro";
					break;
				case 3: //Se escolheu meia...
					posicaoString = "Meia";
					break;
				case 4: //Se escolheu atacante...
					posicaoString = "Atacante";
					break;
				default: //Se escolheu nenhuma posição
					System.out.println("Você escolheu nenhuma das posições, a posição do jogador será armazenada como vazia!\n");
					posicaoString = "";
					break;
				}
				jogadorDAO.BuscarJogador(codJog).setPosicao(posicaoString); //Seta a posição do jogador no DAO jogador
				System.out.println("Posição alterada com sucesso!");
				break;
			case 3: //Caso queira editar o número de cartões...
				System.out.println("Quantos cartões amarelos esse jogador tem?");
				int cartAma = leitor.nextInt(); //Recebe a quantidade de cartões amarelo que o jogador tem no total da competição
				System.out.println("Quantos cartões vermelhos esse jogador tem?");
				int cartVer = leitor.nextInt(); //Recebe a quantidade de cartões vermelho que o jogador tem no total da competição
				leitor.nextLine(); //Limpa o buffer
				int[] cartoes = {cartAma, cartVer};
				jogadorDAO.BuscarJogador(codJog).setCartoes(cartoes); //seta mo DAO jogador o número de cartões
				System.out.println("Edição feita com sucesso!");
				break;
			case 4: //Caso tenha escolhido editar o número de gols...
				System.out.println("Quantos gols esse jogador tem?");
				int gols = leitor.nextInt(); //Recebe o número de gols total do jogador na competição
				leitor.nextLine(); //Limpa o buffer
				jogadorDAO.BuscarJogador(codJog).setNumGols(gols); //Seta o npumero de gols do jogador no DAO jogador
				System.out.println("Edição feita com sucesso!");
				break;
			default: //Caso não tenha escolhido nenhuma das opções...
				System.out.println("Você não digitou nenhuma das opções!!!");
				break;
			}
		}else {
			System.out.println("Jogador não encontrado!");
		}
	}
	
	//Função que remove a seleção e seus jogadores e técnico
	public static void removerSelecao() {
		
		listarSelecoes(); //Lista todas as seleções para que o usuário veja quais seleções podem ser removidas
		System.out.println("Digite o código da seleção que deseja remover, sabendo que ao remover uma seleção, estará removendo seus componentes!");
		System.out.println("Código:");
		int cod = leitor.nextInt(); //Recebe o código da seleção a ser removida
		leitor.nextLine(); //Limpa o buffer
		if(selecaoDAO.BuscarSelecao(cod) != null) { //Se a seleção existir...
			for(int i: selecaoDAO.CodJogadoresSelecao(cod)) {
				jogadorDAO.RemoverJogador(i); //Remove todos os jogadores pertencentes a ela que estão salvos no sistema
			}
			tecnicoDAO.RemoverTecnico(selecaoDAO.BuscarSelecao(cod).getCodTec()); //Remove do sistema o técnico atrelado à seleção
			selecaoDAO.RemoverSelecao(cod); //Por fim, remove a seleção
			System.out.println("Seleção removida com sucesso!");
		}else { //Se a seleção não existe
			System.out.println("Você digitou um código inválido!!!");
		}
		
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
						criarSelecao();
						break;
					case 2: //Editar seleção
						editarSelecao();
						break;
					case 3:
						removerSelecao();
						break;
					case 4: //Caso seja escolhido listar seleções
						listarSelecoesOuComponentes();
						break;
					default:
						System.out.println("Você digitou um valor inválido");
						break;
					}
				}else { //Se não tiver nenhuma seleção cadastrada, cadastrar uma
					criarSelecao();
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
						criarArbitro();
						break;
					case 2: //Se quiser editar árbitro
						editarArbitro();
						break;
					case 3: //Se quiser remover árbitro
						removerArbitro();
						break;
					case 4: //Se quiser listar os árbitros
						listarArbitros();
						break;
					default: //Se não escolher nenhuma das opções
						System.out.println("Você escolheu nenhuma das quatro opções");
						break;
					}
				}else { //Se a lista com árbitros estiver vazia
					criarArbitro();
				}
				break;
			case 3: //Para listar os técnicos da copa
				listarTecnicos();
				break;
			case 4: //Para listar os jogadores da copa
				listarJogadores();
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