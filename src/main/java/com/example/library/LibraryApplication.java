/*package com.example.demo;

import com.example.demo.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	private static  OrderService os;

    public DemoApplication(OrderService os) {
        this.os = os;
    }

    public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

}

public static void main(String[] args) {
	PaymentService paymentService = new PaymentService();
	OrderService orderService = new OrderService(paymentService);
	System.out.println(orderService.placeOrder());
}*/
package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}