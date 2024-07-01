package business.entity;

import business.feature.impl.BookFeatureImpl;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable {
	private int id;
	private String title;
	private String author;
	private String publisher;
	private double price;
	
	public Book() {
	}
	
	public Book(int id, String title, String author, String publisher, double price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void inputData(Scanner scanner) {
		this.id = idAutoIncrement();
		this.title = inputTitle(scanner);
		this.author = inputAuthor(scanner);
		this.publisher = inputPublisher(scanner);
		this.price = inputPrice(scanner);
	}
	
	public double inputPrice(Scanner scanner) {
		System.out.println("Nhập vào giá quyển sách: ");
		do {
			try {
				String price = scanner.nextLine();
				if (price.trim().isEmpty()) {
					System.err.println("Giá không được để trống");
				} else if (Double.parseDouble(price) <= 0) {
					System.err.println("Giá không được nhỏ hơn 0");
				} else {
					return Double.parseDouble(price);
				}
			} catch (NumberFormatException e) {
				System.err.println("Vui lòng nhập lại số:");
			}
		} while (true);
	}
	
	public String inputPublisher(Scanner scanner) {
		System.out.println("Nhập vào tên nhà xuất bản: ");
		do {
			String publisher = scanner.nextLine();
			if (publisher.trim().isEmpty()) {
				System.err.println("Tên nhà xuất bản không được để trống");
			} else {
				return publisher;
			}
		} while (true);
	}
	
	public String inputTitle(Scanner scanner) {
		System.out.println("Nhập vào tiêu đề sách: ");
		do {
			String title = scanner.nextLine();
			if (title.trim().isEmpty()) {
				System.err.println("Tiêu đề không được để trống");
			} else {
				boolean isExist = BookFeatureImpl.books.stream().anyMatch(item -> item.getTitle().equals(title));
				if (isExist) {
					System.err.println("Tiêu đề đã bị trùng");
				} else {
					return title;
				}
			}
		} while (true);
	}
	
	public String inputTitleUpdate(Scanner scanner, String oldTitle) {
		System.out.println("Nhập vào tiêu đề mới: ");
		do {
			String newTitle = scanner.nextLine();
			if (newTitle.trim().isEmpty()) {
				System.err.println("Không được để trống");
			} else {
				if (oldTitle.equals(newTitle)) {
					return newTitle;
				} else {
					boolean isExist = BookFeatureImpl.books
							  .stream()
							  .anyMatch(item -> item.getTitle().equals(newTitle));
					if (isExist) {
						System.err.println("Tên đã bị trùng");
					} else {
						return newTitle;
					}
				}
				
			}
		} while (true);
	}
	
	public String inputAuthor(Scanner scanner) {
		System.out.println("Nhập vào tên tác giả: ");
		do {
			String author = scanner.nextLine();
			if (author.trim().isEmpty()) {
				System.err.println("Tên tác giả không được để trống");
			} else {
				return author;
			}
		} while (true);
	}
	
	public int idAutoIncrement() {
		int max = 0;
		// cách 1: dùng for để tìm max;
		for (Book b : BookFeatureImpl.books) {
			if (b.getId() > max) {
				max = b.getId();
			}
		}
		return max + 1;
		
		// cach 2: dùng java 8 sử dụng phương thức max
//		Optional<Book> optionalBook = BookFeatureImpl.books.stream().max(Comparator.comparingInt(Book::getId));
//		if (optionalBook.isPresent()) {
//			return optionalBook.get().getId() + 1;
//		} else {
//			return 1;
//		}
	}
	
	public void displayData() {
		System.out.printf(
				  "[ ID: %5d | Title: %10s | Author: %10s | Publisher: %10s | Price: %.2f ]\n",
				  this.id,
				  this.title,
				  this.author,
				  this.publisher,
				  this.price
		);
	}
}
