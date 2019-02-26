package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    if(queryParams.containsKey("_id")) {
      String targetID = queryParams.get("_id")[0];
      filteredTodos = new Todo[0];
      filteredTodos[0] = getTodo(targetID);
    }

    if(queryParams.containsKey("status")) {
      if(((queryParams.get("status")[0] == "complete"))||((queryParams.get("status")[0] == "incomplete"))) {
        Boolean targetStatus = stringToBool(queryParams.get("status")[0]);
        filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
      }
    }

    return filteredTodos;
  }

  public Todo[] filterTodosById(Todo[] todos, String targetID) {
    return Arrays.stream(todos).filter(x -> x._id.equals(targetID)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByStatus(Todo[] todos, Boolean status) {
    return Arrays.stream(todos).filter(x -> x.status == status).toArray(Todo[]::new);
  }

  public boolean stringToBool(String status){
    if(status =="complete") {
      return true;
    }
    return false;
  }

}
