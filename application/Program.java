package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner ler = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Entre com o numero de contrato: ");
		Integer numero = ler.nextInt();
		System.out.print("Insira a data do contrato: ");
		LocalDate date = LocalDate.parse(ler.next(), fmt);
		System.out.print("Valor do contrato: ");
		double valor = ler.nextDouble();
		
		Contract contract = new Contract(numero, date, valor);
		
		System.out.print("Entre com o numero de parcelas: ");
		int n = ler.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, n);
		
		System.out.println("Parcelas: ");
		
		for(Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		ler.close();
		
	}

}
