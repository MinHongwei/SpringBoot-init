package com.xunqiandu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xunqiandu.dao.ReadingListRepository;
import com.xunqiandu.datas.Book;


@Controller
@RequestMapping("/") 
public class ReadingListController {

	private ReadingListRepository readingListRepository;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}

//	@Controller 表明该类内的所有方法默认返回页面路径，加了 @ResponseBody的方法返回数据。
//
//	@RestController则是相当于 @Controller @ResponseBody两个注解，该类返回的都是数据，不返回页面。
//	
//	ReadingListController使用了@Controller注解，
//	这样组件扫描会自动将其注册为 Spring应用程序上下文里的一个Bean。
//	它还用了@RequestMapping注解，将其中所有的处理器 方法都映射到了“/”这个URL路径上。 该控制器有两个方法。
//	 readersBooks()：处理/{reader}上的HTTP GET请求，根据路径里指定的读者，
//	从（通 过控制器的构造器注入的）仓库获取Book列表。随后将这个列表塞入模型，用的键是 books，
//	后返回readingList作为呈现模型的视图逻辑名称。
//	 addToReadingList()：处理/{reader}上的HTTP POST请求，
//	将请求正文里的数据绑定 到一个Book对象上。
//	该方法把Book对象的reader属性设置为读者的姓名，随后通过仓 库的save()方法保存修改后的Book对象，
//	后重定向到/{reader}（控制器中的另一个方 法会处理该请求）。
//	readersBooks()方法后返回readingList作为逻辑视图名，
//	为此必须创建该视图。因为 在项目开始之初我就决定要用Thymeleaf来定义应用程序的视图，
//	所以就在src/main/ resources/templates里建一个名为readingList.html的文件
}
