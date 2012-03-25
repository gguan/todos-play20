package models;

import java.util.*;

import play.data.validation.Constraints.*;

import play.db.ebean.*;
import javax.persistence.*;

@Entity
public class TodoItem extends Model {
	
	@Id
	public Long id;
	
	@Required
	public String title;
	
	@Required
	public boolean done;
	
	@Required
	public int index;
	
	public static Finder<Long, TodoItem> find = new Finder(Long.class, TodoItem.class);
	
	public static List<TodoItem> all() {
		return find.all();
	}
	
	public static TodoItem create(TodoItem todo) {
		todo.save();
		return todo;
	}
	
	/**
	 * Update todo item status
	 */
	public static void update(Long id, String title, boolean done) {
		TodoItem todo = TodoItem.find.ref(id);
		todo.title = title;
		todo.done = done;
		todo.update();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
