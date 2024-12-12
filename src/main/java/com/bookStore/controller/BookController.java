package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.service.BookService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	

	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();

		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b , RedirectAttributes redirectAttributes) {
		if(!service.save(b)){
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to save the book. Please check the data.");
			return "redirect:/book_register";
		}
		return "redirect:/available_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
	
}
