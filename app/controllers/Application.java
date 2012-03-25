package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.libs.Json;

import models.TodoItem;

import views.html.*;

public class Application extends Controller {
  
  	public static Result index() {
    	return ok(index.render("Todos - Play2.0", TodoItem.all()));
  	}

  	public static Result todos() {
		return ok(Json.toJson(TodoItem.all()));
	}
	
	public static Result update(Long id) {
		TodoItem.update(id, form().bindFromRequest().get("title"), Boolean.valueOf(form().bindFromRequest().get("done")));
		return ok();
	}
	
	public static Result add() {
		Form<TodoItem> todoForm = form(TodoItem.class).bindFromRequest();
		if (todoForm.hasErrors()) {
			return badRequest();
		} else {
			return ok(
				Json.toJson(TodoItem.create(todoForm.get()))
			);
		}
	}
	
	public static Result delete(Long id) {
		TodoItem.delete(id);
		return ok();
	}
  	
	public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
				controllers.routes.javascript.Application.add(),
				controllers.routes.javascript.Application.update(),
				controllers.routes.javascript.Application.delete()
			)
		);
	}
}