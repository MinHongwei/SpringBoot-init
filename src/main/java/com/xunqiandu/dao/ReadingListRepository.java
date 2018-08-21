package com.xunqiandu.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xunqiandu.datas.Book;

// 用了Spring Data JPA， 所以我们要做的就是简单地定义一个接口，扩展一下Spring Data JPA的JpaRepository接口
public interface ReadingListRepository extends JpaRepository<Book, Long> { // （Book）这里应该使用泛型
	List<Book> findByReader(String reader);
}

// 通过扩展JpaRepository，ReadingListRepository直接继承了18个执行常用持久化操作 的方法。
// JpaRepository是个泛型接口，有两个参数：仓库操作的领域对象类型，及其ID属性的 类型。
// 此外，我还增加了一个findByReader()方法，可以根据读者的用户名来查找阅读列表。 
