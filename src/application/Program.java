package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {

// ------------------ EXERCICIO RESOLVIDO ---------------------
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Product> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
			//PIPELINE
			double avg = list.stream()
					.map(p -> p.getPrice())
					.reduce(0.0,  (x,y) -> x + y) / list.size();
			
			System.out.println("Average price: " + String.format("%.2f", avg));
			
			//PIPELINE
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> names = list.stream()
					.filter(p -> p.getPrice() < avg)
					.map(p -> p.getName())
					.sorted(comp.reversed())
					.collect(Collectors.toList());
			
			names.forEach(System.out::println);
			
					
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
/*		
//-------------- STREAM PIPELINE ------------------
		List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
		
		Stream<Integer> st1 = list.stream().map(x -> x * 10); //Stream com Inteiros
		System.out.println(Arrays.toString(st1.toArray()));
		
		int sum = list.stream().reduce(0,  (x,y) -> x + y); //somatório de todos os elementos da lista
		System.out.println("SUM = " + sum);

		int mult = list.stream().reduce(1,  (x,y) -> x * y); //multiplicação de todos os elementos da lista
		System.out.println("MULTIPLICAÇÃO = " + mult);
		
		List<Integer> newList = list.stream()
				.filter(x -> x % 2 == 0)
				.map(x -> x * 10)
				.collect(Collectors.toList());
		System.out.println(Arrays.toString(newList.toArray())); //operação para multiplicar por 10 os elementos pares originais (4 e 10)
		
/*
//--------------INTRODUÇÃO A STREAM ------------------
		List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
		
		Stream<Integer> st1 = list.stream().map(x -> x * 10); //Stream com Inteiros
		System.out.println(Arrays.toString(st1.toArray()));
		
		Stream<String> st2 = Stream.of("Maria", "Alex", "Bob"); //Stream com String
		System.out.println(Arrays.toString(st2.toArray()));
		
		Stream<Integer> st3 = Stream.iterate(0, x -> x + 2); //Stream de iteração
		System.out.println(Arrays.toString(st3.limit(10).toArray()));
		
		//Usando Stream na sequência de Fibonacci
		Stream<Long> st4 = Stream.iterate(new Long[] { 0L, 1L }, p -> new Long[] { p[1], p[0]+p[1] }).map(p -> p[0]); //Stream de iteração
		System.out.println(Arrays.toString(st4.limit(20).toArray()));
*/		
	}

}
