package br.com.fiap.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import javax.xml.ws.WebServiceException;

import br.com.fiap.repository.JogoRepository;
import br.com.fiap.to.JogoTO;

public class ConsoleView {

	public static Scanner t;
	public static int opcao;

	public static void main(String[] args) {

		t = new Scanner(System.in);

		int opcao = menu();

		while (opcao != 0) {
			switch (opcao) {
			case 0: // sair
				break;

			case 1: // cadastrar
				cadastrar();
				break;

			case 2: // buscar
				buscar();
				break;

			case 3: // buscar
				alterar();
				break;
				
			case 4: // buscar
				listar();
				break;
			case 5: // buscar
				excluir();
				break;
			default:
				break;
			}
		}

		t.close();
	}

		private static void cadastrar() {
			System.out.println("\n\t --- Cadastrar um Jogo --- \t");
			try {
				JogoTO jogo = new JogoTO();
				jogo.setId(0);
				System.out.print("Digite o Título do Jogo:");
				jogo.setTitulo(t.next() + t.nextLine());
				System.out.print("Digite o genero do jogo (Ex: Aventura):");
				jogo.setGenero(t.next() + t.nextLine());
				System.out.print("Digite a descrição do jogo:");
				jogo.setDescricao(t.next() + t.nextLine());
				System.out.print("Digite a dia de lançamento do jogo:");
				int dia = Integer.parseInt(t.nextLine());
				System.out.print("Digite o mes de lançamento do jogo:");
				int mes = Integer.parseInt(t.nextLine());
				System.out.print("Digite o ano de lançamento do jogo:");
				int ano = Integer.parseInt(t.nextLine());
				jogo.setDataLancamento(new GregorianCalendar(ano, mes - 1, dia));

				JogoRepository repository = new JogoRepository();
				repository.cadastrar(jogo);

				System.out.println("Cadastrado com sucesso!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				opcao = menu();
			}
}

	private static void buscar() {
		System.out.print("Digite o código do jogo para busca: ");
		long id = t.nextLong();

		try {
			JogoRepository repository = new JogoRepository();
			JogoTO jogo = repository.buscar(id);
			if (jogo != null) {
				System.out.println(new StringJoiner(", ", "[ ", " ]").add(String.valueOf(jogo.getId()))
						.add(jogo.getTitulo()).add(jogo.getGenero()).add(jogo.getDescricao())
						.add(DateFormat.getDateInstance().format(jogo.getDataLancamento().getTime())).toString());
			} else {
				System.out.println("Jogo não encontrado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			opcao = menu();
		}

	}
	
		private static void listar() {
			try {
				JogoRepository repository = new JogoRepository();
				
				List<JogoTO> lista = repository.listar();
				if (lista != null) {
					System.out.println("-> Listando");
					for (JogoTO jogo : lista) {
						System.out.println(new StringJoiner(", ", "[ ", " ]").add(String.valueOf(jogo.getId()))
								.add(jogo.getTitulo()).add(jogo.getGenero()).add(jogo.getDescricao())
								.add(new SimpleDateFormat("dd/MM/yyyy").format(jogo.getDataLancamento().getTime())));
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			opcao = menu();
		}
	}
		private static void alterar() {
			System.out.print("Digite o código do jogo para busca: ");
			long id = t.nextLong();
			
			try {
				JogoRepository repository = new JogoRepository();
				JogoTO jogo = repository.buscar(id);
				if (jogo != null) {
					System.out.println(new StringJoiner(", ", "[ ", " ]").add(String.valueOf(jogo.getId()))
							.add(jogo.getTitulo()).add(jogo.getGenero()).add(jogo.getDescricao())
							.add(DateFormat.getDateInstance().format(jogo.getDataLancamento().getTime())).toString());
				} else {
					System.out.println("Jogo não encontrado!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				opcao = menu();
			}
}
		
	private static void excluir(){
		System.out.print("Digite o código do jogo para Excluir: ");
		long id = t.nextLong();
		try {
			JogoRepository repository = new JogoRepository();
			repository.remover(id);;
			System.out.println("Removido!");
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
	}

	public static int menu() {
		String[] menu = { "\t\t MENU \t\t", "1-Cadastrar", "2-Buscar","3-Alterar","4-Excluir", "0-Sair" };
		for (String op : menu) {
			System.out.println(op);
		}
		System.out.print("Escolha uma opção: ");
		return t.nextInt();
	}

}
