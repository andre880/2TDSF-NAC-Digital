package br.com.fiap.views;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import org.apache.axis2.AxisFault;

import br.com.fiap.bo.JogoBOStub;
import br.com.fiap.bo.JogoBOStub.Buscar;
import br.com.fiap.bo.JogoBOStub.BuscarResponse;
import br.com.fiap.bo.JogoBOStub.Cadastrar;
import br.com.fiap.bo.JogoBOStub.Jogo;
import br.com.fiap.bo.JogoBOStub.Listar;

public class ConsoleView {

	private static Scanner s;
	private static int opcao;

	public static void main(String[] args) {

		s = new Scanner(System.in);

		opcao = menu();

		while (opcao != 0) {

			switch (opcao) {
			case 0: // sair
				break;

			case 1: // cadastrar
				cadastrar();
				break;

			case 2: // buscar por id
				buscar();
				break;

			case 3: // listar
				listar();
				break;
			}

		}

		s.close();
		System.exit(0);

	}

	private static void listar() {
		try {
			JogoBOStub stub = new JogoBOStub();
			Listar listar = new Listar();
			List<Jogo> lista = Arrays.asList(stub.listar(listar).get_return());
			if (lista != null) {
				System.out.println("-> Listando");
				for (Jogo jogo : lista) {
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

	private static void buscar() {
		System.out.println("\n\t --- Buscar um Jogo --- \t");
		try {
			System.out.print("Digite o código (id) do jogo:");
			long id = s.nextLong();

			JogoBOStub stub = new JogoBOStub();
			Buscar buscar = new Buscar();
			buscar.setId(id);
			BuscarResponse response = stub.buscar(buscar);
			if (response.get_return() != null) {
				System.out.println(new StringJoiner(", ", "[ ", " ]").add(String.valueOf(response.get_return().getId()))
						.add(response.get_return().getTitulo()).add(response.get_return().getGenero())
						.add(response.get_return().getDescricao()).add(new SimpleDateFormat("dd/MM/yyyy")
								.format(response.get_return().getDataLancamento().getTime())));
			} else {
				System.out.println("-> Jogo não encontrado!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			opcao = menu();
		}
	}

	private static void cadastrar() {
		System.out.println("\n\t --- Cadastrar um Jogo --- \t");
		try {
			Jogo jogo = new Jogo();
			jogo.setId(0);
			System.out.print("Digite o Título do Jogo:");
			jogo.setTitulo(s.next() + s.nextLine());
			System.out.print("Digite o genero do jogo (Ex: Aventura):");
			jogo.setGenero(s.next() + s.nextLine());
			System.out.print("Digite a descrição do jogo:");
			jogo.setDescricao(s.next() + s.nextLine());
			System.out.print("Digite a dia de lançamento do jogo:");
			int dia = Integer.parseInt(s.next());
			System.out.print("Digite o mes de lançamento do jogo:");
			int mes = Integer.parseInt(s.next());
			System.out.print("Digite o ano de lançamento do jogo:");
			int ano = Integer.parseInt(s.next());
			jogo.setDataLancamento(new GregorianCalendar(ano, mes - 1, dia));

			JogoBOStub stub = new JogoBOStub();
			Cadastrar cadastrar = new Cadastrar();
			cadastrar.setEntity(jogo);
			stub.cadastrar(cadastrar);

			System.out.println("-> Cadastrado com sucesso!");
		} catch (AxisFault e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			opcao = menu();
		}
	}

	private static int menu() {
		String[] opcoes = { "1-Cadastrar", "2-Buscar por Id", "3-Listar", "0-Sair" };
		System.out.println("\n\t --- MENU --- \t");
		for (String string : opcoes) {
			System.out.println(string);
		}
		System.out.print("Escolha uma opção:");
		return s.nextInt();
	}

}
