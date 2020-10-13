package technuBrega;

import java.util.Scanner;
public class Janela {
	// programa mesmo
	public static void main(String[] args)
	{
	
		String nome = "O NOME DELA É Juliana Cavalaro de Oliveira";
		
		System.out.print("╔");
		linha(nome.length()+1);
		System.out.println("╗");
		//CANTO
		System.out.println("║"+nome+"║");
		System.out.print("╚");
		linha(nome.length()+1);
		System.out.println("╝");
		
		
		
		
	}
	// terminou o programa
	
 	public static void linha(int tamanho)
 	{
 		for (int x = 1; x<tamanho; x++)
 		{
 			System.out.print("═");
 		}
 		
 	}
 	public static void linhacoluna(int tamanho, int linhas)
 	{
 		//LINHA SUPERIOR
 		System.out.print("╔"); //CANTO
 		for (int x = 1; x<tamanho; x++)
 		{
 			System.out.print("═");
 		}
 		System.out.println("╗"); //CANTO
 		
 		//MEIO
 		for (int d = 1 ; d < linhas; d++) {
	 		System.out.print("║");  //alt 186
	 		for (int x = 1; x<tamanho; x++)
	 		{
	 			System.out.print(" ");
	 		}
	 		System.out.println("║");
 		}
 		//parte de baixo
 		System.out.print("╚");
 		for (int x = 1; x<tamanho; x++)
 		{
 			System.out.print("═");
 		}
 		System.out.println("╝");
 	}
 	
 	
}