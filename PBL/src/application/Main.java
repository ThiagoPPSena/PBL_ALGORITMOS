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
import application.model.FaseGrupo;
import application.model.Grupo;
import application.model.JogPartida;
import application.model.Jogador;
import application.model.JogadorDAO;
import application.model.Partida;
import application.model.Selecao;
import application.model.SelecaoDAO;
import application.model.Tecnico;
import application.model.TecnicoDAO;
import execoes.LimiteSelecoesException;
import execoes.QuantidadeSelecoesIncompletaException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	static Scanner leitor = new Scanner(System.in); //Objeto para entrada de dados
	static FaseGrupo faseGrupo = new FaseGrupo();
	static ArbitroDAO arbitroDAO = new ArbitroDAO();
	static JogadorDAO jogadorDAO = new JogadorDAO();
	static SelecaoDAO selecaoDAO = new SelecaoDAO();
	static TecnicoDAO tecnicoDAO = new TecnicoDAO();
	
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
	
	//Função que cria as partidas de um grupo
	public static void criarPartidas() {
		//Laço que percorre a lista dos 8 grupos
		for(int i=0; i<8; i++) {
			
			System.out.println("Digite alguns dados das partidas do grupo "+(i+1));
			Grupo grupo = faseGrupo.buscarGrupo(i);//Recebe o grupo na posição i
			Partida[] partidas = grupo.getPartidas();//Pega as partidas daquele grupo
			for(Partida partida : partidas) {//Percorre as partidas
				//Pega as duas seleções daquela partida
				Selecao selecao1 = selecaoDAO.BuscarSelecao(partida.getCodTime1());
				Selecao selecao2 = selecaoDAO.BuscarSelecao(partida.getCodTime2());
				//Cria a lista de códigos dos jogadores
				List<Integer> listaCodJog_1 = selecao1.getListaCodJog();
				List<Integer> listaCodJog_2 = selecao2.getListaCodJog();
				List<Integer> listaCod = new ArrayList<Integer>();
				List<JogPartida> listaJogPart = new ArrayList<JogPartida>();
				listaCod.addAll(listaCodJog_1);
				listaCod.addAll(listaCodJog_2);
				for(int j=0;j<listaCod.size();j++) {
					int codigoJog = listaCod.get(j);
					JogPartida jogador = new JogPartida();
					jogador.setCodJogador(codigoJog);
					listaJogPart.add(jogador);
				}
				partida.setJogadores(listaJogPart);
				//Pergunta o local e seta o local
				System.out.println("Partida entre " + selecao1.getNome() + " e " + selecao2.getNome());
				System.out.println("Digite o local:");
				String local = leitor.nextLine();
				partida.setLocal(local);
				//Pergunta o ano e seta o ano
				int ano = 1924;
				while(ano < 1930) {
					System.out.println("Digite o ano:");
					try {
						ano = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(ano < 1930)
						System.out.println("Digite novamente!Ano deve ser a partir de 1930");
				}
				//Pergunta o mês e seta o mês
				int mes = 0;
				while(mes < 1 || mes > 12) {
					System.out.println("Digite o número do mes (Ex. 1-Janeiro):");
					try {
						mes = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(mes < 1 || mes > 12)
						System.out.println("Digite novamente!Digite entre 1 e 12");
				}
				//Pergunta o dia e seta o dia
				int dia = 0;
				while(dia < 1 || dia > 31) {
					System.out.println("Digite o dia:");
					try {
						dia = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inálida!");
					}
					if(dia < 1 || dia > 31)
						System.out.println("Digite novamente!Digite entre 1 e 31");
				}
				partida.setData(ano, mes, dia);
				System.out.println("Digite o horário:");
				//Pergunta as horas e o minutos
				int horas = 25;
				int minutos = 60;
				while(horas < 0 || horas > 23) {
					System.out.println("Digite as horas:");
					try {
						horas = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(horas < 0 || horas > 23)
						System.out.println("Digite novamente!Digite entre 0 e 23");
				}
				while(minutos < 0 || minutos > 59) {
					System.out.println("Digite os minutos:");
					try {
						minutos = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(minutos < 0 || minutos > 59)
						System.out.println("Digite novamente!Digite entre 0 e 59");
				}
				int[] horario = {horas, minutos};
				partida.setHorario(horario);
				System.out.println(
						"Agora, olhando a lista de árbitros abaixo, digite o código daquele que abitará esta partida");
				listarArbitros();
				System.out.println("Código: ");
				//Pergunta qual o código do árbitro que apitará o jogo
				boolean entradaInvalida = true;
				int codArbitro = 0;
				while (entradaInvalida) {
					try {
						codArbitro = leitor.nextInt();
						leitor.nextLine();
						entradaInvalida = false;
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida! Insira novamente:");
					}
				}
				entradaInvalida = true;
				boolean loop = true;
				while(loop) {
					if(arbitroDAO.ListaArbitro().containsKey(codArbitro)) {
						partida.setCodArbitro(codArbitro);
						loop = false;
					}else {
						System.out.println("Código do árbitro inválido! Digite um código válido");
						while (entradaInvalida) {
							try {
								codArbitro = leitor.nextInt();
								leitor.nextLine();
								entradaInvalida = false;
							}catch(Exception e) {
								leitor.nextLine();
								System.out.println("Entrada inválida! Insira novamente:");
							}
						}
					}
				}
			}
		}
	} 
	
	//Função que lista as partidas de um grupo
	public static void listarPartidas() {
		
		System.out.println("Digite o código do grupo que deseja listar as partidas (0-7)");
		int cod;//Solicita o código do grupo que deseja listar as partidas
		try {
			cod = leitor.nextInt();
			leitor.nextLine();
		}catch(Exception e) {
			leitor.nextLine();
			cod = -1;
		}
		//O código dever ser de 0 a 7
		if (cod >= 0 && cod < 8) {
			Grupo grupo = faseGrupo.buscarGrupo(cod);
			for (Partida partida : grupo.getPartidas()) {
				Selecao selecao1 = selecaoDAO.BuscarSelecao(partida.getCodTime1());
				Selecao selecao2 = selecaoDAO.BuscarSelecao(partida.getCodTime2());
				System.out.println("Partida entre " + selecao1.getNome() + " e " + selecao2.getNome() + ":");
				System.out.println("Placar, respectivamente: "+partida.getGolsTime1()+"X"+partida.getCodTime2());
				System.out.println("Local: "+partida.getLocal());
				System.out.println("Horario: "+partida.getHorario());
				System.out.println("Data: "+partida.getData());
			}
		} else {
			System.out.println("Código inválido!!!");
		}

	}
	
	//Função que editar os dados da partida
	public static void editarPartidas() {
		List<Integer> listaCodPartidas = new ArrayList<Integer>();
		System.out.println("Digite o código do grupo (0-7) da partida que deseja editar:");
		int codGrupo; //Solicita o código do grupo a ter sua partida editada
		try {
			codGrupo = leitor.nextInt();
			leitor.nextLine();
		}catch(Exception e) {
			leitor.nextLine();
			codGrupo = -1;
		}
		if (codGrupo >= 0 && codGrupo < 8) {
			//Lista as partidas daquele grupo
			Grupo grupo = faseGrupo.buscarGrupo(codGrupo);
			for (Partida partida : grupo.getPartidas()) {
				listaCodPartidas.add(partida.getCodPartida());
				Selecao selecao1 = selecaoDAO.BuscarSelecao(partida.getCodTime1());
				Selecao selecao2 = selecaoDAO.BuscarSelecao(partida.getCodTime2());
				System.out.println(partida.getCodPartida()+" = Partida entre " + selecao1.getNome() + " e " + selecao2.getNome());
			}
			System.out.println("Digite o código da partida que deseja editar:");
			int codPart;//Digita o código da partida aser editada
			try {
				codPart = leitor.nextInt();
				leitor.nextLine();
			}catch(Exception e) {
				leitor.nextLine();
				codPart = -1;
			}//Se o código digitado for daquele grupo...
			if(listaCodPartidas.contains(codPart)) {
				int indexPart = grupo.buscarPartida(codPart);
				Partida partida = grupo.getPartidas()[indexPart];//Pega a partida e edita os dados da partida
				System.out.println("Editar partida "+partida.getCodPartida());
				System.out.println("Caso queira manter a mesma informação, reescreva-a da mesma forma que a anterior");
				System.out.println("Digite o local editado(local atual: "+partida.getLocal()+")");
				String local = leitor.nextLine();
				partida.setLocal(local);
				System.out.println("Digite a data editada(data atual: "+partida.getData()[0]+"/"+partida.getData()[1]+"/"+partida.getData()[2]+")");
				int ano = 1924;
				while(ano < 1930) {
					System.out.println("Digite o ano:");
					try {
						ano = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(ano < 1930)
						System.out.println("Digite novamente!Ano deve ser a partir de 1930");
				}
				int mes = 0;
				while(mes < 1 || mes > 12) {
					System.out.println("Digite o número do mes (Ex. 1-Janeiro):");
					try {
						mes = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(mes < 1 || mes > 12)
						System.out.println("Digite novamente!Digite entre 1 e 12");
				}
				int dia = 0;
				while(dia < 1 || dia > 31) {
					System.out.println("Digite o dia:");
					try {
						dia = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inálida!");
					}
					if(dia < 1 || dia > 31)
						System.out.println("Digite novamente!Digite entre 1 e 31");
				}
				partida.setData(ano, mes, dia);
				System.out.println("Digite o horário:");
				int horas = 25;
				int minutos = 61;
				while(horas < 0 || horas > 23) {
					System.out.println("Digite as horas:");
					try {
						horas = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(horas < 0 || horas > 23)
						System.out.println("Digite novamente!Digite entre 0 e 23");
				}
				while(minutos < 0 || minutos > 59) {
					System.out.println("Digite os minutos:");
					try {
						minutos = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						System.out.println("Entrada inválida!");
					}
					if(minutos < 0 || minutos > 59)
						System.out.println("Digite novamente!Digite entre 0 e 59");
				}
				int[] horario = {horas, minutos};
				partida.setHorario(horario);
			}else
				System.out.println("Código inválido!!!");
				
		}else {
			System.out.println("Código inválido!!!");
		}
		
	}
	
	
	public static void editarJogPartida() {
		List<Integer> listaCodPartidas = new ArrayList<Integer>();
		System.out.println("Digite o código do grupo (0-7) da partida que deseja editar:");
		int codGrupo;//Solicita o código do grupo
		try {
			codGrupo = leitor.nextInt();
			leitor.nextLine();
		}catch(Exception e) {
			leitor.nextLine();
			codGrupo = -1;
		}
		if (codGrupo >= 0 && codGrupo < 8) {
			Grupo grupo = faseGrupo.buscarGrupo(codGrupo);
			for (Partida partida : grupo.getPartidas()) {//Lista todas as partidas do grupo
				listaCodPartidas.add(partida.getCodPartida());
				Selecao selecao1 = selecaoDAO.BuscarSelecao(partida.getCodTime1());
				Selecao selecao2 = selecaoDAO.BuscarSelecao(partida.getCodTime2());
				System.out.println(partida.getCodPartida()+" = Partida entre " + selecao1.getNome() + " e " + selecao2.getNome());
			}
			System.out.println("Digite o código da partida que deseja editar seus jogadores:");
			int codPart;//Solicita o código da partida
			try {
				codPart = leitor.nextInt();
				leitor.nextLine();
			}catch(Exception e) {
				leitor.nextLine();
				codPart = -1;
			}
			if(listaCodPartidas.contains(codPart)) {//Se a partida pertence ao grupo...
				int indexPart = grupo.buscarPartida(codPart);
				Partida partida = grupo.getPartidas()[indexPart];
				System.out.println("Digite o código do jogador que deseja editar:");
				for(JogPartida jogadorPart:partida.getJogadores()) {//Lista todos os jogadores da partida
					int codJogador = jogadorPart.getCodJogador();
					System.out.println("Código: "+codJogador+";Nome: "+jogadorDAO.BuscarJogador(codJogador).getNome());
				}
				int codJogador;//Solicita o código do jogador
				try {
					codJogador = leitor.nextInt();
					leitor.nextLine();
				}catch(Exception e) {
					leitor.nextLine();
					codJogador = -1;
				}
				JogPartida jogPartida = partida.buscarJogPartida(codJogador);
				if(jogPartida != null) {//Se encontrou o jogador, pede para editá-lo
					System.out.println("Editar jogador "+jogadorDAO.BuscarJogador(codJogador).getNome());
					System.out.println("Caso queira manter a mesma informação, reescreva-a da mesma forma que a anterior");
					System.out.println("Digite o número de gols na partida(gols atuais: "+jogPartida.getGols()+")");
					int gols;
					try {
						gols = leitor.nextInt();
						leitor.nextLine();
					}catch(Exception e) {
						leitor.nextLine();
						gols = jogPartida.getGols();
					}
					jogPartida.setGols(gols);
					int cartAma = -1;
					while(cartAma < 0 || cartAma > 2) {
						System.out.println("Digite o número de cartões amarelos na partida(cartões atuais: "+jogPartida.getCartoesAma()+")");
						try {
							cartAma = leitor.nextInt();
							leitor.nextLine();
						}catch(Exception e) {
							leitor.nextLine();
							cartAma = -1;
						}
						if(cartAma < 0 || cartAma > 2) {
							System.out.println("Digite novamente!Ou 0, ou 1, ou 2");
						}
					}
					int cartVer = -1;
					while(cartVer != 0 && cartVer != 1) {
						System.out.println("Digite o número de cartões Vermelhos na partida(cartões atuais: "+jogPartida.getCartoesVer()+")");
						try {
							cartVer = leitor.nextInt();
							leitor.nextLine();
						}catch(Exception e) {
							leitor.nextLine();
							cartVer = -1;
						}
						if(cartVer != 1 && cartVer != 1) {
							System.out.println("Digite novamente!Ou 0, ou 1");
						}
					}
					jogPartida.setCartoesAma(cartAma);
					jogPartida.setCartoesVer(cartVer);
				}else {
					System.out.println("O jogador com esse código não pertence a essa partida ou não existe!!!");
				}
				
			}else {
				System.out.println("A partida com esse código não pertence a esse grupo ou não existe!!!");
			}
		}else {
			System.out.println("Código inválido!!!");
		}
	}
	
	public static void pesquisarPorData() {//Pesquisa por data
		System.out.println("Digite uma data: ");//Solicita o ano, mês e dia
		int ano = 1924;
		while(ano < 1930) {
			System.out.println("Digite o ano:");
			try {
				ano = leitor.nextInt();
				leitor.nextLine();
			}catch(Exception e) {
				leitor.nextLine();
				System.out.println("Entrada inválida!");
			}
			if(ano < 1930)
				System.out.println("Digite novamente!Ano deve ser a partir de 1930");
		}
		int mes = 0;
		while(mes < 1 || mes > 12) {
			System.out.println("Digite o número do mes (Ex. 1-Janeiro):");
			try {
				mes = leitor.nextInt();
				leitor.nextLine();
			}catch(Exception e) {
				leitor.nextLine();
				System.out.println("Entrada inválida!");
			}
			if(mes < 1 || mes > 12)
				System.out.println("Digite novamente!Digite entre 1 e 12");
		}
		int dia = 0;
		while(dia < 1 || dia > 31) {
			System.out.println("Digite o dia:");
			try {
				dia = leitor.nextInt();
				leitor.nextLine();
			}catch(Exception e) {
				leitor.nextLine();
				System.out.println("Entrada inálida!");
			}
			if(dia < 1 || dia > 31)
				System.out.println("Digite novamente!Digite entre 1 e 31");
		}
		for(Grupo grupo:faseGrupo.getGrupos()) {//A partir da data, pesquisa todas as partidas daquela data
			for(Partida partida:grupo.getPartidas()) {
				if(partida.getData()[0] == dia && partida.getData()[1] == mes && partida.getData()[2] == ano) {
					Selecao selecao1 = selecaoDAO.BuscarSelecao(partida.getCodTime1());
					Selecao selecao2 = selecaoDAO.BuscarSelecao(partida.getCodTime2());
					System.out.println(partida.getCodPartida()+" = Partida entre " + selecao1.getNome() + " e " + selecao2.getNome());
					System.out.println("Local: "+partida.getLocal());
					System.out.println("Horário: "+partida.getHorario()[0]+"h"+partida.getHorario()[1]+"m");
				}
			}
		}
		
	}
	
	public static void pesquisarPorCategoria() {
		System.out.println("Digite o nome que deseja pesquisar:");//Solicita o nome a ser pesquisado
		String nome = leitor.nextLine();
		System.out.println("Digite o número correspondente a qual categoria quer pesquisar:");//Pergunta qual é a categoria
		System.out.println("1-Jogador\n2-Técnico\n3-Árbitro");
		int escolha;//Através da escolha, pesquisa todos os objetos com o nome especificado e a categoria
		try {
			escolha = leitor.nextInt();
			leitor.nextLine();
		}catch(Exception e) {
			leitor.nextLine();
			escolha = 0;
		}
		switch(escolha) {
		case 1:
			for(Jogador jogador:jogadorDAO.ListaJog().values()) {
				if(jogador.getNome().equals(nome))
					System.out.println(jogador);
			}
			break;
		case 2:
			for(Tecnico tecnico:tecnicoDAO.ListaTecnico().values()) {
				if(tecnico.getNome().equals(nome))
					System.out.println("Código: "+tecnico.getCodTec()+"; Nome: "+tecnico.getNome());
			}
			break;
		case 3:
			for (Arbitro arbitro : arbitroDAO.ListaArbitro().values()) {
				if (arbitro.getNome().equals(nome))
					System.out.println("Código: " + arbitro.getCodArbitro() + "; Nome: " + arbitro.getNome());
			}
			break;
		default:
			System.out.println("Você selecionou uma categoria inválida!!!");
			break;
			
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
		try {
			cod = leitor.nextInt(); //O usuário digita o código do árbitro a ser removido
			leitor.nextLine(); //Limpa o buffer
		}catch(Exception e) {
			leitor.nextLine();
			cod = -1;
		}
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
		try {
			cod = leitor.nextInt(); //O usuário digita o código do árbitro a ser removido
			leitor.nextLine(); //Limpa o buffer
		}catch(Exception e) {
			leitor.nextLine();
			cod = -1;
		}
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
		int entrada;
		try {
			entrada = leitor.nextInt(); //Recebe entrada do usuário
			leitor.nextLine();
		}catch(Exception e) {
			leitor.nextLine();
			entrada = 0;
		}
		switch(entrada) {
		case 1: //Se escolhar listar todas as seleções
			listarSelecoes();
			break;
		case 2: //Caso escolha listar tudo peretecente a uma seleção
			listarSelecoes(); //Lista todas seleções para facilitar a escolha de qual seleção o usuário quer visualizar
			System.out.println("Qual a seleção deseja visualizar? (Digite o código)");
			int codSel;
			try {
				codSel = leitor.nextInt(); //Recebe o código da seleção a ser visualizada
				leitor.nextLine(); //Limpa o buffer
			}catch(Exception e) {
				leitor.nextLine();
				codSel = -1;
			}
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
		try {
			selecaoDAO.InserirSelecao(sel); //Insere a seleção já criada na lista do DAO selecao
			System.out.println("Agora digite os dados dos 11 jogadores da selecao:");
			for(int i=1; i<12; i++) { //Cria os 11 jogadores da selecao
				System.out.println("Dados do "+i+"º jogador:");
				System.out.println("Nome:");
				nome = leitor.nextLine(); //Recebe o nome do jogador
				System.out.println("Posição (1-Goleiro; 2-Zagueiro; 3-Meia; 4-Atacante):");
				try {
					posicao = leitor.nextInt(); //Recebe um inteiro que simboliza sua posicao
					leitor.nextLine(); //Limpa o buffer
				}catch(Exception e) {
					leitor.nextLine();
					posicao = 0;
				}
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
				selecaoDAO.BuscarSelecao(sel.getCodSel()).getListaCodJog().add(jog.getCodJog()); //Adiciona seu código à sua respectiva seleção
				System.out.println("\n\nJogador cadastrado com sucesso!!!\n");
			}
			System.out.println("Agora digite nome do técnico:");
			nome = leitor.nextLine(); //Recebe o nome do técnico da seleção
			Tecnico tec = new Tecnico(nome); //Instancia um novo objeto Tecnico
			tecnicoDAO.InserirTecnico(tec); //Insere no DAO  de tecnico
			selecaoDAO.BuscarSelecao(sel.getCodSel()).setCodTec(tec.getCodTec()); //Adiciona seu código à sua respectiva seleção
			
			System.out.println("\n\nSeleção cadastrada com sucesso!!!\n");
		}catch(LimiteSelecoesException except) {
			System.out.println(except.getMessage());
		}
		
	}
	
	//Função que edita a seleção, seus jogadores e seus técnicos
	public static void editarSelecao() {
		listarSelecoes(); //Lista todas as seleções já cadastradas para facilitar que o usuário saiba qual editar
		System.out.println("Qual a seleção a ter seus dados alterados? (digite o código)");
		int cod;
		try {
			cod = leitor.nextInt(); //Recebe o código da seleção a ser editada
			leitor.nextLine(); //Limpa o buffer
		}catch(Exception e) {
			leitor.nextLine();
			cod = -1;
		}
		if(selecaoDAO.BuscarSelecao(cod) != null) { //Se encontrar a seleção
			listarMembrosSelecao(cod); //Lista todos os membros da seleção
			System.out.println("Deseja editar o que de seleção?");
			System.out.println("1-Nome de Seleção\n2-Nome do técnico\n3-Dados de algum jogador");
			System.out.println("Escolha:");
			int entrada;
			try {
				entrada = leitor.nextInt(); //Recebe entrada do usuário
				leitor.nextLine(); //Limpa o buffer
			}catch(Exception e) {
				leitor.nextLine();
				entrada = 0;
			}
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
				int codJog;
				try {
					codJog = leitor.nextInt(); //Recebe o código do jogador
					leitor.nextLine(); //Limpa o buffer
				}catch(Exception e) {
					leitor.nextLine();
					codJog = -1;
				}
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
			int escolha;
			try {
				escolha = leitor.nextInt(); //O usuário escolhe o que editar do jogador
				leitor.nextLine();
			}catch(Exception e) {
				leitor.nextLine();
				escolha = 0;
			}
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
				int posicao;
				try {
					posicao = leitor.nextInt(); //Recebe um inteiro que simboliza a nova posição do jogador
					leitor.nextLine(); //Limpa o buffer
				}catch(Exception e) {
					leitor.nextLine();
					posicao = 0;
				}
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
				int cartAma;
				try {
					cartAma = leitor.nextInt(); //Recebe a quantidade de cartões amarelo que o jogador tem no total da competição
					leitor.nextLine();
				}catch(Exception e) {
					leitor.nextLine();
					System.out.println("Entrada inválida. Cartões amarelos setados automaticamente para 0.");
					cartAma = 0;
				}
				System.out.println("Quantos cartões vermelhos esse jogador tem?");
				int cartVer;
				try {
					cartVer = leitor.nextInt(); //Recebe a quantidade de cartões vermelho que o jogador tem no total da competição
					leitor.nextLine();
				}catch(Exception e) {
					leitor.nextLine();
					System.out.println("Entrada inválida. Cartões vermelhos setados automaticamente para 0.");
					cartVer = 0;
				}
				int[] cartoes = {cartAma, cartVer};
				jogadorDAO.BuscarJogador(codJog).setCartoes(cartoes); //seta mo DAO jogador o número de cartões
				System.out.println("Edição feita com sucesso!");
				break;
			case 4: //Caso tenha escolhido editar o número de gols...
				System.out.println("Quantos gols esse jogador tem?");
				int gols;
				try {
					gols = leitor.nextInt(); //Recebe o número de gols total do jogador na competição
					leitor.nextLine();
				}catch(Exception e) {
					leitor.nextLine();
					System.out.println("Entrada inválida. Número de gols setado automaticamente para 0.");
					gols = 0;
				}
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
		int cod;
		try {
			cod = leitor.nextInt(); //Recebe o código da seleção a ser removida
			leitor.nextLine(); //Limpa o buffer
		}catch(Exception e) {
			leitor.nextLine();
			cod = -1;
		}
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
	
	//Função que exibe um menu de opções ao usuário.
	//Menu antes do início da fase de grupos.
	public static boolean menu1() {
		int entrada;
		boolean continuar = true;
		
		System.out.println("Escolha qual ação deseja tomar:");
		//Denrtro de seleção, pode-se manipular os seus jogadores e seu técnico
		System.out.println("1-Manipular seleção, seus jogadores e técnico\n2-Manipular árbitro\n3-Listar técnicos\n4-Listar jogadores\n5-Iniciar fase de grupos\n6-Sair");
		try {
			entrada = leitor.nextInt();
			leitor.nextLine(); //Limpa o buffer
		}catch(Exception e) {
			leitor.nextLine();
			entrada = 0;
		}
		switch(entrada) {
		case 1: //Caso o usuário digite 1
			//Se a lista com seleções não estiver vazia
			if(!selecaoDAO.ListaSelecao().isEmpty()) {
				System.out.println("Digite o número correspondente à ação que deseja tomar");
				System.out.println("1-Criar Seleção\n2-Editar Seleção\n3-Remover Seleção\n4-Listar Seleções");
				System.out.println("Opção:");
				try {
					entrada = leitor.nextInt(); //Recebe entrada do usuário
					leitor.nextLine(); //Limpando Buffer
				}catch(Exception e) {
					leitor.nextLine();
					entrada = 0;
				}
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
			if(!arbitroDAO.ListaArbitro().isEmpty()) {
				System.out.println("Digite o número correspondente à ação que deseja tomar");
				System.out.println("1-Criar árbitro\n2-Editar árbitro\n3-Remover árbitro\n4-Listar árbitros");
				try {
					entrada = leitor.nextInt(); //Recebe a entrada do usuário
					leitor.nextLine(); //Limpa buffer
				}catch(Exception e) {
					leitor.nextLine();
					entrada = 0;
				}
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
		case 5: //Para iniciar a fase de grupos
			faseGrupo.updateSelecoes(selecaoDAO.ListaSelecao());
			try {
				faseGrupo.iniciarFase(arbitroDAO);
				criarPartidas();
			}catch(Exception except) {
				System.out.println(except.getMessage());
			}
			break;
		default:
			//Encerra o menu e sai do loop
			continuar = false;
			break;
		}
		return continuar;
	}

	//Função que exibe um menu de opções ao usuário.
	//Menu após o início da fase de grupos.
	public static boolean menu2() {
		int entrada;
		boolean continuar = true;
		
		System.out.println("Escolha qual ação deseja tomar:");
		//Denrtro de seleção, pode-se manipular os seus jogadores e seu técnico
		System.out.println("1-Manipular seleção, seus jogadores e técnico\n2-Manipular árbitro\n3-Listar técnicos\n"
				+ "4-Listar jogadores\n5-Listar partidas de um dos grupos\n6-Editar partida\n"
				+ "7-Editar jogador em uma partida\n8-Pesquisar partidas por data\n"
				+ "9-Pesquisar alguém por categoria\n10-Encerrar fase de grupos\n11-Sair");
		try {
			entrada = leitor.nextInt();
			leitor.nextLine(); //Limpa o buffer
		}catch(Exception e) {
			leitor.nextLine();
			entrada = 0;
		}
		switch(entrada) {
		case 1: //Caso o usuário digite 1
			System.out.println("Digite o número correspondente à ação que deseja tomar");
			System.out.println("1-Editar Seleção\n2-Listar Seleções");
			System.out.println("Opção:");
			try {
				entrada = leitor.nextInt(); //Recebe entrada do usuário
				leitor.nextLine(); //Limpando Buffer
			}catch(Exception e) {
				leitor.nextLine();
				entrada = 0;
			}
			switch(entrada) {
			case 1: //Caso seja escolhido Editar seleção
				editarSelecao();
				break;
			case 2: //Caso seja escolhido Listar seleções
				listarSelecoesOuComponentes();
				break;
			default:
				System.out.println("Você digitou um valor inválido");
				break;
			}
			break;
		case 2:
			System.out.println("Digite o número correspondente à ação que deseja tomar");
			System.out.println("1-Editar árbitro\n2-Listar árbitros");
			try {
				entrada = leitor.nextInt(); //Recebe a entrada do usuário
				leitor.nextLine(); //Limpa buffer
			}catch(Exception e) {
				leitor.nextLine();
				entrada = 0;
			}
			switch(entrada) {
			case 1: //Se quiser editar árbitro
				editarArbitro();
				break;
			case 2: //Se quiser listar os árbitros
				listarArbitros();
				break;
			default: //Se não escolher nenhuma das opções
				System.out.println("Você escolheu nenhuma das quatro opções");
				break;
			}
			break;
		case 3: //Para listar os técnicos da copa
			listarTecnicos();
			break;
		case 4: //Para listar os jogadores da copa
			listarJogadores();
			break;
		case 5: //Para listar partidas de um dos grupos
			listarPartidas();
			break;
		case 6:
			editarPartidas();
			break;
		case 7:
			editarJogPartida();
			break;
		case 8:
			pesquisarPorData();
			break;
		case 9:
			pesquisarPorCategoria();
			break;
		case 10: //Para encerrar a fase de grupos
			faseGrupo.updateSelecoes(selecaoDAO.ListaSelecao());
			faseGrupo.updateJogadores(jogadorDAO);
			faseGrupo.setFaseEncerrada(true);
			break;
		default:
			//Encerra o menu e sai do loop
			continuar = false;
			break;
		}
		return continuar;
	}

	public static void main(String[] args) {
		//launch(args);
		boolean loop = true;
		
		System.out.println("Bem-vindo ao SysCopa! Este é o seu menu\n");
		
		//Loop dos menus
		while(loop) {
			if (faseGrupo.isFaseIniciada() == false) {
				loop = menu1();
			}else if (faseGrupo.isFaseEncerrada() == false){
				loop = menu2();
			}else {
				List<Integer> selecoesPosFaseGrupo = faseGrupo.encerrarFase();
				System.out.println("\nFase de Grupos encerrada. Seleções que passaram:\n");
				for (int cod: selecoesPosFaseGrupo) {
					System.out.println(selecaoDAO.BuscarSelecao(cod).getCodSel() + "=" + selecaoDAO.BuscarSelecao(cod).getNome());
				}
				loop = false;
			}
		}
		System.out.println("Obrigado por usar o SysCopa!!!");
		leitor.close();
	}
}