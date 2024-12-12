package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bRepo;

	// Method to check if the price is a valid number
	private boolean isValidPrice(String price) {
		try {
			// Try parsing the price as a number
			Double.parseDouble(price);
			return true; // valid price
		} catch (NumberFormatException e) {
			return false; // invalid price
		}
	}
	
	public boolean save(Book b) {

		if(b.getAuthor() == null || b.getAuthor().matches(".*(\\d).*")
				|| b.getName() == null || b.getName().matches(".*(\\d).*") || !isValidPrice(b.getPrice())){


			return false ;
		}
		bRepo.save(b);
		return  true;
	}
	
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}
	
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
}
