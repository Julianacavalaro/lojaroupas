package technuBrega;
import java.util.Arrays;
import java.util.Scanner;
public class TechnuBrega {
	public static void main(String[] args) {
		
		
		// Vetores
		String[] produtos = { " Camisa Brega       ", " Cal�a Brega        ", " T�nis Brega        ",
				" Blusa Brega        ", " Jaqueta Brega      ", " Luva de Couro Brega",
				" Chap�u Fedora Brega", " �culos Oakley      ", " Macac�o Normal     ",
				" Conjuto do Falc�o  " };
		String[] codigos = { "P01", "P02", "P03", "P04", "P05", "P06", "P07", "P08", "P09", "P10" };
		
		String[] dispo = { "dispon�vel", "dispon�vel", "dispon�vel", "dispon�vel", "dispon�vel", "dispon�vel",
				"dispon�vel", "dispon�vel", "dispon�vel", "dispon�vel" };
		
		int[] estoque = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		int[] carrinho = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		double[] precos = { 120, 200, 350, 220, 400, 100, 300, 800, 120, 1200 };
		
		// Outras vari�veis
		String nome = "";
		String codigo;
		char sexo;
		char continuar = 'S';
		int index = 0;
		int contador = 2;
		//matheus
		double total = 0;
		double subtrairDoTotal = 0;
				
		// While
		Scanner entrada = new Scanner(System.in);
		gambiarra: while (continuar == 'S') {
			
			// Cadastro
						if (contador == 1) {//Passa apenas uma vez por aqui
							System.out.print("Ol�, qual o seu nome Brega?   ");
							nome = entrada.next();
							while (true) { //Verifica a validade do char referente ao sexo
								System.out.print("Qual o seu sexo? (M - masculino) / (F - feminino) / (O - outro)   ");
								sexo = entrada.next().toUpperCase().charAt(0);
								
								if (sexo == 'M' || sexo == 'F' || sexo == 'O') {
									if (sexo == 'm' || sexo == 'M') {
										System.out.println("Ol� senhor " + nome + " o que voc� deseja comprar? (Insira o c�digo do produto)");
									}else if(sexo == 'f' || sexo == 'F') {
										System.out.println("Ol� senhora " + nome + " o que voc� deseja comprar? (Insira o c�digo do produto)");
									}else if(sexo == 'o' || sexo == 'O') {
										System.out.println("Ol� senhore " + nome + " o que voc� deseja comprar? (Insira o c�digo do produto)");
									}
//									else {
//										System.out.println("Insira os dados corretamente...");
//									}
									
									break;
								}else {
									System.out.println("Insira os dados corretamente...");
								}
//								
							}
							
//							System.out.println("\n\nO que voc� quer comprar? (Insira o c�digo do produto)"); //Primeira intera��o com o cliente
							contador++;
						}
			
			//Impress�o dos vetores			
			imprimirVetores(produtos, codigos, precos, dispo);
						
			//Leitura do c�digo do produto
			while (true) {
				System.out.print("\n**--- Insira o C�digo do Produto para add ao carrinho --**"
						+ "\n**-- Ou Digite \"Car\" para ver o seu carrinho --**"
						+ "\n**-- Ou Digite \"Caixa\" para ir para o caixa --**"
						+ "\n**-- Ou Digite \"Remv\" para remover itens do carrinho --**"
						+ "\n\n========>");
				codigo = entrada.next().toUpperCase();
				
				//Verifica��o se o c�digo digitado corresponde com os armazenados
				if (!codigo.equals("P01") && !codigo.equals("P02") && !codigo.equals("P03") && !codigo.equals("P04")
						&& !codigo.equals("P05") && !codigo.equals("P06") && !codigo.equals("P07") && !codigo.equals("P08")
						&& !codigo.equals("P09") && !codigo.equals("P10")&& !codigo.equalsIgnoreCase("Car")
						&& !codigo.equalsIgnoreCase("caixa") && !codigo.equalsIgnoreCase("remv")) {
					System.out.println("\n!!!!!--- C�digo inv�lido! Insira um c�digo v�lido ---!!!!!");
					System.out.println("===>Exemplo: P01 para selecionar Camisa Brega"
							+ "\n===>Exemplo: P08 para selecinoar �culos Oakley\n\n");
					imprimirVetores(produtos, codigos, precos, dispo);
				} else if(codigo.equalsIgnoreCase("car")){
					
					//Chamada da fun��o que imprimi os produtos caso o cliente queira saber o que h� no carrinho
					imprimirVetores(produtos, carrinho, precos, codigos);
					
					continue;
				}else if(codigo.equalsIgnoreCase("caixa")) {
					
					break;
				}else if(codigo.equalsIgnoreCase("remv")) {
				
					remvCarAddEstoque(produtos, precos, codigos, carrinho, estoque, dispo, total);
					imprimirVetores(produtos, codigos, precos, dispo);
				}else {
					
					//Chamada da fun��o que add e remove itens do carrinho +++ A fun��o que fornece o index do elemento selecionado
					index = indice(codigos, codigo);
					addCarRemvEstoque(carrinho, estoque, index, dispo);
					
					break;
				}//				
			}
			
			
						
			//Continuar Comprando ou Seguir para o Caixa
			while (true) {
				System.out.printf("%n%s voc� deseja continuar Comprando/ver seu Carrinho? "
						+ "'S' - Sim / 'N' - N�o, seguir para o caixa.%n", nome);
				continuar = entrada.next().toUpperCase().charAt(0);
				if (continuar == 'S') {
					break;
				} else if (continuar == 'N') {
					break gambiarra;
				} else {
					System.out.println("Codigo inv�lida");
				}
			}
		}
		
		// Caixa & Impress�o
//		notaFiscal(codigos,produtos, carrinho, precos);
		
		total = notaFiscal(codigos,produtos, carrinho, precos);
		
		System.out.println("O total ficou: " + total);
		
		metodoDePagamento(total);
		
		//entrada.close();
	}
	
	//FUN��ES
	
	//fun��o que imprimi lista de produtos:
	static void imprimirVetores(String[] prod, String[] cod, double[] prec, String[] disp) {
		
		for(int i=0; i<prod.length; i++) {
			
			System.out.print("**--"+prod[i]+"  --  "+cod[i]+"  --  R$"+prec[i]+"  --  "+disp[i]+"  --**\n");
		}
	}
	
	//Fun��o que imprimi o carrinho
	static void imprimirVetores(String[] prod, int[] car, double[] prec, String[] cod) {
		
		for(int i=0; i<prod.length; i++) {
			
			if(car[i]!=0) {
				
				System.out.print("**-- "+car[i]+" "+prod[i]+" -- R$"+prec[i]+" cada --"+ " C�DIGO: "+cod[i]+" --**\n");
			}
		}
	}
	
	//Fun��o que retorna o �ndice:
	static int indice(String[] vetor, String opcao) {
		
		int index = 0;
		for (int i = 0; i < vetor.length; i++) {
			
			if(opcao.equalsIgnoreCase(vetor[i])) {
				index = i;
			}		
		}
		return index;
	}	
	
	//Fun��o que add itens ao carrinho e Remove do estoque:
	static void addCarRemvEstoque(int[] car, int[] est, int idx, String[] disp) {
		
		int numItens;
		int verificador = 0;
		if(disp[idx].equals("dispon�vel")) {
			
			while(true) {
				
				Scanner entrada = new Scanner(System.in);
				System.out.println("Quantos itens voc� quer adicionar?");
				numItens = entrada.nextInt();
				// Add ao carrinho & Subtrai do Estoque:
				for (int i = 0; i < car.length; i++) {
					
					if (i == idx) {
						
						if(est[i]==0) {
							
							System.out.println("!!!--- Produto Indispon�vel ---!!!");
							break;
						}
						if(est[i]>=numItens && est[i]>0) {
							
							car[i]+=numItens;
							est[i]-=numItens;
							verificador++;
							if(est[i]==0) {
								
								disp[idx] = "indispon�vel";
							}
						}else if(est[i]>0){
							
							System.out.println("Voc� digitou uma quantidade de itens maior que o estoque\n");
						}else if(est[i]==0) {
							
							disp[idx] = "indispon�vel";
						}
					}
					
				}
				if(verificador!=0) {
					break;
				}
				
			}
			
		}
	}
	
	
	
	//Fun��o que remove do carrinho e add no estoque
		static void remvCarAddEstoque(String[] prod, double[] prec, String[] listaCodigos, int[] car, int[] est, String[] disp, double subtrair) {
			
			String codigoProdRemv;
			int qtdeItens;
			int vericador = 0;
			System.out.println("\n**-- Digite o c�digo do Produto que deseja remover. --**\n");
			
			//informa��es para o cliente
			imprimirVetores(prod, car, prec, listaCodigos);
			
			
			//leitura
			Scanner leia = new Scanner(System.in);
			
			System.out.print("\n========>");
			codigoProdRemv = leia.next();
			
			System.out.println("Quantos itens deseja remover?");
			System.out.print("\n========>");
			qtdeItens = leia.nextInt();
			
			/*
			while(true) {
				
				if(verificador!=0) {
					break;
				}
			}
			*/
			
			
			for(int i=0; i<listaCodigos.length; i++) {
				
				if(codigoProdRemv.equalsIgnoreCase(listaCodigos[i])) {
					
					if(car[i]==0) {
						
						System.out.println("\n!!!--- O item que est� tentando remover n�o est� em seu carrinho ---!!!\n");
						imprimirVetores(prod, car, prec, listaCodigos);
					}else {
						
						if(qtdeItens>est[i]) {
							
						}
						car[i]--;
						est[i]++;
						if(disp[i].equalsIgnoreCase("indispon�vel"))
							disp[i] = "dispon�vel";
						
						System.out.printf("%n***-- O item %s foi removido de seu carrinho com sucesso! --***%n%n", prod[i]);
					}
				}
			}
		}
		
		
	
	static double notaFiscal(String[] codigos, String[] produtos, int[] carrinho, double[] precos) {
		double total = 0;
		
		for (int i = 0; i < produtos.length; i++) {
			if(carrinho[i]!=0) {
			System.out.println(carrinho[i] + " - " + produtos[i] + " " +  precos[i]);
			total += precos[i]* carrinho[i];
			System.out.println("-----------");
			}
		}
		
		return total;
	}
	
	static void metodoDePagamento(double total) {
		
		Scanner entrada = new Scanner(System.in);
		double imposto = .09*total;
		char opc = ' ';
		
		do {
			System.out.println("Selecione uma op��o de pagamento");
			System.out.println("        Tabela de pagamento");
			System.out.println("-----------------------------------");
			System.out.println("1 - � vista em dinheiro ou cheque com 10% de desconto");
			System.out.println("2 - � vista no cart�o");
			System.out.println("3 -      Em duas vezes");
			System.out.println("4 -      Em tr�s vezes");
			System.out.println("-----------------------------------\n");
			
			System.out.print("Insira o C�digo da Forma de Pagamento ========>");
			opc = entrada.next().charAt(0);
			
			switch (opc) {
			case '1': 		
				System.out.println("\nPagamento � vista\n");
				System.out.printf("Total de R$%.2f com 10%% de desconto e com R$%.2f(9%%) de ICMS", total*.90, imposto);
				break;
			case '2':
				System.out.println("\nPagamento � vista no cart�o");
				System.out.printf("R$%.2f com R$%.2f(9%%) de ICMS", total, imposto);
				break;
			case '3':
				System.out.println("\nPagamento Parcelado em 2 Vezes no Cart�o\n");
				System.out.printf("R$%.2f%n", total);
				total /= 2;
				System.out.printf("Com 2 parcelas de R$%.2f sem juros com R$%.2f(9%%) de ICMS", total/2, imposto);
				break;
			case '4':
				System.out.println("\nPagamento Parcelado em 3 Vezes no Cart�o\n");
				System.out.printf("R$%.2f%n",total);
				total /= 3;
				System.out.printf("Com 3 parcelas de R$%.2f e com R$%.2f(9%%) de ICMS", total/3, imposto);
				break;
			default:
				System.out.println("\nEscolha uma op��o de pagamento v�lida...");
				break;
			}
	
		
		}while(opc != '1' && opc != '2' && opc != '3' && opc != '4');
		
	}
	
}