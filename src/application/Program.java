package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
		Calendar cal = Calendar.getInstance();
	
		System.out.print("Enter department's name: ");
		String department = sc.nextLine();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		WorkerLevel level = WorkerLevel.valueOf(sc.nextLine());
		System.out.print("Base salary: ");
		Double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(name, level, baseSalary, new Department(department));
		
		System.out.print("How many contracts to this worker? ");
		Integer contracts = sc.nextInt();
		
		for (int i=1; i<=contracts; i++) {
			sc.nextLine();
			System.out.println("Enter contract #" + i+ " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			Date data = sdf.parse(sc.next());
			System.out.print("Value per hour: ");
			Double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			Integer duration = sc.nextInt();
			HourContract contract = new HourContract(data, valuePerHour, duration);
			worker.addContract(contract);
		}
		
		System.out.println();
		System.out.println("Enter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		Date income = sdf2.parse(monthAndYear);
		cal.setTime(income);
		int month = 1 + cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Incoming for " + monthAndYear + ": " + worker.income(year, month));
		
		
		sc.close();
		

	}

}
