package zeiterfassung.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;
import java.time.Duration;
import java.math.BigDecimal;
import java.util.Observable;

public class SubProject extends Observable implements DescribableContainer, TimeableWork {
    private List<Task> taskList;
    private String name;
    private String description;

    public void getTasks(Listable<Task> tasks){
        tasks.getList(taskList);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Duration getDuration(LocalDateTime start, LocalDateTime stop) {
        Duration duration = Duration.ofSeconds(0);
        for (Task task : taskList) {
            duration = duration.plus(task.getDuration(start, stop));
        }
        return duration;
    }

    @Override
    public double getCosts(LocalDateTime start, LocalDateTime stop) {
        double costs = 0;
        for (Task task : taskList) {
            costs += task.getCosts(start, stop);
        }
        return costs;
    }

    /**
     * @throws IllegalArgumentException
     */
    public void addTask(Task newTask) {
            taskList.add(newTask);
    }

    public boolean hasTask(Task taskToFind) {
        return taskList.contains(taskToFind);
    }

    public boolean removeTask(Task task) {
        return taskList.remove(task);
    }

    public SubProject(){
        taskList = new ArrayList<>();
    }


}
