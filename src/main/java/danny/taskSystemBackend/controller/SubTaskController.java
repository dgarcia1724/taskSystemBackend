package danny.taskSystemBackend.controller;

import danny.taskSystemBackend.entity.SubTask;
import danny.taskSystemBackend.entity.Task;
import danny.taskSystemBackend.service.SubTaskService;
import danny.taskSystemBackend.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/subtasks")
public class SubTaskController {
    private final SubTaskService subTaskService;
    private final TaskService taskService;

    public SubTaskController(SubTaskService subTaskService, TaskService taskService) {
        this.subTaskService = subTaskService;
        this.taskService = taskService;
    }

    @GetMapping
    public List<SubTask> getSubTasksByTaskId(@PathVariable Long taskId) {
        return subTaskService.getSubTasksByTaskId(taskId);
    }

    @PostMapping
    public SubTask createSubTask(@PathVariable Long taskId, @RequestBody SubTask subTask) {
        Task task = taskService.getTaskById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));
        subTask.setTask(task);
        return subTaskService.createSubTask(subTask);
    }

    @PutMapping("/{id}")
    public SubTask updateSubTask(@PathVariable Long id, @RequestBody SubTask subTask) {
        return subTaskService.updateSubTask(id, subTask);
    }

    @DeleteMapping("/{id}")
    public void deleteSubTask(@PathVariable Long id) {
        subTaskService.deleteSubTask(id);
    }
}

