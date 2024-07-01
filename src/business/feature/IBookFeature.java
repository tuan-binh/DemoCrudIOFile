package business.feature;

import business.entity.Book;

public interface IBookFeature {
	
	void saveOrUpdate(Book book);
	
	void deleteById(int idDelete);
	
	int findIndexById(int idFind);
}
