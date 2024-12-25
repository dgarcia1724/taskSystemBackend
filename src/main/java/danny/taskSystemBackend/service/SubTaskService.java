package danny.taskSystemBackend.service;

import danny.taskSystemBackend.entity.SubTask;
import danny.taskSystemBackend.repository.SubTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository;
    }

    public List<SubTask> getSubTasksByTaskId(Long taskId) {
        return subTaskRepository.findByTaskId(taskId);
    }

    public SubTask createSubTask(SubTask subTask) {
        return subTaskRepository.save(subTask);
    }

    public SubTask updateSubTask(Long id, SubTask updatedSubTask) {
        return subTaskRepository.findById(id)
                .map(existingSubTask -> {
                    existingSubTask.setTitle(updatedSubTask.getTitle());
                    existingSubTask.setCompleted(updatedSubTask.getCompleted());
                    return subTaskRepository.save(existingSubTask);
                })
                .orElseThrow(() -> new RuntimeException("SubTask not found with id " + id));
    }

    public void deleteSubTask(Long id) {
        subTaskRepository.deleteById(id);
    }
}
