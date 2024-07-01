package business.feature.impl;

import business.entity.Book;
import business.feature.IBookFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class BookFeatureImpl implements IBookFeature {
	public static List<Book> books = new ArrayList<>();
	
	public BookFeatureImpl() {
		books = IOFile.readFromFile(IOFile.PATH_BOOK);
	}
	
	@Override
	public void saveOrUpdate(Book book) {
		int indexCheck = findIndexById(book.getId());
		if (indexCheck < 0) {
			// chức năng thêm mới
			books.add(book);
			System.out.println("Đã thêm mới thành công");
		} else {
			// chức năng cập nhật
			books.set(indexCheck, book);
			System.out.println("Đã cập nhật thành công");
		}
		IOFile.writeToFile(IOFile.PATH_BOOK,books);
	}
	
	@Override
	public void deleteById(int idDelete) {
		int indexDelete = findIndexById(idDelete);
		if (indexDelete >= 0) {
			books.remove(indexDelete);
			System.out.println("Đã xóa thành công");
			IOFile.writeToFile(IOFile.PATH_BOOK,books);
		} else {
			System.err.println("Không tồn tại id = " + idDelete);
		}
	}
	
	@Override
	public int findIndexById(int idFind) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == idFind) {
				return i;
			}
		}
		return -1;
	}
}
