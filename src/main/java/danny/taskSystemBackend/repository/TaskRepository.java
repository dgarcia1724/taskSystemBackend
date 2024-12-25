package danny.taskSystemBackend.repository;

import danny.taskSystemBackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
