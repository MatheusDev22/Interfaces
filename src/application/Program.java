package application;

import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.entityes.CarRental;
import model.entityes.Vehicle;
import model.services.RentalService;
import model.services.BrazilTaxService;

public class Program {

	public static void main (String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.print("Pick (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine() );
		System.out.print("Return (dd/MM/yyyy HH:mm): ");
		Date finish = sdf.parse(sc.nextLine() );
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel) );
		
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day.: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay,pricePerHour, new BrazilTaxService() );
		
		rentalService.processInvoice(cr);
		
		System.out.println("\nINVOICE");
		System.out.println("Basic payment..: " + String.format("%.2f", cr.getInvoice().getBasicPayment() ));
		System.out.println("Tax............: " + String.format("%.2f", cr.getInvoice().getTax() ));
		System.out.println("Total Payment..: " + String.format("%.2f", cr.getInvoice().getTotalPayment() ));
		
		
		sc.close();
	}
}
