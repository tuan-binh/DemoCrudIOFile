package presentation;

import business.entity.Book;
import business.feature.IBookFeature;
import business.feature.impl.BookFeatureImpl;

import java.util.Scanner;

public class BookManagement {
	
	static IBookFeature bookFeature = new BookFeatureImpl();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("==================== MENU ====================");
			System.out.println("1. SHOW BOOK");
			System.out.println("2. ADD NEW BOOK");
			System.out.println("3. UPDATE BOOK BY ID");
			System.out.println("4. DELETE BOOK BY ID");
			System.out.println("5. EXIT");
			System.out.println("==============================================");
			System.out.println("Lựa chọn của bạn: ");
			int choice = inputNumber(sc);
			switch (choice) {
				case 1:
					showAllBooks();
					break;
				case 2:
					addNewBook(sc);
					break;
				case 3:
					updateBook(sc);
					break;
				case 4:
					deleteById(sc);
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.err.println("Vui lòng nhập lại từ 1 -> 5: ");
			}
		} while (true);
	}
	
	private static void updateBook(Scanner sc) {
		System.out.println("Nhập vào id cần cập nhật: ");
		int idUpdate = inputNumber(sc);
		int indexUpdate = bookFeature.findIndexById(idUpdate);
		if (indexUpdate < 0) {
			System.err.println("Không tồn tại id = " + idUpdate);
			return;
		}
		Book bookUpdate = BookFeatureImpl.books.get(indexUpdate);
		boolean isLoop = true;
		do {
			System.out.println("1. Cập nhật tiêu đề");
			System.out.println("2. Cập nhật tác giả");
			System.out.println("3. Cập nhật publisher");
			System.out.println("4. Cập nhật price");
			System.out.println("5. Quay lại");
			System.out.println("Lụa chọn của bạn: ");
			int choice = inputNumber(sc);
			switch (choice) {
				case 1:
					bookUpdate.setTitle(bookUpdate.inputTitleUpdate(sc, bookUpdate.getTitle()));
					break;
				case 2:
					bookUpdate.setAuthor(bookUpdate.inputAuthor(sc));
					break;
				case 3:
					bookUpdate.setPublisher(bookUpdate.inputPublisher(sc));
					break;
				case 4:
					bookUpdate.setPrice(bookUpdate.inputPrice(sc));
					break;
				case 5:
					isLoop = false;
					break;
				default:
					System.err.println("Vui lòng nhập lại từ 1 -> 5");
			}
		} while (isLoop);
	}
	
	private static void deleteById(Scanner sc) {
		System.out.println("Nhập vào id muốn xóa: ");
		int idDelete = inputNumber(sc);
		bookFeature.deleteById(idDelete);
	}
	
	public static void addNewBook(Scanner scanner) {
		System.out.println("Nhập vào số lượng sách muốn thêm: ");
		int number = inputNumber(scanner);
		for (int i = 0; i < number; i++) {
			Book book = new Book();
			book.inputData(scanner);
			bookFeature.saveOrUpdate(book);
		}
	}
	
	public static void showAllBooks() {
		if (BookFeatureImpl.books.isEmpty()) {
			System.err.println("Danh sách trống");
			return;
		}
		// method referance
		BookFeatureImpl.books.forEach(Book::displayData);
	}
	
	public static int inputNumber(Scanner scanner) {
		do {
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.err.println("Vui lòng nhập số");
			}
		} while (true);
	}
}
