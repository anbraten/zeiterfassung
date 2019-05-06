package zeiterfassung.models;

import java.time.Duration;

public interface TimeableWork {
    Duration getDuration();

    double getCosts();
}
