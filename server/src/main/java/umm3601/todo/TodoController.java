package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import spark.Request;
import spark.Response;

import static umm3601.Util.buildFailJsonResponse;

public class TodoController {

    private final Gson gson;
    private TodoDatabase database;

    public TodoController(TodoDatabase database) {
      gson = new Gson();
      this.database = database;
    }

    public JsonElement getTodo(Request req, Response res) {
      res.type("application/json");
      String id = req.params("id");
      Todo todo = database.getTodo(id);
      if (todo != null) {

        return gson.toJsonTree(todo);
      } else {
        String message = "Todo with ID " + id + " wasn't found.";
        return buildFailJsonResponse("id", message);
      }
    }

    /**
     * Get a JSON response with a list of all the todos in the "database".
     *
     * @param req the HTTP request
     * @param res the HTTP response
     * @return a success JSON object containing all the todos
     */
    public JsonElement getTodos(Request req, Response res) {
      res.type("application/json");
      Todo[] todos = database.listTodos(req.queryMap().toMap());

      //return buildSuccessJsonResponse("todos", gson.toJsonTree(todos));
      return gson.toJsonTree(todos);
    }

}
