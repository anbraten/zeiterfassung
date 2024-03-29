package zeiterfassung.xml;

import zeiterfassung.models.*;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Test data generator
 */
public class TestData {

    /**
     * Get some sample data
     *
     * @return Sample {@link TimeRegistrationRoot}
     */
    public static TimeRegistrationRoot getData() {
        LocalDateTime startTime = LocalDateTime.of(2017, 6, 5, 12, 35);
        TaskRandomizedTimeFiller taskFiller = new TaskRandomizedTimeFiller(startTime, 0, 1, 60);
        TimeRegistrationRoot root = new TimeRegistrationRoot();

        Area area = new Area("Privat", "Hier findet man alle meine privaten Projekte");
        root.addArea(area);

        Project project = new Project("Garten", "Dieses Projekt ist für meine Garten Aufgaben");
        project.addRole(new Role("Angestellter", "Sklave", 5.8));
        project.addRole(new Role("Chef", "Benz", 17.3));

        area.addProject(project);

        SubProject subProject = new SubProject("Baum Pflege", "");
        project.addSubProject(subProject);
        Task task = new Task("rotes Kugelobst ernten", "Only the big red ones are worth it.");
        task.setEstimatedDuration(Duration.ofHours(333));
        task.setRole(project.getRoleByName("Angestellter"));
        taskFiller.fill(task);
        task.addWorkChunk(new WorkChunk(LocalDateTime.now(), LocalDateTime.now().plusHours(4), "Toll"));
        subProject.addTask(task);
        task.start();

        SubProject subProject1 = new SubProject("Federtiere", "");
        project.addSubProject(subProject1);

        Task task1 = new Task("Stall ausmisten", "");
        task1.setRole(project.getRoleByName("Chef"));

        taskFiller.fill(task1);

        subProject1.addTask(task1);

        Task task2 = new Task("Eier sammeln", "");
        task2.setRole(project.getRoleByName("Angestellter"));
        taskFiller.fill(task2);
        subProject1.addTask(task2);

        Area areaFH = new Area("FH", "Hier findet man FH Kram");

        Project aem = new Project("Agile Entwicklungsmethoden", "Modul AEM im SS2019");
        areaFH.addProject(aem);

        Project seg = new Project("Software Engineering", "Modul SEG im SS2019");
        areaFH.addProject(seg);

        Project pij = new Project("Programmieren in Java", "Modul PIJ im SS2019");
        pij.addRole(new Role("Hiwi", "Der kleine Hilfsarbeiter", 11.2));
        areaFH.addProject(pij);

        SubProject sprint4 = new SubProject("4. Sprint", "Unser 4. Sprint");
        aem.addSubProject(sprint4);

        SubProject anaphase = new SubProject("Analysephase", "Anforderungsanalyse und Lastenheft");
        seg.addSubProject(anaphase);


        SubProject sprint2 = new SubProject("2. Sprint", "Unser 2. einwöchiger Sprint");
        pij.addSubProject(sprint2);

        Task xml = new Task("XML Import", "XML Datei erzeugen und auslesen", new Role("Student", "Ein ganz normaler Student ohne besondere Qualifikationen", 8.5));
        xml.addWorkChunk(new WorkChunk(LocalDateTime.now(), LocalDateTime.now().plusHours(4), "Meine Beispielarbeit"));
        sprint2.addTask(xml);

        root.addArea(areaFH);

        return root;
    }
}
