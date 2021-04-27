package se.meepo.slothtodos;

import org.springframework.web.bind.annotation.*;
import se.meepo.slothtodos.model.Task;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
public class TasksController {

    @Inject
    TaskRepository tasksRepository;

    @RequestMapping(
            value = "tasks",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    public Task post(@Valid @RequestBody Task task) {
        return tasksRepository.save(task);
    }

    @RequestMapping(
            value = "tasks/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    public Task put(@PathVariable int id, @Valid @RequestBody Task task) {
        task.id = id;
        return tasksRepository.save(task);
    }

    @RequestMapping(
            value = "tasks/{id}",
            method = RequestMethod.DELETE
    )
    @CrossOrigin(origins = "http://localhost:8080")
    public void delete(@PathVariable int id) {
        tasksRepository.deleteById(id);
    }

    @RequestMapping(
        value = "tasks",
        method = RequestMethod.GET,
        consumes = "application/json"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    public List<Task> getByMemberId(@RequestParam(value = "memberId", required = true) int memberId) {
        return tasksRepository.findByMemberId(memberId);
    }
}
